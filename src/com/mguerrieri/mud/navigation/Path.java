package com.mguerrieri.mud.navigation;

import java.util.ArrayList;

import com.mguerrieri.mud.AbstractObject;
import com.mguerrieri.mud.GameObject;
import com.mguerrieri.mud.character.Player;
import com.mguerrieri.mud.control.Verb;
import com.mguerrieri.mud.inventory.Key;
import com.mguerrieri.mud.io.AdventureLoader;

/**
 * The <code>Path</code> class represents passages between areas. In practice, they link <code>Room</code> objects, allowing the player to move between them.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Path extends AbstractObject
{
	private Room destination;
	private int destID;
	private boolean passable;
	private boolean destVisible;
	private String destDesc;
	private String moveOutput;
	private String lockedOutput;

	public Path(String[] names, String description, String detailedDesc, String moveText, String lockedText, boolean visible, Room destination, String destDesc, boolean passable, boolean destVisible)
	{
		super(names, description, detailedDesc, visible);
		this.destination = destination;
		this.passable = passable;
		this.destVisible = destVisible;
		this.destDesc = destDesc;
		this.moveOutput = moveText;
		this.lockedOutput = lockedText;
	}

	public Path(String[] names, String description, String detailedDesc, String moveText, String lockedText, boolean visible, int destRoomID, String destDesc, boolean passable, boolean destVisible)
	{
		super(names, description, detailedDesc, visible);
		this.destination = null;
		this.destID = destRoomID;
		this.passable = passable;
		this.destVisible = destVisible;
		this.destDesc = destDesc;
		this.moveOutput = moveText;
		this.lockedOutput = lockedText;
	}

	public String getDetailedDescription()
	{
		if(this.destVisible && this.detailedDesc.length() > 0)
		{
			return this.detailedDesc + " " + this.destDesc;
		}
		else if(this.destVisible)
		{
			return this.destDesc;
		}
		else return this.detailedDesc;
	}

	/**
	 * Returns the output associated with a given action on this object.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @return the output associated with a given action on this object.
	 */
	public String act(Verb action, Player actor)
	{
		if(this.destination == null)
		{
			this.destination = new AdventureLoader(this.destID).loadWorld();
		}

		if(action.equals(new Verb("move", "where")))
		{
			if(this.passable)
			{
				actor.moveTo(this.destination);
				actor.setStateChanged();
				return this.moveOutput;
			}
			else return this.lockedOutput;
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
		if(action.equals(new Verb("move", "where")) && secondaryObject instanceof Key && ((Key)secondaryObject).getPath() == this)
		{
			this.passable = true;
			return this.act(action, actor);
		}
		else return this.act(action, actor);
	}

	public void setPassable()
	{
		this.passable = true;
	}
	public void setImpassable()
	{
		this.passable = false;
	}

	public void setVisible()
	{
		this.visible = true;
	}
	public void setInvisible()
	{
		this.visible = false;
	}

	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		out.add(new Verb("move", "where"));
		out.add(new Verb("go", "move", "where"));
		out.add(new Verb("open", "move", "what"));
		for(Verb v : super.getVerbs()) out.add(v);
		return out.toArray(new Verb[0]);
	}

	public void unLoad()
	{
		new AdventureLoader(this.destID).saveWorld(this.destination);
		this.destination = null;
	}
}
