/*
 * This file is part of AmbientSpout.
 *
 * Copyright (c) ${project.inceptionYear}-2012, croxis <https://github.com/croxis/>
 *
 * AmbientSpout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AmbientSpout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AmbientSpout. If not, see <http://www.gnu.org/licenses/>.
 */
package me.rahazan.ambientspout.model;

import me.rahazan.ambientspout.AmbientSpout;

public class Settings {

	
	private static int delayBetweenSongs = 20;
	private static int delayRandomness = 2;
	public static int fadeOutAreaChange = 4;
	public static boolean defaultOn = true;
	public static boolean musicAreaEnabled = false;
	public static boolean enableWebServer = true;
	public static boolean useBackgroundImage = true;
	public static boolean resetSongOnRespawn = true;
	public static boolean showCurrentSong = true;
	public static boolean effectLoopsEnabled = true;
	public static boolean notifyPlayerEffectLoopDownload = true;
	/**
	 *  Loads values from config.yml (should be used for reload)
	 */
	public static void loadConfig(){
		delayBetweenSongs 	= AmbientSpout.callback.getConfig().getInt(		"Settings.DelayBetweenSongs", 20);
		delayRandomness 	= AmbientSpout.callback.getConfig().getInt(		"Settings.DelayRandomness"	, 2);
		fadeOutAreaChange 	= AmbientSpout.callback.getConfig().getInt(		"Settings.FadeOutLength", 3);
		defaultOn			= AmbientSpout.callback.getConfig().getBoolean(	"Settings.DefaultOn"		 , true);
		musicAreaEnabled 	= AmbientSpout.callback.getConfig().getBoolean(	"Settings.MusicAreaEnabled"	 , true);
		enableWebServer 	= AmbientSpout.callback.getConfig().getBoolean(	"Settings.EnableWebServer"	 , true);
		useBackgroundImage  = AmbientSpout.callback.getConfig().getBoolean(	"Settings.UseBackgroundImage", true);
		resetSongOnRespawn  = AmbientSpout.callback.getConfig().getBoolean(	"Settings.ResetSongOnRespawn", true);
		showCurrentSong 	= AmbientSpout.callback.getConfig().getBoolean( "Settings.ShowCurrentSong"	 , true);
		effectLoopsEnabled	= AmbientSpout.callback.getConfig().getBoolean( "Settings.EffectLoopsEnabled", true);
		notifyPlayerEffectLoopDownload = AmbientSpout.callback.getConfig().getBoolean("Settings.NotifyPlayerEffectLoopDownload", true);
	}
	
	/**
	 * @return delay to be had between songs.
	 */
	public static int getDelayBetweenSongs(){
		return (AmbientSpout.random.nextInt(delayRandomness*2)-delayRandomness + delayBetweenSongs);
	}
	
	
	
	
}
