package com.mguerrieri.mud.control;

/**
 * Represents an action which a <code>GameObject</code> may perform.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public class Verb
{
	private String name;
	private String synonym;
	private String interrogative;

	/**
	 * Initializes a <code>Verb</code> object named <code>name</code>.
	 *
	 * @param name The <code>Verb</code>'s name.
	 */
	public Verb(String name, String interrogative)
	{
		this.name = name.toLowerCase();
		this.synonym = name.toLowerCase();
		this.interrogative = interrogative.toLowerCase();
	}

	/**
	 * Initializes a <code>Verb</code> object named <code>name</code>, synonymous with the <code>Verb</code> <code>synonym</code>.
	 *
	 * @param name The <code>Noun</code>'s name.
	 * @param synonym The primary <code>Verb</code> with which this <code>Verb</code> is synonymous.
	 */
	public Verb(String name, String synonym, String interrogative)
	{
		this(name, interrogative);
		this.synonym = synonym;
	}

	/**
	 * Returns the <code>Verb</code>'s name.
	 *
	 * @return The <code>Verb</code>'s name.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the primary verb that this <code>Verb</code> is synonymous to.
	 *
	 * @return The primary verb that this <code>Verb</code> is synonymous to.
	 */
	public String getSynonym()
	{
		return this.synonym;
	}

	/**
	 * Returns the interrogative word that should be used with this <code>Verb</code>.
	 *
	 * @return the interrogative word that should be used with this <code>Verb</code>.
	 */
	public String getInterrogative()
	{
		return this.interrogative;
	}

	/**
	 * Determines if two <code>Verb</code>s are equal by comparing their synonyms' names.
	 *
	 * @param other The <code>Verb</code> to be compared.
	 * @return <code>true</code> if the <code>Verb</code>s are equal, <code>false</code> otherwise.
	 */
	public boolean equals(Object other)
	{
		if(other instanceof Verb) return this.synonym.equals(((Verb)other).synonym) || this.name.equals(((Verb)other).name);
		else return false;
	}

	public String toString()
	{
		return this.name;
	}
}
