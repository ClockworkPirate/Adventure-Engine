package com.guerrieri.mud.ui;

/**
 * Defines an object that can receive user input and display text output.
 * @author Mario Guerrieri
 *
 */
public interface UserInterface
{
	public void display(String displayText);
	public String getInput();
}