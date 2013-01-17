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
import me.rahazan.ambientspout.view.MainGUI;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;

public class AmbientSpoutKeyListener implements BindingExecutionDelegate{


	@Override
	public void keyPressed(KeyBindingEvent arg0) {
		if (!Model.playerPopup.containsKey(arg0.getPlayer().getName())) {
			MainGUI pop = new MainGUI(arg0.getPlayer());
			Model.playerPopup.put(arg0.getPlayer().getName(), pop);
		}
			Model.playerPopup.get(arg0.getPlayer().getName()).setVisible(true);
			Model.playerPopup.get(arg0.getPlayer().getName()).setDirty(true);
		arg0.getPlayer().getMainScreen().attachPopupScreen(Model.playerPopup.get(arg0.getPlayer().getName()));
	}

	@Override
	public void keyReleased(KeyBindingEvent arg0) {
		
	}

}
