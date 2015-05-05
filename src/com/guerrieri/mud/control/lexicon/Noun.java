package com.guerrieri.mud.control.lexicon;

import com.guerrieri.mud.GameObject;

/**
 * Represents a name by which a <code>GameObject</code> may be called.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Noun
{
	private String name;
	private GameObject ref;

	/**
	 * Initializes a <code>Noun</code> object named <code>name</code>, referring to <code>GameObject</code> <code>ref</code>.
	 *
	 * @param name The <code>Noun</code>'s name.
	 * @param ref The <code>GameObject</code> to which the <code>Noun</code> refers.
	 */
	public Noun(String name, GameObject ref)
	{
		this.name = name.toLowerCase();
		this.ref = ref;
	}

	/**
	 * Returns the <code>Noun</code>'s name.
	 *
	 * @return The <code>Noun</code>'s name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Returns the <code>GameObject</code> to which the <code>Noun</code> refers.
	 *
	 * @return The <code>GameObject</code> to which the <code>Noun</code> refers.
	 */
	public GameObject getRef()
	{
		return ref;
	}

	/**
	 * Determines if two <code>Noun</code>s are equal by comparing the <code>GameObject</code>s that they refer to (The <code>GameObject</code>s must actually be the same object, so memory addresses are compared).
	 *
	 * @param other The <code>Noun</code> to be compared.
	 * @return <code>true</code> if the <code>Noun</code>s are equal, <code>false</code> otherwise.
	 */
	public boolean equals(Noun other)
	{
		return this.ref == other.ref;
	}

	public String toString()
	{
		return this.name;
	}
}
