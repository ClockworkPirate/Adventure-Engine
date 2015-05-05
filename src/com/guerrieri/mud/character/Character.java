package com.guerrieri.mud.character;

import java.util.ArrayList;
import java.util.Hashtable;

import com.guerrieri.mud.AbstractObject;
import com.guerrieri.mud.GameObject;
import com.guerrieri.mud.control.Noun;
import com.guerrieri.mud.control.Verb;
import com.guerrieri.mud.inventory.Item;
import com.guerrieri.mud.inventory.Weapon;

/**
 * Represents any mob in the game.
 *
 * @author Mario Guerrieri
 *
 */
public class Character extends AbstractObject
{
	protected String deathText;
	protected String encounterText;

	protected boolean encountered;

	protected int hitPoints;
	protected int maxHP;
	protected int AC;

	protected ArrayList<Item> inventory; //Items in the Character's backpack
	protected Weapon weapon;
	protected Hashtable<String, Status> state;

	public Character(String[] names, String description, String detailedDesc, String deathText, String encounterText, boolean visible, int maxHP, int AC)
	{
		super(names, description, detailedDesc, visible);

		this.encounterText = encounterText;
		this.deathText = deathText;
		this.encountered = false;

		this.weapon = null;
		this.inventory = new ArrayList<Item>();
		this.maxHP = maxHP;
		this.hitPoints = maxHP;
		this.AC = AC;

		this.state = new Hashtable<String, Status>();
	}

	/**
	 * Returns the <code>Character</code>'s long description.
	 *
	 * @return The <code>Character</code>'s long description.
	 */
	public String getDescription()
	{
		if(!this.encountered)
		{
			return this.encounterText;
		}
		else if(this.weapon != null)
		{
			return this.desc + " It is wielding a " + this.weapon.getNouns()[0].getName();
		}
		else return this.desc;

		/*if(this.wielding.size() == 0)
		{
			return this.desc; // + " It is unequipped.";
		}
		else if(this.wielding.size() == 1)
		{
			return this.desc + " It is equipped with " + this.wielding.get(0) + ".";
		}
		else
		{
			StringBuilder out = new StringBuilder();
			out.append(this.desc + " It is wielding ");
			for(int i = 0; i < this.wielding.size() - 1; i ++)
			{
				out.append(this.wielding.get(i) + ", ");
			}
			out.append("and " + this.wielding.get(this.wielding.size()));
			return out.toString();
		}*/
	}

	public void equipWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}

	public void takeItem(Item item)
	{
		this.inventory.add(item);
	}

	public void dropItem(Item toDrop)
	{
		for(int i = 0; i < this.inventory.size(); i ++)
		{
			if(this.inventory.get(i).equals(toDrop))
			{
				this.inventory.remove(i);
			}
		}
	}

	public Status[] getState()
	{
		ArrayList<Status> out = new ArrayList<Status>();
		for(String key : this.state.keySet())
		{
			out.add(this.state.get(key));
		}
		return out.toArray(new Status[0]);
	}

	public void addStatus(Status status)
	{
		this.state.put(status.getName(), status);
	}
	public void removeStatus(Status status)
	{
		this.state.remove(status.getName());
	}

	public boolean encountered()
	{
		return this.encountered;
	}

	public String wound(int damage)
	{
		this.hitPoints -= damage;
		if(this.hitPoints > 0) return "";
		else
		{
			this.die();
			return this.deathText;
		}
	}

	public void die()
	{
		for(int i = 0; i < this.inventory.size(); i ++)
		{
			this.dropItem(this.inventory.get(i));
		}
		this.moveTo(null);
	}

	public String act(Verb action, Player actor)
	{
		if(action.equals(new Verb("attack", "what")))
		{
			return "You can't attack without a weapon.";
		}
		else return super.act(action, actor);
	}

	public String act(Verb action, Player actor, GameObject secondaryObject)
	{
		if(action.equals(new Verb("attack", "what")))
		{
			if(secondaryObject instanceof Weapon)
			{
				if(this.weapon != null)
				{
					int damage = ((Weapon)secondaryObject).attack(this.AC);
					StringBuilder out = new StringBuilder();
					if(damage > 0)
					{
						out.append("You strike the " + this.names[0].toLowerCase() + " with your " + secondaryObject.getNouns()[0].getName().toLowerCase() + " for " + damage + " damage.\n");
						out.append(this.wound(damage));
					}
					else
					{
						out.append("You swing your " + secondaryObject.getNouns()[0].getName().toLowerCase() + " and miss the " + this.names[0].toLowerCase() + ".\n");
					}

					if(this.hitPoints > 0)
					{
						int mobDamage = this.weapon.attack(actor.AC);
						if(mobDamage > 0)
						{
							out.append("It strikes back, hitting you for " + mobDamage + " damage.");
							out.append(actor.wound(mobDamage));
						}
						else
						{
							out.append("It attempts to reply in kind, but misses, failing to harm you.");
						}
					}
					return out.toString();
				}
				else return "You couldn't hurt this " + this.names[0].toLowerCase() + " - it's harmless!";
			}
			else return "You can't attack with a " + secondaryObject.getNouns()[0].getName().toLowerCase() + ".";
		}
		else return super.act(action, actor);
	}

	public Verb[] getVerbs()
	{
		ArrayList<Verb> out = new ArrayList<Verb>();
		out.add(new Verb("attack", "what"));
		out.add(new Verb("hit", "attack", "what"));
		out.add(new Verb("kill", "attack", "what"));
		for(Verb v : super.getVerbs()) out.add(v);
		return out.toArray(new Verb[0]);
	}

	public Noun[] getNouns()
	{
		this.encountered = true;
		return super.getNouns();
	}
}
