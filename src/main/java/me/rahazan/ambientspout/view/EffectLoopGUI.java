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
import me.rahazan.ambientspout.model.datatype.EffectLoop;

import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.ListWidgetItem;
import org.getspout.spoutapi.gui.WidgetAnchor;

public class EffectLoopGUI extends GenericPopup{

	
	
	private GenericListWidget list;

	public EffectLoopGUI(){
		list = new GenericListWidget();
		list.setAnchor(WidgetAnchor.TOP_LEFT);
		//list.shiftYPos(- list.getHeight() / 2); 
		list.setHeight(180).setWidth(240);
		
		for (EffectLoop el : Model.effectLoops) {
			list.addItem(buildItem(el));
		}
		this.attachWidget(AmbientSpout.callback, list);
		
	}
	
	private ListWidgetItem buildItem(EffectLoop e){
		return new ListWidgetItem(e.getName(), e.getSong().getUrl().split("/")[e.getSong().getUrl().split("/").length-1], "http://dl.dropbox.com/u/43693599/SoftRedux/effectloop.png");
	}
	
}
