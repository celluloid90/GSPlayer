package com.gsplayer.playlist.playorder;

import com.gsplayer.model.Track;
import com.gsplayer.persistance.framework.executor.OperationExecutor;
import com.gsplayer.persistance.framework.sort.RandomOrder;
import com.gsplayer.persistance.source.sql.First;
import com.gsplayer.persistance.source.sqlite.QuerySqlite;
import com.gsplayer.persistance.turtle.db.TurtleDatabase;
import com.gsplayer.persistance.turtle.db.structure.Tables;
import com.gsplayer.persistance.turtle.mapping.TrackCreator;
import com.gsplayer.playlist.Playlist;

public class PlayOrderRandom implements PlayOrderStrategy
{

	private final Playlist playlist;
	private final TurtleDatabase db;

	public PlayOrderRandom(TurtleDatabase db,
								  Playlist playlist)
	{
		this.playlist = playlist;
		this.db = db;
	}

	public Track getNext(Track currTrack)
	{
		return get();
	}

	public Track getPrevious(Track currTrack)
	{
		return get();
	}

	private Track get()
	{
		return OperationExecutor.execute(db,
				  new QuerySqlite<Tables.Tracks, Tables.Tracks, Track>(
							 playlist.getCompressedFilter(),
							 new RandomOrder<Tables.Tracks>(),
							 new First<Track>(Tables.TRACKS, new TrackCreator())));
	}
}
