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
package me.rahazan.ambientspout.resources;

import java.io.File;


public class Resources {
	public static String get(String resource) throws ResourceDoesNotExist {
		if(!doesResoruceExists(resource))
			throw new ResourceDoesNotExist(resource);
		
		String s = "http://"+ResourcesMain.getCallback().plugin.getConfig().getString("HostAddress")+":"+ResourcesMain.getCallback().plugin.getConfig().getInt("HostPort")+"/"+resource;
		return s.replace(File.separator, "/");
		
		
	}


	private static boolean doesResoruceExists(String resource) {
		resource = resource.replace("/", File.separator);
		return new File(ResourcesMain.getCallback().getResourceDir().getAbsolutePath()+File.separator+resource).exists();
	}
}
