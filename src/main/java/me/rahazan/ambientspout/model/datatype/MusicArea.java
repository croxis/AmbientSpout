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

import java.util.ArrayList;


import org.bukkit.Location;

public class MusicArea {

	@SuppressWarnings("unused")
	private ArrayList<Song> playList = new ArrayList<Song>();
	private Location loc1;
	private Location loc2;
	@SuppressWarnings("unused")
	private int priority;
	
	/**
	 * @param playList
	 * @param priority
	 */
	public MusicArea(ArrayList<Song> playList, int priority) {
		this.playList = playList;
		this.priority = priority;
	}
	
	public boolean isInArea(Location loc){
		return (((loc.getBlockX() <= loc1.getBlockX() && loc.getBlockX() >= loc2.getBlockX())) || ((loc.getBlockX() >= loc1.getBlockX() && loc.getBlockX() <= loc2.getBlockX())) 
		&& ((loc.getBlockY() <= loc1.getBlockY() && loc.getBlockY() >= loc2.getBlockY())) || ((loc.getBlockY() >= loc1.getBlockY() && loc.getBlockY() <= loc2.getBlockY())) 
		&& ((loc.getBlockZ() <= loc1.getBlockZ() && loc.getBlockZ() >= loc2.getBlockZ())) || ((loc.getBlockZ() >= loc1.getBlockZ() && loc.getBlockZ() <= loc2.getBlockZ())));
	}
	
}
