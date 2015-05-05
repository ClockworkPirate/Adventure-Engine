package com.guerrieri.mud.control;

import com.guerrieri.mud.character.Player;
import com.guerrieri.mud.character.Status;
import com.guerrieri.mud.character.Status.StatusType;
import com.guerrieri.mud.navigation.Room;

/**
 * Takes input from the player and various objects, updates those objects accordingly
 *
 * @author Mario Guerrieri
 *
 */
public class Engine implements Parser
{
	private Player player;
	private Lexicon lexicon;
	private boolean running;

	public Engine(Player player)
	{
		this.lexicon = new Lexicon();
		this.player = player;
		this.running = true;
	}

	public String parseCommand(String command)
	{
		StringBuilder out = new StringBuilder();

		if(command.toLowerCase().equals("quit"))
		{
			this.running = false;
			out.append("");
		}
		else if(command.toLowerCase().equals("help"))
		{
			out.append(this.lexicon.toString() + "\nlook\nlook around\ninventory\nquit");
		}
		else if(command.toLowerCase().equals("look") || command.toLowerCase().equals("look around"))
		{
			out.append(this.player.getLocation().getDescription());
		}
		else if(command.toLowerCase().equals("inventory"))
		{
			out.append(this.player.readInventory());
		}
		else
		{
			Noun primaryNoun = null;
			Noun secondaryNoun = null;
			Verb verb = null;

			Command cmd = new Command(command);

			for(int i = cmd.length() - 1; i >= 0; i --)//for(int i = 0; i < cmd.length() - 1; i ++)
			{
				String[] cmds = cmd.split(i);

				for(String s : cmds)
				{
					if(this.lexicon.containsNoun(s) && primaryNoun == null)
					{
						primaryNoun = this.lexicon.getNoun(s);
					}
					else if(this.lexicon.containsNoun(s) && secondaryNoun == null)
					{
						secondaryNoun = this.lexicon.getNoun(s);
					}
					else if(this.lexicon.containsVerb(s))
					{
						verb = this.lexicon.getVerb(s);
					}
				}
			}

			if(primaryNoun != null && secondaryNoun != null && verb != null) out.append(primaryNoun.getRef().act(verb, this.player, secondaryNoun.getRef()));
			else if(primaryNoun != null && verb != null) out.append(primaryNoun.getRef().act(verb, this.player));
			else if(primaryNoun == null && secondaryNoun == null && verb != null)
			{
				String temp = verb.getName();
				out.append(Character.toUpperCase(temp.charAt(0)) + temp.substring(1) + " " + verb.getInterrogative() + "?");
			}
			else if(primaryNoun != null && verb == null) out.append("Do what with the " + primaryNoun.getName() + "?");
			else if(primaryNoun != null && secondaryNoun != null && verb == null) out.append("Do what with the " + primaryNoun.getName() + " and the " + secondaryNoun.getName() + "?");
			else out.append("I don't know what '" + command + "' means.");
		}
		return out.toString();
	}

	public void parseStatus()
	{
		for(Status s : this.player.getState())
		{
			if(s.getUnlockType().equals(StatusType.SET_IMPASSABLE))
			{
				s.getRef().setImpassable();
			}
			else if(s.getUnlockType().equals(StatusType.SET_INVISIBLE))
			{
				s.getRef().setInvisible();
			}
			else if(s.getUnlockType().equals(StatusType.SET_PASSABLE))
			{
				s.getRef().setPassable();
			}
			else if(s.getUnlockType().equals(StatusType.SET_VISIBLE))
			{
				s.getRef().setVisible();
			}
		}
	}

	public void setContext(Room newRoom)
	{
		this.lexicon = this.player.getContext();
		this.lexicon.addAll(newRoom.getLexicon());
	}

	public boolean isRunning()
	{
		return this.running;
	}
}
