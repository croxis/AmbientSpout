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
package me.rahazan.ambientspout.view;

import me.rahazan.ambientspout.AmbientSpout;
import me.rahazan.ambientspout.model.Model;
import me.rahazan.ambientspout.model.Settings;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;

public class MainGUI extends GenericPopup{

	private GenericButton nextSongButton;
	private GenericButton stopMusicButton;
	private GenericLabel currentSongLabel;
	private Player p;
	
	public MainGUI(Player p){
		this.p = p;
		nextSongButton = new GenericButton("Music On"); //The first button.
		nextSongButton.setAnchor(WidgetAnchor.CENTER_CENTER); //We are "sticking" it from the center_center, from there we will be shifting.
		nextSongButton.setWidth(90).setHeight(20); //Setting the width and height of the button.
		nextSongButton.shiftXPos(-90/2).shiftYPos(-nextSongButton.getHeight()/2+5); 
		
		stopMusicButton = new GenericButton("Music Off"); //The second button.
        stopMusicButton.setAnchor(WidgetAnchor.CENTER_CENTER);
        stopMusicButton.setWidth(90).setHeight(20);
        //stopMusicButton.shiftXPos(0).shiftYPos(stopMusicButton.getHeight()/2); 
        stopMusicButton.shiftXPos(-90/2).shiftYPos((int) (stopMusicButton.getHeight()*1.1)); 
        
        if (Settings.useBackgroundImage)
	        addBackground();
        
        currentSongLabel = new GenericLabel("");
        currentSongLabel.setAnchor(WidgetAnchor.CENTER_CENTER);
        currentSongLabel.setWidth(200).setHeight(10);
        currentSongLabel.shiftXPos(-50).shiftYPos(50); 
        currentSongLabel.setTextColor(new Color(200,200,200));
        if (Settings.showCurrentSong) this.attachWidget(AmbientSpout.callback, currentSongLabel);
        
        
        if (!AmbientSpout.callback.getConfig().getBoolean("Settings.Donation", false)) 
        	addCredit();
        
        if (Model.playerMusicEnabled.containsKey(p.getName())){
			if (Model.playerMusicEnabled.get(p.getName())){
				nextSongButton.setText("Next Song");
				updateCurrentSong();
			}
			else{
				stopMusicButton.setVisible(false);
			}
		}
        
        if (p.hasPermission("ambientspout.admin") || p.isOp()) {
        	//System.out.println("Adding EffectLoopButton"); //TODO DebugOnly
        	
        	addEffectLoopButton();
        }
        
        this.attachWidget(AmbientSpout.callback, nextSongButton);
        this.attachWidget(AmbientSpout.callback, stopMusicButton);
	}
	
	public void updateMusicEnabled(){
		if (Model.playerMusicEnabled.containsKey(p.getName())){
			if (Model.playerMusicEnabled.get(p.getName())){
				nextSongButton.setText("Next Song");
				nextSongButton.setDirty(true);
				stopMusicButton.setVisible(true);
				stopMusicButton.setDirty(true);
				
			}
			else{
				nextSongButton.setText("Music On");
				nextSongButton.setDirty(true);
				stopMusicButton.setVisible(false);
				stopMusicButton.setDirty(true);
			}
		}
		this.setDirty(true);
		
	}
	
	
	public void updateCurrentSong(){
		currentSongLabel.setText(getCurrentSong());
	}
	
	private void addBackground(){
		 GenericTexture texture = new GenericTexture();	
			texture.setUrl(AmbientSpout.callback.getConfig().getString("Settings.BackgroundImage", "http://dl.dropbox.com/u/43693599/AmbientSpout.png"));
			texture.setAnchor(WidgetAnchor.TOP_LEFT);
			texture.setWidth(427).setHeight(240);
			texture.setPriority(RenderPriority.High);
			this.attachWidget(AmbientSpout.callback, texture);
	}
	private void addCredit(){
		GenericLabel label = new GenericLabel("AmbientSpout Free v" + AmbientSpout.callback.getDescription().getVersion() + " - By Rahazan.");
		label.setTextColor(new Color(0.4F, 0.4F, 0.4F));
		label.setAnchor(WidgetAnchor.TOP_LEFT);
		label.setWidth(180).setHeight(6);
		label.shiftXPos(1).shiftYPos(1);
		this.attachWidget(AmbientSpout.callback, label);
	}
	
	private void addEffectLoopButton(){
		GenericButton effectLoopButton = new GenericButton("Manage EffectLoops");
		effectLoopButton.setAnchor(WidgetAnchor.BOTTOM_LEFT); 
		effectLoopButton.setWidth(120).setHeight(20);
		effectLoopButton.shiftXPos(10).shiftYPos(-nextSongButton.getHeight()-10); 
		this.attachWidget(AmbientSpout.callback, effectLoopButton);
	}
	
	
	private String getCurrentSong(){
		if (Model.playerMusicEnabled.containsKey(p.getName())) {
			if (!Model.playerMusicEnabled.get(p.getName())) {
					return "";
				}
			}
			
		if (Model.playerCurrentSong.containsKey(p.getName())) {
			String s = Model.playerCurrentSong.get(p.getName());
			return "Current Song: " + s.split("/")[s.split("/").length-1];
		}
		return "";
	}
	
	
}
