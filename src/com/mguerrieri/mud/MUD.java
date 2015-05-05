package com.mguerrieri.mud;

import com.mguerrieri.mud.control.Adventure;
import com.mguerrieri.mud.io.AdventureLoader;

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
