package com.guerrieri.mud.inventory;

/**
 * Represents an <code>Item</code> that can be used to attack <code>Character</code>s.
 * @author Mario Guerrieri
 *
 */
public class Weapon extends Item
{
	private int attack;
	private int damageDie;
	private int damageBonus;

	public Weapon(String[] names, String description, String detailedDesc, boolean visible, boolean container, int attack, int damageDie, int damageBonus)
	{
		super(names, description, detailedDesc, visible, container);
		this.attack = attack;
		this.damageDie = damageDie;
	}

	public int attack(int AC)
	{
		int toHit = (int)(Math.random() * 20 + 1) + this.attack;
		if(toHit >= AC)
		{
			return (int)(Math.random() * this.damageDie + 1) + this.damageBonus;
		}
		else return 0;
	}
}
