package com.guerrieri.mud;

import java.util.ArrayList;
import java.util.Hashtable;

import com.guerrieri.mud.character.Player;
import com.guerrieri.mud.control.Noun;
import com.guerrieri.mud.control.Verb;
import com.guerrieri.mud.navigation.Room;

/**
 * An abstract class representing a basic <code>GameObject</code>.
 *
 * @author Mario Guerrieri
 */
public abstract class AbstractObject implements GameObject
{
	protected String[] names;
	protected String desc;
	protected String detailedDesc;
	protected boolean visible;

	protected Hashtable<Verb, String> actionResponses;

	protected Room location;

	protected AbstractObject(String[] names, String description, String detailedDesc, boolean visible)
	{
		this.names = names;
		this.desc = description;
		this.detailedDesc = detailedDesc;
		this.visible = visible;

		this.actionResponses = new Hashtable<Verb, String>();
	}

	/**
	 * Returns the <code>AbstractObject</code>'s detailed description, which will be shown when the <code>AbstractObject</code> is examined closely.
	 *
	 * @return the <code>AbstractObject</code>'s detailed description, which will be shown when the <code>AbstractObject</code> is examined closely.
	 */
	public String getDetailedDescription()
	{
		return this.detailedDesc;
	}

	/**
	 * Returns the <code>AbstractObject</code>'s long description.
	 *
	 * @return The <code>AbstractObject</code>'s long description.
	 */
	public String getDescription()
	{
		return this.desc;
	}

	/**
	 * Returns the <code>AbstractObject</code>'s visibility.
	 *
	 * @return Whether the <code>AbstractObject</code> is visible or not.
	 */
	public boolean isVisible()
	{
		return this.visible;
	}

	/**
	 * Causes the <code>AbstractObject</code> to act as appropriate for the action command specified by <code>action</code>.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @param actor The <code>Player</code> performing this action.
	 * @return Any output from the action, <code>null</code> if action was not performed.
	 */
	public String act(Verb action, Player actor)
	{
		if(action.equals(new Verb("examine", "what")))
		{
			if(this.getDetailedDescription().length() > 0) return this.getDetailedDescription();
			else return "The " + this.getNouns()[0].getName() + " is rather nondescript.";
		}
		else if(this.actionResponses.containsKey(action))
		{
			return this.actionResponses.get(action);
		}
		else return "You can't " + action.getName() + " a " + this.getNouns()[0].getName().toLowerCase() + ".";
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

	/**
	 * Causes the <code>AbstractObject</code> to move to the specified <code>Room</code>.
	 *
	 * @param destination The <code>Room</code> for the <code>AbstractObject</code> to move to.
	 */
	public void moveTo(Room destination)
	{
		if(this.location != null)
		{
			this.location.removeObject(this.names[0]);
			this.location = null;
		}
		if(destination != null)
		{
			destination.addObject(this.names[0], this);
		}
		this.location = destination;
	}

	/**
	 * Returns this <code>AbstractObject</code>'s current location.
	 *
	 * @return This <code>AbstractObject</code>'s current location.
	 */
	public Room getLocation()
	{
		return this.location;
	}

	/**
	 * Returns the names that could refer to this <code>AbstractObject</code>.
	 *
	 * @return An array of <code>Noun</code>s containing one generated from each of this <code>AbstractObject</code>'s names.
	 */
	public Noun[] getNouns()
	{
		Noun[] out = new Noun[this.names.length];
		for(int i = 0; i < this.names.length; i ++)
		{
			out[i] = new Noun(this.names[i], this);
		}
		return out;
	}

	/**
	 * Returns the <code>Verb</code>s whose actions can be performed by this <code>GameObject</code>.
	 *
	 * @return The <code>Verb</code>s whose actions can be performed by this <code>GameObject</code>.
	 */
	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		for(Verb v : this.actionResponses.keySet())
		{
			out.add(v);
		}
		out.add(new Verb("examine", "what"));
		return out.toArray(new Verb[0]);
	}
}
