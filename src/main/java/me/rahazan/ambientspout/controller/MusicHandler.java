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
package me.rahazan.ambientspout.controller;

import me.rahazan.ambientspout.AmbientSpout;
import me.rahazan.ambientspout.model.Model;
import me.rahazan.ambientspout.model.Settings;
import me.rahazan.ambientspout.model.datatype.Song;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MusicHandler {

	public static void nextSong(final Player player, boolean fade){
		if (Model.ambientMusic.size() == 0){
			System.out.println("[AmbientSpout] No ambient music found whatsoever!");
			return;
		}
		stopMusic(player, fade);
		final Song nextSong = getNextSong(player);
		Model.playerCurrentSong.put(player.getName(), nextSong.getUrl());
		
		
		
		if (fade){
		
			
			Model.playerTaskID.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(AmbientSpout.callback, new Runnable(){
				
				@Override
				public void run() {
					SpoutManager.getSoundManager().playCustomMusic(AmbientSpout.callback, (SpoutPlayer) player, nextSong.getUrl(), true);
					//player.sendMessage("Fadeout done, starting song!");
					 prepareForEndOfSong(player, nextSong.getDuration());
				}
				
			}, Settings.fadeOutAreaChange*20 + 10));
		}
		
		
		
		else {

			 SpoutManager.getSoundManager().playCustomMusic(AmbientSpout.callback, (SpoutPlayer) player, nextSong.getUrl(), true);
			// player.sendMessage("Starting song!");
			 prepareForEndOfSong(player, nextSong.getDuration());
		}
		Model.updateGUI(player);
	}
	
	
	
	private static void prepareForEndOfSong(final Player player, int songDuration){
		//player.sendMessage("This song will end in " + ((songDuration+ Settings.getDelayBetweenSongs())*20) + "ticks = " + (((songDuration+ Settings.getDelayBetweenSongs())*20)/20) +" sec");
		Model.playerTaskID.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(AmbientSpout.callback, new Runnable(){
		
			@Override
			public void run() {
				//player.sendMessage("Song done!");
				MusicHandler.nextSong(player, false);
				
			}
							
		}, ((songDuration+ Settings.getDelayBetweenSongs())*20)) );
		
		
	}
	
	
	
	
	
	
	public static void stopMusic(final Player player, boolean areaChange){
		if (Model.playerTaskID.containsKey(player.getName()) && areaChange){
			Bukkit.getScheduler().cancelTask(Model.playerTaskID.get(player.getName()));
			//player.sendMessage("Starting fadeout");
			SpoutManager.getSoundManager().stopMusic((SpoutPlayer) player, true, Settings.fadeOutAreaChange*1000);
		}
		else if (Model.playerTaskID.containsKey(player.getName())){
			Bukkit.getScheduler().cancelTask(Model.playerTaskID.get(player.getName()));
			SpoutManager.getSoundManager().stopMusic((SpoutPlayer) player);
		}
		Model.playerTaskID.remove(player.getName());
		Model.updateGUI(player);
	}
	
	
	
	
	
	
	
	
	
	private static Song getNextSong(Player player){
		
		if (Model.playerCurrentSong.containsKey(player.getName())){
			String s = Model.playerCurrentSong.get(player.getName());
			if (Model.ambientMusic.size() == 1){
				return Model.ambientMusic.get(0);
			}
			
			int index;
			do {
				index = AmbientSpout.random.nextInt(Model.ambientMusic.size());
			}
			while (Model.ambientMusic.get(index).getUrl().equals(s));
			
			return Model.ambientMusic.get(index);
		}
		else return Model.ambientMusic.get(AmbientSpout.random.nextInt(Model.ambientMusic.size()));
		
		
	}
	
	
}
