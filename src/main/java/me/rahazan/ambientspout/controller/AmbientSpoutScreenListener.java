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

import me.rahazan.ambientspout.model.Model;
import me.rahazan.ambientspout.view.EffectLoopGUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class AmbientSpoutScreenListener implements Listener{
	
	
	@EventHandler
    public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer(); //The SpoutPlayer that pressed the button.
		   
        if (!(Model.playerPopup.containsKey(player.getName()))){ //Have we NOT ever added any information in the playerPopup hashmap linked with this player?
            return;
        }
   
        if (Model.playerPopup.get(player.getName()) != event.getButton().getScreen()){ //Is the screen that the button was pressed on NOT the screen that we once saved to the hashmap?
            return; // If this is the case, it's probably a screen that is part of MineCraft or of another plugin.
        }
        
       if ( event.getButton().getText().equals("Next Song") || event.getButton().getText().equals("Music On")){
    	   Model.playerMusicEnabled.put(player.getName(), true);
    	   MusicHandler.nextSong(event.getPlayer(), false);
    	   Model.playerPopup.get(player.getName()).updateMusicEnabled();
    	   Model.savePlayerSettings();
    	   
       }
       else if (event.getButton().getText().equals("Music Off")){
    	 
    	   Model.playerMusicEnabled.put(player.getName(), false);
    	   MusicHandler.stopMusic(player, false);
    	   Model.playerPopup.get(player.getName()).updateMusicEnabled();
    	   Model.savePlayerSettings();
       }
       
       else if (event.getButton().getText().equals("Manage EffectLoops")){
    	  player.getMainScreen().closePopup();
    	  player.getMainScreen().attachPopupScreen(new EffectLoopGUI());
       }
    }
	
}
