/*
 *
 * TURTLE PLAYER
 *
 * Licensed under MIT & GPL
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Created by Edd GPSS (com.gpss.com)
 * More Information @ com.gpss.com
 *
 */

package com.gsplayer.playlist.playorder;

import android.util.Log;
import com.gsplayer.model.Track;
import com.gsplayer.persistance.framework.executor.OperationExecutor;
import com.gsplayer.persistance.framework.paging.Paging;
import com.gsplayer.persistance.framework.sort.OrderSet;
import com.gsplayer.persistance.framework.sort.SortOrder;
import com.gsplayer.persistance.source.sql.First;
import com.gsplayer.persistance.source.sqlite.QuerySqlite;
import com.gsplayer.persistance.turtle.db.TurtleDatabase;
import com.gsplayer.persistance.turtle.db.structure.Tables;
import com.gsplayer.persistance.turtle.mapping.TrackCreator;
import com.gsplayer.playlist.Playlist;

public class PlayOrderSorted implements PlayOrderStrategy
{

	private final Playlist playlist;
	private final TurtleDatabase db;

	public PlayOrderSorted(final TurtleDatabase db,
								  final Playlist playlist)
	{
		this.playlist = playlist;
		this.db = db;
	}

	public Track getNext(Track currTrack)
	{
		return get(currTrack, new DefaultOrder(SortOrder.ASC));
	}

	public Track getPrevious(Track currTrack)
	{
		return get(currTrack, new DefaultOrder(SortOrder.DESC));
	}

	private Track get(Track ofTrack, OrderSet<? super Tables.Tracks> order)
	{
		OrderSet<? super Tables.Tracks> currOrder = order;
		while(!currOrder.isEmpty())
		{
			Log.v(PlayOrderSorted.class.getName(),
					  "Generate Paging Filters from: " + order);
			Log.v(PlayOrderSorted.class.getName(),
					  "resulting in Paging Filters : " + Paging.getFilter(playlist.getCompressedFilter(), ofTrack, currOrder));
			Track nextTrack = OperationExecutor.execute(
				  db,
				  new QuerySqlite<Tables.Tracks, Tables.Tracks, Track>(
							 Paging.getFilter(playlist.getCompressedFilter(), ofTrack, currOrder),
							 order,
							 new First<Track>(Tables.TRACKS, new TrackCreator())
				  )
			);
			if(nextTrack != null){
				return nextTrack;
			}
			currOrder = currOrder.removeLast();
		}
		return null;
	}
}
