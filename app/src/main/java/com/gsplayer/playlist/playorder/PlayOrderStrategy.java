package com.gsplayer.playlist.playorder;

import com.gsplayer.model.Track;

public interface PlayOrderStrategy {

	/*
	  * @return null if strategy has no next song for this config and tracklist
     */
	Track getNext(Track currTrack);

	/**
	 * @return null if strategy has no previous song for this config and tracklist
	 */
	Track getPrevious(Track currTrack);

}
