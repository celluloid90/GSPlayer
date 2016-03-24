package com.gsplayer.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import com.gsplayer.TurtlePlayer;
import com.gsplayer.controller.TouchHandler;
import com.gsplayer.model.Track;
import com.gsplayer.model.TrackBundle;
import com.gsplayer.persistance.framework.filter.Filter;
import com.gsplayer.persistance.turtle.db.structure.Tables;
import com.gsplayer.player.ObservableOutput;
import com.gsplayer.player.Output;
import com.gsplayer.player.OutputCommand;
import com.gsplayer.playlist.playorder.PlayOrderStrategy;

/**
 * TURTLE PLAYER
 * <p/>
 * Licensed under MIT & GPL
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * <p/>
 * More Information @ com.gpss.com
 *
 * @author Simon Honegger (Hoene84)
 */

public class AlbumArtView
{

	private final View albumArtViewGroup;

	//AlbumArt
	private final AlbumArt albumArt;
	private final AlbumArt albumArtLeft;
	private final AlbumArt albumArtRight;

	private AsyncTask<Track, Void, TrackBundle> actualAsyncTask = null;

	public AlbumArtView(final Activity activity,
							  final TurtlePlayer tp,
							  final PlayOrderStrategy playOrderStrategy)
	{
		albumArtViewGroup = activity.findViewById(com.gpss.gsplayer.R.id.relativeLayout_albumArt);

		albumArt = new AlbumArt(albumArtViewGroup, AlbumArt.Type.CENTER, tp.db);
		albumArtLeft = new AlbumArt(albumArtViewGroup, AlbumArt.Type.LEFT, tp.db);
		albumArtRight = new AlbumArt(albumArtViewGroup, AlbumArt.Type.RIGHT, tp.db);

		tp.player.addObserver(new ObservableOutput.PlayerObserver()
		{
			public void trackChanged(final Track track, int lengthInMillis)
			{
				albumArt.setTrackDigest(track);
				albumArtLeft.setTrackDigest(null);
				albumArtRight.setTrackDigest(null);

				actualAsyncTask = new AsyncTask<Track, Void, TrackBundle>(){

					@Override
					protected TrackBundle doInBackground(Track... params)
					{
						Thread.currentThread().setName(Thread.currentThread().getName() + ":albumArtUpdater");
						if(actualAsyncTask == this)
						{
							return tp.playlist.enrich(playOrderStrategy, params[0]);
						}
						return null;
					}

					@Override
					protected void onPostExecute(TrackBundle trackBundle)
					{
						if(actualAsyncTask == this && trackBundle != null)
						{
							albumArt.setTrack(trackBundle.getTrack());
							albumArtRight.setTrack(trackBundle.getTrackAfter());
							albumArtLeft.setTrack(trackBundle.getTrackBefore());
						}
					}
				}.execute(track);

			}

			public void started()
			{
				//ignore
			}

			public void stopped()
			{
				//ignore
			}

			public String getId()
			{
				return "AlbumArtUpdater";
			}
		});


		TouchHandler touchHandler = new TouchHandler(
				  activity,
				  albumArt.getAlbumArtView(),
				  albumArtLeft.getAlbumArtView(),
				  albumArtRight.getAlbumArtView()
		){
			@Override
			protected void nextGestureRecognized()
			{
				tp.player.connectPlayer(new OutputCommand()
				{
					public void connected(Output output)
					{
						output.play(tp.playlist.getNext(playOrderStrategy, output.getCurrTrack()));
					}
				});
			}

			@Override
			protected void previousGestureRecognized()
			{
				tp.player.connectPlayer(new OutputCommand()
				{
					public void connected(Output output)
					{
						output.play(tp.playlist.getPrevious(playOrderStrategy, output.getCurrTrack()));
					}
				});
			}

			@Override
			protected void filterSelected(final Filter<? super Tables.Tracks> filter,
													final boolean wasActive)
			{
				tp.player.connectPlayer(new OutputCommand()
				{
					public void connected(Output output)
					{
						if(wasActive){
							tp.playlist.removeFilter(filter);
						}
						else
						{
							tp.playlist.addFilter(filter);
						}

					}
				});
			}

			public String getId()
			{
				return "SwipeAndInstantFilterSelectionDetector";
			}
		};

		albumArt.getAlbumArtView().setOnTouchListener(touchHandler);
		tp.playlist.addObserver(touchHandler);
		tp.player.addObserver(touchHandler);
	}
}
