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

// Package
package com.gsplayer;

import android.app.Application;
import com.gsplayer.persistance.turtle.db.TurtleDatabase;
import com.gsplayer.player.ObservableOutput;
import com.gsplayer.player.PlayerServiceConnector;
import com.gsplayer.playlist.Playlist;

public class TurtlePlayer extends Application
{
	public final ObservableOutput player;
	public Playlist playlist;
	public TurtleDatabase db;

	private static TurtlePlayer instance = null;

	public TurtlePlayer()
	{
		player = new PlayerServiceConnector(this);
		/*mp = new MediaPlayer();
		playlist = new Playlist();
		isPaused = true;
		isInitialised = false;
		currentlyPlaying = new Track();*/
	}

	@Override
	public void onCreate()
	{
		instance = this;
	}

	public static TurtlePlayer getStaticInstance()
	{
		if(instance == null){
			throw new IllegalStateException("Not initialized yet");
		}
		return instance;
	}
}
