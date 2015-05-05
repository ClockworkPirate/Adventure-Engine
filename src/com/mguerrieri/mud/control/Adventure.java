package com.mguerrieri.mud.control;

import com.mguerrieri.mud.character.Player;
import com.mguerrieri.mud.navigation.Room;
import com.mguerrieri.mud.ui.Console;
import com.mguerrieri.mud.ui.UserInterface;

/**
 * Represents an instance of the game, in a given state.
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Adventure
{
	private Player player;
	private Engine engine;
	private UserInterface userInterface;
	private Room currentRoom;

	private String introText;

	/**
	 * Initializes a new <code>Adventure</code> from the data file at <code>dataPath</code>.
	 *
	 * @param dataPath The file system path to a data file
	 */
	public Adventure(String dataPath)
	{
		this.player = new Player(new String[]{"You"}, "A nondescript sentient being stands nearby.", true, 10, 15);
		this.engine = new Engine(this.player);
		this.userInterface = new Console();

		//TODO: Get the introduction text

		this.userInterface.display(this.introText);

		//TODO: Get the first Room from its file, put the player in it
	}

	public Adventure(Room room, String introText)
	{
		this.player = new Player(new String[]{"You"}, "A nondescript sentient being stands nearby.", true, 10, 15);
		this.currentRoom = room;
		this.engine = new Engine(this.player);
		this.userInterface = new Console();

		this.introText = introText;
		this.userInterface.display(introText + "\n");
	}

	/**
	 * Runs the <code>Adventure</code>!
	 */
	public void run()
	{
		this.player.moveTo(this.currentRoom);
		this.userInterface.display(this.engine.parseCommand("look"));
		this.engine.setContext(this.player.getLocation());

		while(this.engine.isRunning())
		{
			StringBuilder display = new StringBuilder(this.engine.parseCommand(this.userInterface.getInput()));
			if(this.player.stateChanged())
			{
				display.append("\n" + this.engine.parseCommand("look"));
				this.player.unsetStateChanged();
			}
			this.userInterface.display(display.toString());
			this.engine.setContext(this.player.getLocation());
			this.engine.parseStatus();
		}
	}
}
