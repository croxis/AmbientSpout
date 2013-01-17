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
package me.rahazan.ambientspout;

import java.util.Random;
import java.util.logging.Logger;
import me.rahazan.ambientspout.controller.AmbientSpoutKeyListener;
import me.rahazan.ambientspout.controller.AmbientSpoutListener;
import me.rahazan.ambientspout.controller.AmbientSpoutScreenListener;
import me.rahazan.ambientspout.controller.FileHandler;
import me.rahazan.ambientspout.metrics.MetricsHandler;
import me.rahazan.ambientspout.model.Model;
import me.rahazan.ambientspout.model.Settings;
import me.rahazan.ambientspout.model.datatype.EffectLoop;
import me.rahazan.ambientspout.resources.ResourcesMain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;


public class AmbientSpout extends JavaPlugin {

	public static Random random = new Random();
	public static AmbientSpout callback;
	public static Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onDisable() {
		Model.savePlayerSettings();
	}
	
	@Override
	public void onEnable() {
		callback = this;
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		Settings.loadConfig();
		saveConfig();
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new AmbientSpoutListener(), this);
		pm.registerEvents(new AmbientSpoutScreenListener(), this);
		
		
		if (Settings.enableWebServer) 
			webServerInit();
		
		FileHandler.loadSongs("Ambient", Model.ambientMusic);
		FileHandler.loadSongs("EffectLoops", Model.effectLoopsMusic);
		
		Model.loadPlayerSettings();
		
		log.info("[AmbientSpout] Found AmbientMusic: \n" + Model.ambientMusic.toString());
		log.info("[AmbientSpout] Found EffectLoopSounds: \n" + Model.effectLoopsMusic.toString());
		
		if (Settings.effectLoopsEnabled) {
			FileHandler.loadEffectLoops();
			System.out.println("[AmbientSpout] Starting EffectLoops!");
			for (EffectLoop e : Model.effectLoops) {
				if (e.isActive())
					e.startEffect();
			}
			FileHandler.saveEffectLoops();
		}
		
		
		
		SpoutManager.getKeyBindingManager().registerBinding("AmbientSpout_MainPopup", Keyboard.KEY_EQUALS, "Opens the AmbientSpout main screen.", new AmbientSpoutKeyListener(), this);
		
		MetricsHandler.metricsGo();
		System.out.println("[AmbientSpout] All systems GO!");
	}
	
	
	
	
	
	
	
	private void webServerInit(){
		//debug("Starting Resources server for AmbientSpout.");
		ResourcesMain resMain = new ResourcesMain(this);
		resMain.startResources();
		try {
			Model.ambientMusic = resMain.getSongList("Ambient");
			Model.effectLoopsMusic = resMain.getSongList("EffectLoops");
			
			//debug(resourcesMain.getSubFolders("PubArea" + File.separator + "asasass" + File.separator).toString());
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
}
