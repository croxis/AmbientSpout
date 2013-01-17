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
package me.rahazan.ambientspout.metrics;

import java.io.IOException;

import org.bukkit.Bukkit;

import me.rahazan.ambientspout.AmbientSpout;
import me.rahazan.ambientspout.metrics.Metrics.Graph;
import me.rahazan.ambientspout.model.Model;
import me.rahazan.ambientspout.model.Settings;

public class MetricsHandler {

	public static void metricsGo(){
		try {
		    Metrics metrics = new Metrics(AmbientSpout.callback);
		    
		    
		    metrics.addCustomData(new Metrics.Plotter("Total Ambient Song Count") {

		        @Override
		        public int getValue() {
		            return Model.ambientMusic.size();
		        }

		    });
		    
		    metrics.addCustomData(new Metrics.Plotter("Total EffectLoop Count") {

		        @Override
		        public int getValue() {
		            return Model.effectLoops.size();
		        }

		    });
		    
		 // Construct a graph, which can be immediately used and considered as valid
		    Graph graph = metrics.createGraph("WebServer Usage");

		    // Diamond sword
		    graph.addPlotter(new Metrics.Plotter("Enabled") {

		            @Override
		            public int getValue() {
		                    return Settings.enableWebServer ? 1 : 0;
		            }

		    });

		    // Iron sword
		    graph.addPlotter(new Metrics.Plotter("Disabled") {

		            @Override
		            public int getValue() {
		                    return Settings.enableWebServer ? 0 : 1;
		            }

		    });
		    if (Settings.enableWebServer && !AmbientSpout.callback.getConfig().getString("HostAddress").equals("127.0.0.1"))  {
		    	Graph graph2 = metrics.createGraph("Most Populated Server w/ WebServer Enabled");
		    	graph2.addPlotter(new Metrics.Plotter( AmbientSpout.callback.getConfig().getString("HostAddress") ) {
		    		
		    		@Override
		            public int getValue() {
		                    return Bukkit.getServer().getOnlinePlayers().length;
		            }
		    	});
		    }
		  
		    
		    
		    metrics.start();
		    
		} catch (IOException e) {
			System.out.println("Failed to submit stats to Metrics for AmbientSpout :< Nothing to worry about..");
		    // Failed to submit the stats :-(
		}
		
		
	}
}
