package com.gsplayer.persistance.turtle.mapping;

import android.database.Cursor;
import com.gsplayer.model.ArtistDigest;
import com.gsplayer.persistance.framework.creator.ResultCreator;
import com.gsplayer.persistance.turtle.db.structure.Tables;
import com.gsplayer.persistance.turtle.db.structure.Views;

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

public class ArtistCreator implements ResultCreator<Views.Artists, ArtistDigest, Cursor>
{
    public ArtistDigest create(Cursor source)
    {
        return new ArtistDigest(source.getString(source.getColumnIndex(Tables.ArtistsReadable.ARTIST.getName())));
    }
}
