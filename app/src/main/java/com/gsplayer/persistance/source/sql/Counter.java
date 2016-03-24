package com.gsplayer.persistance.source.sql;

import android.database.Cursor;
import com.gsplayer.persistance.framework.mapping.Mapping;
import com.gsplayer.persistance.source.relational.Table;
import com.gsplayer.persistance.source.sql.query.Select;
import com.gsplayer.persistance.source.sql.query.Sql;

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

public abstract class Counter implements Mapping<Select, Integer, Cursor>
{
	private final Table table;

	public Counter(Table table)
	{
		this.table = table;
	}

	public Select get()
	{
		return new Select(table, Select.SelectMethod.COUNT, Sql.FIELD_PSEUDO_STAR);
	}
}
