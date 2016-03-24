package com.gsplayer.persistance.turtle.db.structure;

import com.gsplayer.persistance.source.relational.Field;
import com.gsplayer.util.Shorty;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

public class Views
{
	public final static Albums ALBUMS = new Albums();
	public final static Artists ARTISTS = new Artists();
	public final static Genres GENRES = new Genres();
	public final static Songs SONGS = new Songs();

	public static final class Albums implements Tables.AlbumsReadable
	{
		public Set<Tables.Tracks> getTables()
		{
			return Shorty.oneElementSet(Tables.TRACKS);
		}

		public List<Field> getFields()
		{
			return Arrays.asList(new Field[]{ALBUM});
		}
	}

	public static final class Artists implements Tables.ArtistsReadable
	{
		public Set<Tables.Tracks> getTables()
		{
			return Shorty.oneElementSet(Tables.TRACKS);
		}

		public List<Field> getFields()
		{
			return Arrays.asList(new Field[]{ARTIST});
		}
	}

	public static final class Genres  implements Tables.GenresReadable
	{
		public Set<Tables.Tracks> getTables()
		{
			return Shorty.oneElementSet(Tables.TRACKS);
		}

		public List<Field> getFields()
		{
			return Arrays.asList(new Field[]{GENRE});
		}
	}

	public static final class Songs  implements Tables.SongsReadable
	{
		public Set<Tables.Tracks> getTables()
		{
			return Shorty.oneElementSet(Tables.TRACKS);
		}

		public List<Field> getFields()
		{
			return Arrays.asList(new Field[]{TITLE});
		}
	}

}
