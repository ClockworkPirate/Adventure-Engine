package com.mguerrieri.mud.character;

import com.mguerrieri.mud.navigation.Path;

/**
 * A <code>Status</code> object represents a state that a player character can be in.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Status
{
	public enum StatusType
	{
		SET_VISIBLE,
		SET_INVISIBLE,
		SET_PASSABLE,
		SET_IMPASSABLE,
		NONE
	}

	private String name;
	private Path ref;
	private StatusType unlockType;

	public Status(Path ref, StatusType unlockType)
	{
		this.ref = ref;
		this.unlockType = unlockType;
	}

	/**
	 * Returns the name of the <code>Status</code>.
	 * @return the <code>Status</code>' name.
	 */
	public String getName()
	{
		return this.name;
	}

	public StatusType getUnlockType()
	{
		return this.unlockType;
	}

	public Path getRef()
	{
		return this.ref;
	}
}
