package com.guerrieri.mud.inventory;

import com.guerrieri.mud.navigation.Path;

/**
 * Represents an <code>Item</code> that can make impassable <code>Path</code>s passable.
 * @author Mario Guerrieri
 *
 */
public class Key extends Item
{
	private Path opens;

	public Key(String[] names, String description, String detailedDesc, boolean visible, boolean container, Path opens)
	{
		super(names, description, detailedDesc, visible, container);
		this.opens = opens;
	}

	public Path getPath()
	{
		return this.opens;
	}
}
