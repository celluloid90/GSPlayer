package com.gsplayer.persistance.turtle.mapping;

import android.database.Cursor;
import com.gsplayer.model.Album;
import com.gsplayer.model.AlbumDigest;
import com.gsplayer.persistance.framework.creator.ResultCreator;
import com.gsplayer.persistance.turtle.db.structure.Tables;

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

public class AlbumCreator implements ResultCreator<Tables.AlbumsReadable, Album, Cursor>
{
    public Album create(Cursor source)
    {
        return new AlbumDigest(source.getString(source.getColumnIndex(Tables.AlbumsReadable.ALBUM.getName())));
    }
}
