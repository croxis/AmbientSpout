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


public class Song{

	/**
	 * 
	 */
	private String url;
	private int duration;
	
	public Song(String url, int duration){
		this.url = url;
		this.duration = duration;
	}
	
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public String toString(){
		return duration + " " + url.replace(" ", "%20");
	}
	
	/**
	 * @param s duration + " " + url
	 * @return Song built from the string.
	 */
	public static Song buildFromString(String s){
		return new Song (s.split(" ")[1], Integer.parseInt(s.split(" ")[0]));
	}
}
