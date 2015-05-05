package com.mguerrieri.mud;

import com.mguerrieri.mud.character.Player;
import com.mguerrieri.mud.control.Noun;
import com.mguerrieri.mud.control.Verb;
import com.mguerrieri.mud.navigation.Room;

/**
 * An object of a class that implements this interface can be described and interacted with.
 *
 * @author Mario Guerrieri
 * @version 0.01
 */
public interface GameObject
{
	/**
	 * Returns the <code>GameObject</code>'s detailed description, which will be shown when the <code>GameObject</code> is examined closely.
	 *
	 * @return the <code>GameObject</code>'s detailed description, which will be shown when the <code>GameObject</code> is examined closely.
	 */
	public String getDetailedDescription();

	/**
	 * Returns the <code>GameObject</code>'s long description.
	 *
	 * @return The <code>GameObject</code>'s long description.
	 */
	public String getDescription();

	/**
	 * Returns whether the <code>GameObject</code> is visible to the player or not.
	 *
	 * @return Whether the <code>GameObject</code> is visible to the player or not.
	 */
	public boolean isVisible();

	/**
	 * Causes the <code>GameObject</code> to act as appropriate for the action command specified by <code>action</code>.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @param actor The <code>Player</code> performing this action.
	 * @return Any output from the action, <code>null</code> if action was not performed.
	 */
	public String act(Verb action, Player actor);

	/**
	 * Causes the <code>GameObject</code> to act as appropriate for the action command specified by <code>action</code>.
	 *
	 * @param action A <code>Verb</code> specifying the action to be taken.
	 * @param actor The <code>Player</code> performing this action.
	 * @param secondaryObject Another <code>GameObject</code> with which the action is being performed.
	 * @return Any output from the action, <code>null</code> if an action was not performed.
	 */
	public String act(Verb action, Player actor, GameObject secondaryObject);

	/**
	 * Causes the <code>GameObject</code> to move to the specified <code>Room</code>.
	 *
	 * @param destination The <code>Room</code> for the <code>GameObject</code> to move to.
	 */
	public void moveTo(Room destination);

	/**
	 * Returns the names that could refer to this <code>GameObject</code>.
	 *
	 * @return An array of <code>Noun</code>s containing one generated from each of this <code>GameObject</code>'s names.
	 */
	public Noun[] getNouns();

	/**
	 * Returns the <code>Verb</code>s whose actions can be performed by this <code>GameObject</code>.
	 *
	 * @return The <code>Verb</code>s whose actions can be performed by this <code>GameObject</code>.
	 */
	public Verb[] getVerbs();
}
