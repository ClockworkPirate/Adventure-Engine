package com.guerrieri.mud.control;

/**
 * An interface for objects that can parse text commands.
 * @author Mario Guerreri
 *
 */
public interface Parser
{
	public String parseCommand(String command);
}
