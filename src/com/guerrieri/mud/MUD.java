package com.guerrieri.mud;

import com.guerrieri.mud.control.Adventure;
import com.guerrieri.mud.io.AdventureLoader;

/**
 * Runs the adventure world contained in files "0" and "0InitText"
 *
 * @author Mario Guerrieri
 *
 */
public class MUD
{
	public static void main(String[] args)
	{
		AdventureLoader.init("./");

		AdventureLoader loader = new AdventureLoader(0);
		new Adventure(loader.loadWorld(), loader.loadInitText()).run();
	}
}
