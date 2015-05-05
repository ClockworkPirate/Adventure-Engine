package com.guerrieri.mud.inventory;

import java.util.ArrayList;

import com.guerrieri.mud.AbstractObject;
import com.guerrieri.mud.GameObject;
import com.guerrieri.mud.character.Player;
import com.guerrieri.mud.control.Verb;

/**
 * Represents an item in the game world.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Item extends AbstractObject
{
	private ArrayList<Item>	contents;
	private boolean isContainer;

	/**
	 * Initializes the <code>Item</code> with the name/short description <code>name</code> and long description <code>description</code>.
	 *
	 * @param names The <code>Item</code>'s name/short description.
	 * @param description The <code>Item</code>'s long description.
	 */
	public Item(String[] names, String description, String detailedDesc, boolean visible, boolean container)
	{
		super(names, description, detailedDesc, visible);
		this.contents = new ArrayList<Item>();
		this.isContainer = container;
	}

	public void addItem(Item toAdd)
	{
		this.contents.add(toAdd);
	}

	/**
	 * Causes the <code>GameObject</code> to act as appropriate for the action command specified by <code>action</code>.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @param actor The <code>Player</code> performing this action.
	 * @return Any output from the action, <code>null</code> if action was not performed.
	 */
	public String act(Verb action, Player actor)
	{
		if(action.equals(new Verb("take", "what")))
		{
			this.location.removeObject(this.names[0]);
			actor.takeItem(this);
			return "You take the " + this.names[0].toLowerCase() + ".";
		}
		else if(action.equals(new Verb("open", "what")))
		{
			if(this.contents.size() > 0)
			{
				StringBuilder out = new StringBuilder();
				out.append("You take\n");
				for(int i = 0; i < this.contents.size(); i ++)
				{
					out.append(this.contents.get(i).names[0] + "\n");
					actor.takeItem(this.contents.get(i));
					this.contents.remove(i);
				}
				out.append("from the " + this.names[0].toLowerCase());
				return out.toString();
			}
			else if(this.isContainer) return "The " + this.names[0].toLowerCase() + " is empty.";
			else return "You can't open a " + this.names[0].toLowerCase() + ".";
		}
		else if(action.equals(new Verb("drop", "what")))
		{
			this.moveTo(actor.getLocation());
			actor.dropItem(this);
			return "You drop the " + this.names[0].toLowerCase() + ".";
		}
		else return super.act(action, actor);
	}

	/**
	 * Causes the <code>GameObject</code> to act as appropriate for the action command specified by <code>action</code>.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @param actor The <code>Player</code> performing this action.
	 * @param secondaryObject Another <code>GameObject</code> with which the action is being performed.
	 * @return Any output from the action, <code>null</code> if an action was not performed.
	 */
	public String act(Verb action, Player actor, GameObject secondaryObject)
	{
		return this.act(action, actor);
	}

	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		out.add(new Verb("take", "what"));
		out.add(new Verb("pick up", "take", "what"));
		out.add(new Verb("grab", "take", "what"));
		out.add(new Verb("drop", "what"));
		out.add(new Verb("leave", "drop", "what"));
		for(Verb v : super.getVerbs()) out.add(v);
		return out.toArray(new Verb[0]);
	}

	public boolean equals(Object other)
	{
		return other instanceof Item && this == other;
	}
}
