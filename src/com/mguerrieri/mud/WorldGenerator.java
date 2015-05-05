package com.mguerrieri.mud;

import com.mguerrieri.mud.character.Character;
import com.mguerrieri.mud.inventory.Key;
import com.mguerrieri.mud.inventory.Weapon;
import com.mguerrieri.mud.io.AdventureLoader;
import com.mguerrieri.mud.navigation.Path;
import com.mguerrieri.mud.navigation.Room;

/**
 * Generates a world from the specified objects to the file "0"
 *
 * @author Mario Guerrieri
 *
 */
public class WorldGenerator
{
	public static void main(String[] args)
	{
		AdventureLoader.init("");

		Room room1 = new Room(new String[]{"Dungeon cell"}, "You are standing in a dank, dark dungeon cell.", "", true);
		Weapon sword = new Weapon(new String[]{"Sword"}, "An old, rusty sword leans against the wall.", "Closer inspection reveals a small door along the seam between hilt and pommel.", true, true, 5, 10, 0);
		sword.moveTo(room1);
		Room room2 = new Room(new String[]{"Dungeon hallway"}, "A hallway extends into the darkness in both directions, with burnt-out torches hung every few meters, between doors to cells like yours.", "", true);
		Path path1_2 = new Path(new String[]{"Cell door", "door"}, "The door is thick iron, with a small barred window at about eye level.", "", "The door scrapes open loudly, flakes of rust falling from the hinges.", "The door is locked. Maybe the key is somewhere nearby.", true, room2, "Through the bars, you can make out a dimly lit hallway.", false, true);
		Key key = new Key(new String[]{"Dungeon key", "key"}, "An ancient-looking key lies discarded on the floor.", "", true, false, path1_2);
		sword.addItem(key);
		room1.addObject(path1_2.getNouns()[0].getName(), path1_2);
		Path path2_1 = new Path(new String[]{"Cell door", "door"}, "The door to your cell hangs open.", "", "You step back into your cell, swinging the door closed behind you.", "", true, room1, "Through the open doorway, you see your cell.", true, false);
		room2.addObject(path2_1.getNouns()[0].getName(), path2_1);
		Room room3 = new Room(new String[]{"Dungeon hallway"}, "A hallway extends into the darkness in both directions, with burnt-out torches hung every few meters, between doors to cells like yours.", "", true);
		Character skeleton = new Character(new String[]{"Skeleton guard", "skeleton", "guard"}, "An animated skeleton stands nearby, feebly waving its broken sword.", "", "The skeleton's bones break apart and scatter across the floor as you finish it off.", "Through the shadows you begin to be able to pick out a dusty skeleton. To your surprise, as you approach, it stands up, brandishing a broken sword.", true, 10, 10);
		Weapon skeleSword = new Weapon(new String[]{"Broken sword"}, "An old sword, snapped about a foot above the pommel, lies nearby.", "", true, true, 0, 6, 0);
		skeleton.equipWeapon(skeleSword);
		skeleton.takeItem(skeleSword);
		skeleton.moveTo(room3);
		Path path2_2 = new Path(new String[]{"Dead end", "left"}, "", "", "Just out of sight of your cell, the hallway's ceiling has caved in, blocking your path. You turn back.", "", true, room2, "The hallway continues to the left.", true, false);
		room2.addObject(path2_2.names[0], path2_2);
		Path path2_3 = new Path(new String[]{"Hallway", "right"}, "", "", "You stumble through the darkness to the right of your cell.", "", true, room3, "The hallway continues to the right.", true, false);
		room2.addObject(path2_3.names[0], path2_3);
		String initText = "You wake up, groggy and disoriented. You grope around in the darkness, managing to pull yourself to your feet against a rough stone wall.";

		AdventureLoader loader = new AdventureLoader(0);
		loader.saveWorld(room1);
		loader.saveInitText(initText);
	}
}
