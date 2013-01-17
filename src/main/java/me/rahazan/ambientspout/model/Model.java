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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.rahazan.ambientspout.AmbientSpout;
import me.rahazan.ambientspout.controller.FileHandler;
import me.rahazan.ambientspout.model.datatype.EffectLoop;
import me.rahazan.ambientspout.model.datatype.Song;
import me.rahazan.ambientspout.view.MainGUI;

import org.bukkit.entity.Player;

public class Model {

	public static Map<String, Integer> playerTaskID = new HashMap<String, Integer>();
	public static Map<String, String> playerCurrentSong = new HashMap<String, String>();
	//public static Map<Player, MusicArea> playerMusicArea = new HashMap<Player, MusicArea>();
	public static Map<String, MainGUI> playerPopup = new HashMap<String, MainGUI>();
	public static Map<String, Boolean> playerMusicEnabled = new HashMap<String, Boolean>();
	
	public static List<Song> ambientMusic = new ArrayList<Song>();
	public static List<Song> effectLoopsMusic = new ArrayList<Song>();
	
	public static List<EffectLoop> effectLoops = new ArrayList<EffectLoop>();
	
	public static void updateGUI(Player p){
		if (playerPopup.containsKey(p.getName())) {
			playerPopup.get(p.getName()).updateCurrentSong();
		}
	}
	
	/**
	 *  Serialize!
	 */
	public static void savePlayerSettings(){
		try {
			FileHandler.save(playerMusicEnabled, "plugins/AmbientSpout/playerdata1.dat");
		} catch (Exception e) {
			AmbientSpout.log.severe("[AmbientSpout] Something went wrong saving player settings to files!");
			e.printStackTrace();
		}
	}
	
	/**
	 *  Deserialize!
	 */
	@SuppressWarnings("unchecked")
	public static void loadPlayerSettings(){
		try {
			File f = new File("plugins/AmbientSpout/playerdata1.dat");
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("Creating playerdata1.dat");
				savePlayerSettings();
				return;
			}
			playerMusicEnabled = (Map<String, Boolean>) FileHandler.load("plugins/AmbientSpout/playerdata1.dat");
		} catch (Exception e) {
			AmbientSpout.log.severe("[AmbientSpout] Something went wrong loading player settings from files!");
			e.printStackTrace();
		}
	}
	
	
}
