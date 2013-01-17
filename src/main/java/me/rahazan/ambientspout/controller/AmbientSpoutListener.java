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
import me.rahazan.ambientspout.model.datatype.EffectLoop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class AmbientSpoutListener implements Listener {

	@EventHandler
	public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
		//System.out.println("SpoutCraftEnable! :D");
		
		if (Model.playerMusicEnabled.containsKey(event.getPlayer().getName())){
			if (Model.playerMusicEnabled.get(event.getPlayer().getName())){
				MusicHandler.nextSong(event.getPlayer(), false);
			}
		}
		else {
			if (Settings.defaultOn){
				MusicHandler.nextSong(event.getPlayer(), false);
			}
			Model.playerMusicEnabled.put(event.getPlayer().getName(), Settings.defaultOn);
		}
		
		
		
		if (event.getPlayer().getName().equals("Rahazan") && AmbientSpout.callback.getConfig().getBoolean("Settings.DevMessage", true)) {
			
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN + "Believe it or not, the developer of " 
				+ ChatColor.GOLD + "AmbientSpout" + ChatColor.DARK_GREEN + " just joined! ");
		
			//Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Rahazan" + ChatColor.DARK_GREEN + " wooh :3!");
		}
		
		if (Settings.effectLoopsEnabled) {
			for (EffectLoop el : Model.effectLoops) {
				if (el.isActive() && event.getPlayer().getWorld().equals(el.getLocation().getWorld()) && event.getPlayer().getLocation().distance(el.getLocation()) > (el.getDistance()))
				SpoutManager.getSoundManager().playCustomSoundEffect(AmbientSpout.callback, event.getPlayer(), el.getSong().getUrl(), Settings.notifyPlayerEffectLoopDownload, el.getLocation(), el.getDistance(), el.getVolumePercent());
			}
		}
		
		
		
		
		
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (event.getPlayer() instanceof SpoutPlayer) {
			if (Model.playerPopup.containsKey(event.getPlayer().getName())) {
				Model.playerPopup.remove(event.getPlayer().getName());
			}
		}
	}
	
	
	
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event){
		if (Settings.resetSongOnRespawn && event.getPlayer() instanceof SpoutPlayer && Model.playerMusicEnabled.containsKey(event.getPlayer().getName()) ? Model.playerMusicEnabled.get(event.getPlayer().getName()) : Settings.defaultOn)
			MusicHandler.nextSong(event.getPlayer(), true);

	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
		///if (Settings.effectLoopsEnabled) {
		//	((SpoutPlayer)event.getPlayer()).getMainScreen().attachPopupScreen(new EffectLoopGUI());
		//}
	}
	
}
