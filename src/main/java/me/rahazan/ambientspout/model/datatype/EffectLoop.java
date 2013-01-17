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
package me.rahazan.ambientspout.model.datatype;

import me.rahazan.ambientspout.AmbientSpout;
import me.rahazan.ambientspout.model.Settings;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;

public class EffectLoop implements Runnable{

	/**
	 * 
	 */
	private Song song;
	private String name;
	private String worldname;
	private int x, y, z;
	private transient Location location;
	private int volumePercent;
	private int distance;
	private transient int taskID;
	private boolean active = true;
	

	/**
	 * @param song
	 * @param name
	 * @param worldname
	 * @param x
	 * @param y
	 * @param z
	 * @param volumePercent
	 * @param distance
	 * @param active
	 */
	public EffectLoop(Song song, String name, String worldname, int x, int y,
			int z, int volumePercent, int distance, boolean active) {
		this.song = song;
		this.name = name;
		this.worldname = worldname;
		this.x = x;
		this.y = y;
		this.z = z;
		this.volumePercent = volumePercent;
		this.distance = distance;
		this.active = active;
		location = new Location(Bukkit.getWorld(worldname), x, y, z);
	}

	public void startEffect(){
		if (active)
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AmbientSpout.callback, this, 0, song.getDuration()*20+10);
	}

	@Override
	public void run() {
		//System.out.println("Starting effectloop " + name);
		SpoutManager.getSoundManager().playGlobalCustomSoundEffect(AmbientSpout.callback, song.getUrl(), Settings.notifyPlayerEffectLoopDownload, location, distance, volumePercent);
	}
	
	public void stopEffect(){
		active = false;
			Bukkit.getScheduler().cancelTask(taskID);
	}
	
	public void changePosition(Location newLocation){
		this.location = newLocation;
		x = newLocation.getBlockX();
		y = newLocation.getBlockY();
		z = newLocation.getBlockZ();
		worldname = newLocation.getWorld().getName();
	}

	/**
	 * @param song the song to set
	 */
	public void setSong(Song song) {
		this.song = song;
	}

	/**
	 * @param volumePercent the volumePercent to set
	 */
	public void setVolumePercent(int volumePercent) {
		this.volumePercent = volumePercent;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString(){ 
		return name + " " + song.toString() + " " + x + " " + y + " " + z + " " + worldname + " " + volumePercent + " " + distance + " " + active;
	}
	
	public void printToLog(){
		AmbientSpout.log.info("EFFECTLOOP: " + name + " song:" + song.toString() + " xyz: " + x + " " + y + " " + z + 
				" world:" + worldname + " LocationToString: " + location.toString() + " volumePercent: " + volumePercent + " distance: " + distance + " active: " + active);
	}
	
	
	public static EffectLoop buildFromString(String s){
		String[] a = s.split(" ");//x = a[3]
		return new EffectLoop(Song.buildFromString(a[1] + " " + a[2]), a[0], a[6], Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[7]), Integer.parseInt(a[8]), Boolean.parseBoolean(a[9]));
		
	}

	public String getName() {
		return name;
	}
	public Song getSong(){
		return song;
	}
	public Location getLocation(){
		return location;
	}
	public int getVolumePercent(){
		return volumePercent;
	}
	public int getDistance(){
		return distance;
	}
	public boolean isActive(){
		return active;
	}
}
