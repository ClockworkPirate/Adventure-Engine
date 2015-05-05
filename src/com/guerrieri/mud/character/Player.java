package com.guerrieri.mud.character;

import java.util.ArrayList;

import com.guerrieri.mud.GameObject;
import com.guerrieri.mud.control.Lexicon;
import com.guerrieri.mud.control.Noun;
import com.guerrieri.mud.control.Verb;
import com.guerrieri.mud.inventory.Item;
import com.guerrieri.mud.navigation.Room;

/**
 * The <code>Player</code> class represents the player character.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Player extends Character
{
	/*private static final Lexicon baseContext = new Lexicon(new Noun[]{}, new Verb[]{
			new Verb("move", "where"),
			new Verb("go", "move", "where"),
			new Verb("take", "what"),
			new Verb("drop", "what"),
			new Verb("attack", "what"),
			new Verb("hit", "attack", "what"),
			new Verb("kill", "attack", "what"),
			new Verb("examine", "what"),
			new Verb("look at", "examine", "what"),
			new Verb("open", "move", "what")});*/

	private boolean stateChanged;

	public Player(String[] names, String description, boolean visible, int maxHP, int AC)
	{
		super(names, description, "Your vision slowly fades to black as you fall unconscious.", "", "", visible, maxHP, AC);
		this.visible = false;
		this.stateChanged = false;
	}

	public void moveTo(Room destination)
	{
		super.moveTo(destination);
	}

	public void setStateChanged()
	{
		this.stateChanged = true;
	}

	public void unsetStateChanged()
	{
		this.stateChanged = false;
	}

	public boolean stateChanged()
	{
		return this.stateChanged;
	}

	public Lexicon getContext()
	{
		Lexicon out = new Lexicon();
		out.addAll(this.getNouns());
		out.addAll(this.getVerbs());
		return out;
	}

	public Noun[] getNouns()
	{
		ArrayList<Noun> out = new ArrayList<Noun>();
		for(int i = 0; i < this.inventory.size(); i ++)
		{
			GameObject obj = this.inventory.get(i);
			for(int k = 0; k < obj.getNouns().length; k ++)
			{
				out.add(obj.getNouns()[k]);
			}
		}
		return out.toArray(new Noun[0]);
	}

	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		for(int i = 0; i < this.inventory.size(); i ++)
		{
			GameObject obj = this.inventory.get(i);
			for(int k = 0; k < obj.getVerbs().length; k ++)
			{
				out.add(obj.getVerbs()[k]);
			}
		}
		for(Verb v : super.getVerbs()) out.add(v);
		return out.toArray(new Verb[0]);
	}

	public String readInventory()
	{
		if(this.inventory.size() > 0)
		{
			StringBuilder out = new StringBuilder();
			out.append("You are carrying:\n");
			for(Item i : this.inventory)
			{
				out.append(i.getNouns()[0].getName() + "\n");
			}

			return out.toString().substring(0, out.toString().length() - 1);
		}
		else return "You aren't carrying anything.";
	}
}
