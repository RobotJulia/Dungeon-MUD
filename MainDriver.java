// Super-bonus points attempted!
// Extra character with a special ability
// Extra dungeon monsters / monsters have abilities
// Inventory system & item use
// Full story-line adventure with multiple rooms to battle in
// One VS. One and One VS. Many battle modes
// Levelling up and collecting monies
// Boss Monster fight based off 'Golbez Clad in Darkness' from Final Fantasy series
// Arena mode (once boss is finished.)
// players can get poisioned, blinded, or turned into a frog
// all ailments can be cured by item use
// the more you level up, the more powerful you (and the Arena) gets :)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainDriver {
	public static void main(String ... args) { 
		Boolean[] roomState = {false, false, false, false, false, false, false, false, false, false, false, false};
		Hero player = null;
		Scanner kb = new Scanner(System.in);
		
		welcome();
		
		// load saved character 
		// jump to correct room
		try {
			
			boolean resumeGame = continueMessage(kb);
			if(resumeGame) {
				String fileName = getFileName(kb);
				if(!fileName.endsWith(".sav")) {
					fileName = fileName + ".sav";
				}
				player = loadHero(fileName, roomState);
				roomState = getRooms(fileName);
			}
			
		} catch (IOException e) {
			System.out.println("Load failed.");
		}
		
		if(player == null) {
			player = new Sorceress("New Player");
			player.name = getPlayerName(kb);
			player = (Hero) setPlayerClass(kb, player);	
		}
		
		while(true) {
			if(!roomState[0]) {
				roomState[0] = room1(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			if(!roomState[1]) {
				roomState[1] = room2(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			if(!roomState[2]) {
				roomState[2] = room3(kb, player);
			}
			if(!roomState[3]) {
				roomState[3] = room4(kb, player);
				displayTownMenu(kb, player, roomState);
				
			}
			if(!roomState[4]) {
				roomState[4] = room5(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			if(!roomState[5]) {
				roomState[5] = room6(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			
			if(!roomState[6]) {
				roomState[6] = room7(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			if(!roomState[7]) {
				roomState[7] = room8(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			if(!roomState[9]) {
				roomState[9] = playRoom9(kb, player, roomState[8], roomState[9]); // this forks of into playing room 10
				displayTownMenu(kb, player, roomState);
			}
			
			if(!roomState[10]) {
				roomState[10] = room11(kb, player);
				displayTownMenu(kb, player, roomState);
			}
			
			displayEndMessage(kb, player);	// Arena fight is in this method
		
		}
	}


	private static boolean continueMessage(Scanner kb) {
		String choice = "";
		System.out.println("\nDo you have a character that would like to continue?");
		System.out.println("(y/n)");
		choice = kb.nextLine();
		if(choice.equals("y") || choice.equals("Y") || choice.toLowerCase().equals("yes")) {
			return true;
		}
		else {
			return false;
		}
	}

	private static String getFileName(Scanner kb) {
		System.out.println("Enter the name of the file: ");
		String fileName = kb.nextLine();
		return fileName;
	}

	private static void displayEndMessage(Scanner kb, Hero player) {
		String choice = "";
		System.out.println("You've beaten the final boss and now that the evil wizard and Lich Queen");
		System.out.println("are dead, peace has been restored to this land.");
		System.out.println("You have defeated all monsters and saved the world!");
		System.out.println("Congratulations! " + player.name + "!");
		System.out.println("If you like you can enter the arena from here");
		System.out.println("enter (y/n)");
		choice = kb.nextLine();
		if(choice.equals("y") || choice.equals("Y") || choice.toLowerCase().equals("yes")) {
			arenaFight(kb, player);
		}
	}
	
	private static void playRoom11(Scanner kb, Hero player) {
		System.out.println("The air inside the house clears as the sunlight outside ");
		System.out.println("becomes apparent. The cloud cover thins as the souls of");
		System.out.println("the undead return to the earth. It is a sunny and clear");
		System.out.println("day outside when suddenly you realize that the last of");
		System.out.println("the monsters have come for revenge!");
	}
	
	
	private static void playRoom10(Scanner kb, Hero player) {
		System.out.println("As you enter the house you notice a shifting of reality like");
		System.out.println("a cold whisp running through all rooms of the house. You hear");
		System.out.println("a mighty voice. 'WE HAVE COMPANY, EVERYONE!'");
		System.out.println("It's the Lich Queen!");
	}
	
	private static boolean playRoom9(Scanner kb, Hero player, boolean rm9, boolean rm10) {
		System.out.println("As you enter the gates of the old mansion you notice more ");
		System.out.println("liches around the sides of the building you can either go");
		System.out.println("inside or fight the liches outside. ");
		System.out.println("Which would you like to do?");
		System.out.println("1. fight monsters outside");
		System.out.println("2. face the boss inside");
		String choice = kb.nextLine();
		try {
			int opt = Integer.parseInt(choice);
			if(opt != 1 && opt != 2) {
				System.out.println("Please enter a valid choice.");
			}
			else if(opt == 1) {
				room9(kb, player); // forks to playing room 10
				rm9 = true;
				rm10 = true;
			}
			else if(opt == 2) {
				rm9 = true;
				playRoom10(kb, player);
				rm10 = room10(kb, player);
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid choice.");
		}		
		return rm10;
	}
	
	private static void playRoom8(Scanner kb, Hero player, ArrayList<Character> enemies) {
		System.out.println("There is a loud howl causing the unded to regroup. This is ");
		System.out.println("bad. You notice that the howling is coming from a group of");
		System.out.println("characters heading your way. It appears as though you have");
		System.out.println("found the lair of many liches. As you head toward the castle");
		System.out.println("you now face " + enemies.size() + " liches!");
	}
	
	private static void playRoom7(Scanner kb, Hero player, ArrayList<Character> enemies) {
		System.out.println("The path you are on is heading towards a town with an ominous");
		System.out.println("castle outlying the village. There is a dark presence here as");
		System.out.println("well as the undead. They seem to be sprouting up from everywhere.");
		System.out.println(player.name + ", you see " + enemies + " in your path.");
	}
	
	private static void playRoom6(Scanner kb, Hero player, ArrayList<Character> boss) {
		System.out.println("You reach your destination and the young wizard agrees to read the scroll, now that");
		System.out.println("he feels safe. 'Our best bet is if I stay here and defend the town. I will put up holy");
		System.out.println("barriers that should keep the town safe.' He says.");
		System.out.println("'What about my scroll?', " + player.name + " asks.");
		System.out.println("'Right! Give that here', he exclaims.");
		System.out.println("You hand him the scroll and he spends quite a lot of time looking at it. Finally he");
		System.out.println("reads it to you. 'Basically it says, the forces of evil will one day gather. When they");
		System.out.println("do a great hero of light will emerge. After that, it gets kind of confusing to read.'");
		System.out.println("With that you part ways. The young wizard thanks you for helping save the village");
		System.out.println("and you decide to continue along the path toward the next town to see if it is also in");
		System.out.println("Trouble.");
		System.out.println("Almost there, You are ambushed by the dark wizard " + boss.get(0).name);		
	}
	 
	private static void playRoom5(Scanner kb, Hero player, ArrayList<Character> enemies5) { 
		System.out.println(player.name + ", you venture forth and realize that your wizard friend must have left town.");
		System.out.println("With all the chaos, there seems to be no reason for most people to stay. Gremlins and the undead");
		System.out.println("are everywhere you look. The town's shops surely won't last.");
		System.out.println("You come across a young wizard hauling an enormous quantity of scrolls.");
		System.out.println("They seem friendly enough, so you ask if they could help you with your scroll.");
		System.out.println("The young wizard agrees, but only if you escort him to safety. He points to a ");
		System.out.println("small tower up on a winding hill. There are no baddies on the hill but there are ");
		System.out.println(enemies5.size() + " zombies on the road blocking the young wizard's path.");
		System.out.println("You agree to help this mysterious stranger and head down the zombie's path.");
	}
	
	
	private static void playRoom4(Scanner kb, Hero player) {
		System.out.println("You are nearly at the town where all wizards have been gathering. Surely your friend ");
		System.out.println("is near by. As you continue along your path, you notice an increase in dead plant life ");
		System.out.println("and you have noticed the presence of the undead. Surely these wizards can answer more");
		System.out.println("of your questions. ");
	}
	

	private static void playRoom3(Scanner kb, Hero player) {
		int townDex = (int)(Math.random() * 6);
		String[] townName = {"Ur", "Spokane", "Cheney", "HelloKitty", "Silly Wizards", "Chaos"};
		System.out.println("You have made it to the Wizards town of " + townName[townDex] + ".");
		System.out.println("Unfortunately, the place is in chaos. You have the opportunity");
		System.out.println("to stay at the inn and visit stores.");
	}
	
	private static void playRoom2(Scanner kb, Character player, ArrayList<Character> baddies) {
		System.out.println("You continue your journey on to the wizards town.");
		System.out.println("You have chosen the best path, one with many town along the way.");
		System.out.println("you come across " + baddies.toString() + " !!");
	}

	private static void playRoom1(Scanner kb, Character player, ArrayList<Character> kobolds) {
		int wizDex = (int)(Math.random() * 6);
		int n = kobolds.size();
		String[] wizNames = {"Arcanis", "'the Archivist'", "Chansellor Roy", "Bonanza", "Tropical Fred", "William the Wise"};
		System.out.println("You venture off alone following clues that led you to finding an ancient scroll,");
		System.out.println("there is but one Wizard who can decipher it, thank goodness you and the Wizard " + wizNames[wizDex]);
		System.out.println("are friends!");
		System.out.println("As you head on your journey, you notice that the nearest town is also on the way.");
		System.out.println("All you have to do is get around some arguing kobolds that are blocking your path.");
		System.out.println("There are " + n + " of them." );
	}

	private static Hero loadHero(String fileName, Boolean[] rooms) throws IOException {
			
			try {	
				File file = new File(fileName);
				Scanner f = new Scanner (file);

				while(f.hasNext()) {	
					String name = f.nextLine();
					String playerClass = f.nextLine();
					int hps = Integer.parseInt(f.nextLine());
					int fullHP = Integer.parseInt(f.nextLine());
					int fullMP = Integer.parseInt(f.nextLine());
					int level = Integer.parseInt(f.nextLine());
					int xps = Integer.parseInt(f.nextLine());
					int xpLimit  = Integer.parseInt(f.nextLine());
					int mps = Integer.parseInt(f.nextLine());
					int monies = Integer.parseInt(f.nextLine());
					int attSpeed = Integer.parseInt(f.nextLine());
					int maxDamage = Integer.parseInt(f.nextLine());
					int minDamage = Integer.parseInt(f.nextLine());
					int spellDamage = Integer.parseInt(f.nextLine());
					double chanceToHit = Double.parseDouble(f.nextLine());
					double chanceToBlock = Double.parseDouble(f.nextLine());
					int minHeal = Integer.parseInt(f.nextLine());
					int maxHeal = Integer.parseInt(f.nextLine());
					int chanceToHeal = Integer.parseInt(f.nextLine());
					double pChance = Double.parseDouble(f.nextLine());
					boolean isPoisioned = Boolean.parseBoolean(f.nextLine());
					boolean isFrog = Boolean.parseBoolean(f.nextLine());
					boolean isBlind = Boolean.parseBoolean(f.nextLine());
					int minFrogDamage = Integer.parseInt(f.nextLine());
					int maxFrogDamage = Integer.parseInt(f.nextLine());	
					
					ArrayList<String> items = new ArrayList<String>();
					
					for(int i = 0; i < rooms.length; i++) {
						rooms[i] = Boolean.parseBoolean(f.nextLine());
					}				
					while(f.hasNext()) {
						items.add(f.nextLine());
					}
					f.close();
					if(playerClass.equals("class Sorceress")) {
						Hero player = new Sorceress(name);
						player.hps = hps;
						player.fullHP = fullHP;
						player.fullMP = fullMP;
						player.mps = mps;
						player.level = level;
						player.xps = xps;
						player.xpLimit = xpLimit;
						player.monies = monies;
						player.attSpeed = attSpeed;
						player.maxDamage = maxDamage;
						player.minDamage = minDamage;
						player.spellDamage = spellDamage;
						player.chanceToBlock = chanceToBlock;
						player.chanceToHeal = chanceToHeal; // no point but whatever, load it.
						player.minHeal = minHeal;
						player.maxHeal = maxHeal;
						player.chanceToHit = chanceToHit;
						player.pChance = pChance;
						player.isPoisioned = isPoisioned;
						player.isBlind = isBlind;
						player.isFrog = isFrog;
						player.minFrogDamage = minFrogDamage;
						player.maxFrogDamage = maxFrogDamage;
						player.inventory = items;
						return player;
					}
					else if(playerClass.equals("class Rogue")) {
						Hero player = new Rogue(name);
						player.hps = hps;
						player.fullHP = fullHP;
						player.fullMP = fullMP;
						player.mps = mps;
						player.level = level;
						player.xps = xps;
						player.xpLimit = xpLimit;
						player.monies = monies;
						player.attSpeed = attSpeed;
						player.maxDamage = maxDamage;
						player.minDamage = minDamage;
						player.spellDamage = spellDamage;
						player.chanceToBlock = chanceToBlock;
						player.chanceToHeal = chanceToHeal; // no point but whatever, load it.
						player.minHeal = minHeal;
						player.maxHeal = maxHeal;
						player.chanceToHit = chanceToHit;
						player.pChance = pChance;
						player.isPoisioned = isPoisioned;
						player.isBlind = isBlind;
						player.isFrog = isFrog;
						player.minFrogDamage = minFrogDamage;
						player.maxFrogDamage = maxFrogDamage;
						player.inventory = items;
						return player;
					}
					else if(playerClass.equals("class Warrior")) {
						Hero player = new Warrior(name);
						player.hps = hps;
						player.fullHP = fullHP;
						player.fullMP = fullMP;
						player.mps = mps;
						player.level = level;
						player.xps = xps;
						player.xpLimit = xpLimit;
						player.monies = monies;
						player.attSpeed = attSpeed;
						player.maxDamage = maxDamage;
						player.minDamage = minDamage;
						player.spellDamage = spellDamage;
						player.chanceToBlock = chanceToBlock;
						player.chanceToHeal = chanceToHeal; // no point but whatever, load it.
						player.minHeal = minHeal;
						player.maxHeal = maxHeal;
						player.chanceToHit = chanceToHit;
						player.pChance = pChance;
						player.isPoisioned = isPoisioned;
						player.isBlind = isBlind;
						player.isFrog = isFrog;
						player.minFrogDamage = minFrogDamage;
						player.maxFrogDamage = maxFrogDamage;
						player.inventory = items;
						return player;
					}
					else if(playerClass.equals("class Druid")) {
						Hero player = new Druid(name);
						player.hps = hps;
						player.fullHP = fullHP;
						player.fullMP = fullMP;
						player.mps = mps;
						player.level = level;
						player.xps = xps;
						player.xpLimit = xpLimit;
						player.monies = monies;
						player.attSpeed = attSpeed;
						player.maxDamage = maxDamage;
						player.minDamage = minDamage;
						player.spellDamage = spellDamage;
						player.chanceToBlock = chanceToBlock;
						player.chanceToHeal = chanceToHeal; // no point but whatever, load it.
						player.minHeal = minHeal;
						player.maxHeal = maxHeal;
						player.chanceToHit = chanceToHit;
						player.pChance = pChance;
						player.isPoisioned = isPoisioned;
						player.isBlind = isBlind;
						player.isFrog = isFrog;
						player.minFrogDamage = minFrogDamage;
						player.maxFrogDamage = maxFrogDamage;
						player.inventory = items;
						return player;
					}
					else if(playerClass.equals("class Werewolf")) {
						Hero player = new Werewolf(name);
						player.hps = hps;
						player.fullHP = fullHP;
						player.fullMP = fullMP;
						player.mps = mps;
						player.level = level;
						player.xps = xps;
						player.xpLimit = xpLimit;
						player.monies = monies;
						player.attSpeed = attSpeed;
						player.maxDamage = maxDamage;
						player.minDamage = minDamage;
						player.spellDamage = spellDamage;
						player.chanceToBlock = chanceToBlock;
						player.chanceToHeal = chanceToHeal; // no point but whatever, load it.
						player.minHeal = minHeal;
						player.maxHeal = maxHeal;
						player.chanceToHit = chanceToHit;
						player.pChance = pChance;
						player.isPoisioned = isPoisioned;
						player.isBlind = isBlind;
						player.isFrog = isFrog;
						player.minFrogDamage = minFrogDamage;
						player.maxFrogDamage = maxFrogDamage;
						player.inventory = items;
						return player;
					}				
				}
			}
			catch (Exception e) {
				System.out.println("Bad data in file");
				e.printStackTrace();
			}
		
		return null;	
	}
	private static Boolean[] getRooms(String fileName) {
		try {
			File file = new File(fileName);
			Scanner f = new Scanner(file);
			
			while(f.hasNext()) {
				for(int i = 0; i < 25; i++) {
					f.nextLine();
				}
				Boolean[] rooms = new Boolean[11];
				for(int j = 0; j < 11; j++) {
					rooms[j] = Boolean.parseBoolean(f.nextLine());
				}
				f.close(); // recent addition
				return rooms;
			}	
			f.close();
		} catch (IOException e) {
			System.out.println("Room restoration failure.");
		}
		
		return null;	
	}
	
	private static void saveGame(Scanner kb, Hero player, Boolean[] rooms) {
		System.out.println("Save current progress? (y/n)");
		String yesNo = "";
		yesNo = kb.nextLine();
		if(yesNo.equals("y") || yesNo.equals("Y") || yesNo.toLowerCase().equals("yes")) {
			
			System.out.println("Please enter a filename: ");
			String fileName = kb.nextLine() + ".sav";
			
			PrintWriter fout;
			try {
				fout = new PrintWriter(new File(fileName));
				fout.println(player.name);
				fout.println(player.getClass());
				fout.println(player.hps);
				fout.println(player.fullHP);
				fout.println(player.fullMP);
				fout.println(player.level);
				fout.println(player.xps);
				fout.println(player.xpLimit);
				fout.println(player.mps);
				fout.println(player.monies);
				fout.println(player.attSpeed);
				fout.println(player.maxDamage);
				fout.println(player.minDamage);
				fout.println(player.spellDamage);
				fout.println(player.chanceToHit);
				fout.println(player.chanceToBlock);
				fout.println(player.minHeal);
				fout.println(player.maxHeal);
				fout.println(player.chanceToHeal);
				fout.println(player.pChance);
				fout.println(player.isPoisioned);
				fout.println(player.isFrog);
				fout.println(player.isBlind);
				fout.println(player.minFrogDamage);
				fout.println(player.maxFrogDamage);
				for(int i = 0; i < rooms.length; i++) {
					fout.println(rooms[i]);
				}
				for(int i = 0; i < player.inventory.size(); i++) {
					fout.println(player.inventory.get(i));
				}
				fout.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error writing to file.");
			}
			
		}
	}
	
	private static void welcome() {
		System.out.println("                                 (`-.                                        \r\n" + 
				"                                  \\  `                                       \r\n" + 
				"     /)         ,   '--.           \\    `                                    \r\n" + 
				"    //     , '          \\/          \\   `   `                                \r\n" + 
				"   //    ,'              ./         /\\    \\>- `   ,----------.               \r\n" + 
				"  ( \\  ,'    .-.-._        /      ,' /\\    \\   . `            `.             \r\n" + 
				"   \\ \\'     /.--. .)       ./   ,'  /  \\     .      `           `.           \r\n" + 
				"    \\     -{/    \\ .)        / /   / ,' \\       `     `-----.     \\          \r\n" + 
				"    <\\      )     ).:)       ./   /,' ,' \\        `.  /\\)    `.    \\         \r\n" + 
				"     >^,  //     /..:)       /   //--'    \\         `(         )    )        \r\n" + 
				"      | ,'/     /. .:)      /   (/         \\          \\       /    /         \r\n" + 
				"      ( |(_    (...::)     (                \\       .-.\\     /   ,'          \r\n" + 
				"      (O| /     \\:.::)                      /\\    ,'   \\)   /  ,'            \r\n" + 
				"       \\|/      /`.:::)                   ,/  \\  /         (  /              \r\n" + 
				"               /  /`,.:)                ,'/    )/           \\ \\              \r\n" + 
				"             ,' ,'.'  `:>-._._________,<;'    (/            (,'              \r\n" + 
				"           ,'  /  |     `^-^--^--^-^-^-'                                     \r\n" + 
				" .--------'   /   |                                                          \r\n" + 
				"(       .----'    |                                                          \r\n" + 
				" \\ <`.  \\         |                                                          \r\n" + 
				"  \\ \\ `. \\        |           Welcome to                                    \r\n" + 
				"   \\ \\  `.`.      |                                                          \r\n" + 
				"    \\ \\   `.`.    |                                                          \r\n" + 
				"     \\ \\    `.`.  |          Dungeon - MUD!                                  \r\n" + 
				"      \\ \\     `.`.|                                                          \r\n" + 
				"       \\ \\      `.`.                                                         \r\n" + 
				"        \\ \\     ,^-'                                                         \r\n" + 
				"         \\ \\    |                                                            \r\n" + 
				"          `.`.  |                                                            \r\n" + 
				"             .`.|                                                            \r\n" + 
				"              `._> ");
		System.out.println("");
		System.out.println("You will navigate a dungeon and destroy monsters");
		System.out.println("All the while collecting loot and power-ups!");
	}
	
	public static String getPlayerName(Scanner kb) {
		String choice = "";
		System.out.println();
		System.out.println("What is your name, Adventurer?:");
		choice = kb.nextLine();
		return choice;
	}
	
	public static String displayMenu(Scanner kb, String choice, Character player) {
		choice = "";
		System.out.println("Speed: " + player.attSpeed + "     HitPoints: " + player.hps + "     Magic: " + player.mps + "     Level: " + player.level);
		System.out.println("What would you like to do, " + player.name + "?");
		System.out.println();
		System.out.println("1. Attack");
		System.out.println("2. Magic");
		System.out.println("3. Investigate");
		System.out.println("4. Use Item");
		choice = kb.nextLine();
		return choice;
	}
	
	public static String displayTownMenu(Scanner kb, Character player, Boolean[] levels) {
		String choice = "";
		String option = "";
		while(!option.equals("7")) {
			System.out.println("You are now in a town. You have $" + player.monies + " monies. Would you like to visit:");
			System.out.println("1. Item Shop");		
			System.out.println("2. Weapon Shop");
			System.out.println("3. Magic Shop");
			System.out.println("4. Stay at the Inn");
			System.out.println("5. Use Item");
			System.out.println("6. Save Progress");
			System.out.println("7. No thanks");
			option = kb.nextLine();
			if(option.equals("1")) {
				choice = "";
				while(!choice.equals("5")) {
					System.out.println("Welcome to the item shop. We have the following items.");
					System.out.println("You have $" + player.monies + " monies.");
					System.out.println("1. small healing potion			40 monies");
					System.out.println("2. antidote				25 monies");		
					System.out.println("3. eyedrops				25 monies");
					System.out.println("4. maiden's kiss			25 monies");
					System.out.println("5. leave");
					choice = kb.nextLine();
					
					if(choice.equals("1")) {
						if(player.monies - 40 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 40;
							player.inventory.add("small potion");
							System.out.println("Item puchased.");
						}
					}
					if(choice.equals("2")) {
						if(player.monies - 25 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 25;
							player.inventory.add("antidote");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("3")) {
						if(player.monies - 25 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 25;
							player.inventory.add("eyedrops");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("4")) {
						if(player.monies - 25 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 25;
							player.inventory.add("maiden's kiss");
							System.out.println("Item purchased.");
						}	
					}
				}
			} 
			
			else if(option.equals("2")) {
				choice = "";
				while(!choice.equals("5")) {
					System.out.println("Welcome to the weapon shop. We have the following items.");
					System.out.println("You have $" + player.monies + " monies.");
					System.out.println("1. small sword			100 monies");
					System.out.println("2. small labrys			80 monies");		
					System.out.println("3. headgear			60 monies");
					System.out.println("4. elbow pads			60 monies");
					System.out.println("5. leave");
					choice = kb.nextLine();
					if(choice.equals("1")) {
						if(player.monies - 100 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 100;
							player.inventory.add("small sword");
							System.out.println("Item puchased.");
						}
					}
					if(choice.equals("2")) {
						if(player.monies - 80 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 80;
							player.inventory.add("small labrys");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("3")) {
						if(player.monies - 60 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 60;
							player.inventory.add("headgear");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("4")) {
						if(player.monies - 60 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 60;
							player.inventory.add("elbowpads");
							System.out.println("Item purchased.");
						}
					
					}
				}
			} 
		
			
			else if(option.equals("3")) {
				choice = "";
				while(!choice.equals("5")) {
					System.out.println("Welcome to the magic shop. We have the following items.");
					System.out.println("You have $" + player.monies + " monies.");
					System.out.println("1. small charm			100 monies");
					System.out.println("2. metal rod			80 monies");		
					System.out.println("3. wood staff			60 monies");
					System.out.println("4. feather			60 monies");
					System.out.println("5. leave");
					choice = kb.nextLine();
					if(choice.equals("1")) {
						if(player.monies - 100 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 100;
							player.inventory.add("small charm");
							System.out.println("Item puchased.");
						}	
					}
					if(choice.equals("2")) {
						if(player.monies - 80 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 80;
							player.inventory.add("metal rod");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("3")) {
						if(player.monies - 60 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 60;
							player.inventory.add("woodstaff");
							System.out.println("Item purchased.");
						}
					}
					if(choice.equals("4")) {
						if(player.monies - 60 < 0) {
							System.out.println("You cannot afford it.");
						}
						else {
							player.monies = player.monies - 60;
							player.inventory.add("feather");
							System.out.println("Item purchased.");
						}
					}
				}	
			}
			else if(option.equals("4")) {
				choice = "";
				String yesno = "";
				System.out.println("Would you like to stay at the inn? You have $" + player.monies + " monies.");
				System.out.println("It's only 10 monies (y/n) ?");
				yesno = kb.nextLine();
				if(yesno.equals("y") || yesno.equals("Y") || yesno.equals("yes") || yesno.equals("Yes")) {
					if(player.monies - 10 < 0) {
						System.out.println("You cannot afford it.");
					}
					else {
						System.out.println("You spend a nice night at the Inn.");
						System.out.println("All HP/MP restored.");
						player.hps = player.fullHP;
						player.mps = player.fullMP;
					}
				}
				
			}
			else if(option.equals("5")) {
				((Hero) player).useItem(kb, (Hero) player);
			}
			else if(option.equals("6")) {
				saveGame(kb, (Hero) player, levels);
			}
			}
		    return option;
		}
		
	
	public static Character setPlayerClass(Scanner kb, Hero player) {
		String choice = "";
		System.out.println("What class is your character?");
		System.out.println("1. Sorceress");
		System.out.println("2. Rogue");
		System.out.println("3. Warrior");
		System.out.println("4. Druid");
		choice = kb.nextLine();
		switch(choice) {
		
		case "1": choice = "1"; {		
			player = new Sorceress(player.name);
			break;
		}
		case "2": choice = "2"; {
			player = new Rogue(player.name);
			break;
		}
		case "3": choice = "3"; {
			player = new Warrior(player.name);
			break;
		}
		case "4": choice = "4"; {
			player = new Druid(player.name);
			break;
		}
		default: {
			System.out.println("Please enter a valid number.");
			setPlayerClass(kb, player);
		}
		} return player;
	}

	private static void enemyTurn(Scanner kb, ArrayList<Character> enemies, Character player) {
		for(int i =0; i < enemies.size(); i++) {
			Monster enemy = (Monster) enemies.get(i);
			if(enemy.isPoisioned) {
				enemy.isPoisoned();
			}
			
			if(enemy.chanceToHit > Math.random()) {
				enemy.attack((Hero) player, enemy);
				if(player.hps <= 0) {
					gameOver(kb, player);
				}
			}	

		}
	}
	
	private static void enemyTurn(Hero player, Monster enemy) {
		int turns = 1;
		if(enemy.isPoisioned) {
			enemy.isPoisoned();
		}
		if(enemy.attSpeed > 2 * player.attSpeed) {
			turns = (enemy.attSpeed / player.attSpeed);
			System.out.println(enemy.name + "gets " + turns + " turns this round.");
		}
	
		for(int i = 0; i < turns; i++) {
			if (enemy.chanceToHit > Math.random()) {
				enemy.attack(player, enemy);
			}	
			if(player.hps <= 0) {
				gameOver(new Scanner(System.in), player);
			}
		}
	}
	
	private static void bossTurn(Scanner kb, ArrayList<Character> enemies, Character player) {
		for(int i =0; i < enemies.size(); i++) {
			Monster enemy = (Monster) enemies.get(i);
			if(enemy.isPoisioned) {
				enemy.isPoisoned();
			}
			if(enemy.chanceToHit > Math.random()) {
				enemy.attack(kb, enemies, player);
				if(player.hps <= 0) {
					gameOver(kb, player);
				}
			}	

		}
	}
	
	static void gameOver(Scanner kb, Character player) {
		String choice;
		System.out.println(player.name);
		System.out.println("You have died.");
		System.out.println("Game Over.");
		System.out.println();
		System.out.println("Would you like to play again (y/n) ?");
		choice = kb.nextLine();
		if(choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals("Yes")) {
			main();
		}
		else System.exit(0);
	}

	protected static Monster getMonster() {
		String[] monsterName = {"'The Smasher'", "'The Grader'", "Jarvar", "RottTooth", "Mad Howard",  
				"OS-X", "Windows", "of broken backs", "BeerGut", "of Doom"};
		String name = monsterName[(int)(Math.random() * 10)];
		int n = (int)(Math.random() * 4);
		if(n == 0) {
			Monster mon = new Ogre("Ogre " + name);
			return mon;
		}
		if(n == 1) {
			Monster mon = new Gremlin("Gremlin " + name);
			return mon;
		}
		if(n == 2) {
			Monster mon = new Skeleton("Skeleton " + name);
			return mon;
		}
		if(n == 3) {
			Monster mon = new Zombie("Zombie " + name);
			return mon;
		}
		return null;
	}
		
	protected static Monster getMonsterPlus() {
		String[] monsterName = {"'The Smasher'", "'The Grader'", "Jarvar", "RottTooth", "Mad Howard",  
				"of the Wild", "'the Damned'", "of broken backs", "'the Furious'", "of Doom"};
		String name = monsterName[(int)(Math.random() * 10)];
		int n = (int)(Math.random() * 6);
		if(n == 0) {
			Monster mon = new Ogre("Ogre " + name);
			return mon;
		}
		if(n == 1) {
			Monster mon = new Gremlin("Gremlin " + name);
			return mon;
		}
		if(n == 2) {
			Monster mon = new Skeleton("Skeleton " + name);
			return mon;
		}
		if(n == 3) {
			Monster mon = new Zombie("Zombie " + name);
			return mon;
		}
		if(n == 4) {
			Monster mon = new Lich("Lich " + name);
			return mon;
		}
		if(n == 5) {
			Monster mon = new WhiteDragon("White Dragon " + name);
			return mon;
		}
		
		return null;
	}
	
	
	private static boolean room1(Scanner kb, Hero player) {
		String choice = "";
		// set up room enemies
		int n = 2 + (int)(Math.random() * 3);
		ArrayList<Character> enemies = new ArrayList<Character>(n);
		for(int i = 0; i < n; i++) {
			enemies.add(new Kobold("Kobold "+ (i+1)));
		}
		
		// launch room
		boolean room1Won = false;
		playRoom1(kb, player, enemies);
		while(!room1Won) {
			
			choice = displayMenu(kb, choice, player);	
			
			switch(choice) {
			case "1": choice = "1"; {
				player.attack(kb, enemies, player, room1Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room1Won = true;
				}
				break;
			}
			case "2": choice = "2"; {
				player.castMagic(kb, enemies, player, room1Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room1Won = true;
				}
				break;
			}
			case "3": choice = "3"; {
				player.investigate((Monster) enemies.get(0));
				break;
			}
			case "4": choice = "4"; {
				player.useItem(kb, player);
				break;
			}
			default: {
				System.out.println("Please enter a valid choice.");
				break;
			}
			}	
			enemyTurn(kb, enemies, player);
		}	
		
		System.out.println("You've beaten room 1! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room1Won;
	}
	
	private static boolean room2(Scanner kb, Hero player) {
				String choice = "";
				boolean room2Won = false;
				// set up room 2 enemies
				int n = 2 + (int)(Math.random() * 6);
				ArrayList<Character> enemies = new ArrayList<Character>(n);
				for(int i = 0; i < n; i++) {
					int draw = (int)(Math.random()* 2);
					if(draw == 1) {
						enemies.add(new Kobold("Kobold "+ (i+1)));
					}
					if(draw == 0) {
						enemies.add(new GiantBat("Giant Bat " + (i+1)));
						// 
					}	
				}
				playRoom2(kb, player, enemies);
				
				while(!room2Won) {
					
					choice = displayMenu(kb, choice, player);	
					
					switch(choice) {
					case "1": choice = "1"; {
						player.attack(kb, enemies, player, room2Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room2Won = true;
						}
						break;
					}
					case "2": choice = "2"; {
						player.castMagic(kb, enemies, player, room2Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room2Won = true;
						}
						break;
					}
					case "3": choice = "3"; {
						player.investigate((Monster) enemies.get(0));
						break;
					}
					case "4": choice = "4"; {
						player.useItem(kb, player);
						break;
					}
					default: {
						System.out.println("Please enter a valid choice.");
						break;
					}
					}
					
					enemyTurn(kb, enemies, player);
					
				}
				System.out.println("You've beaten room 2 " + player.name + "! Yay!");
				System.out.println("You gained " + player.xps + " experience.");
				return room2Won;
				
	}
	
	private static boolean room3(Scanner kb, Hero player) {
		String choice = "";
		boolean room3Won = false;
		Monster mon = getMonster();
		
		System.out.println("You meet " + mon.name + "!");
		
		// room 3
		while(!room3Won) {
			
			choice = displayMenu(kb, choice, player);	
			
			if (choice.equals("1")) {
				player.attack(player, mon);
				player.levelUp();
				if(mon.hps <= 0) {
					room3Won = true;
				}
			}
				
			else if (choice.equals("2")) {
				player.castMagic(kb, player, mon);
				player.levelUp();
				if(mon.hps <= 0) {
					room3Won = true;
				}
			}
			else if (choice.equals("3")) {
				player.investigate(mon);	
			}
			else if (choice.equals("4")) {
				player.useItem(kb, player);
			}
			else {
				System.out.println("Please enter a valid choice.");
			}
			if(!room3Won) {
				enemyTurn(player, mon);
			}
		}
		System.out.println("You've beaten room 3 " + player.name + "! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room3Won;
		
	}
	
	private static boolean room4(Scanner kb, Hero player) {
		String choice = "";
		boolean room4Won = false;
		playRoom4(kb, player);
		
		ArrayList<Character> enemies4 = new ArrayList<Character>(2);
		enemies4.add(new Gremlin("Poky"));
		enemies4.add(new Gremlin("Gnash"));
		
		System.out.println("You meet the gremlins " + enemies4.get(0).name + " and " + enemies4.get(1).name + "!");
		
		// room 4
		while(!room4Won) {
			
			choice = displayMenu(kb, choice, player);	
			
			if (choice.equals("1")) {
				player.attack(kb, enemies4, player, room4Won);
				player.levelUp();
				if(enemies4.isEmpty()) {
					room4Won = true;
				}
			}
				
			else if (choice.equals("2")) {
				player.castMagic(kb, enemies4, player, room4Won);
				player.levelUp();
				if(enemies4.isEmpty()) {
					room4Won = true;
				}
			}	
			else if (choice.equals("3")) {
				player.investigate((Monster) enemies4.get(0));	
			}
			else if (choice.equals("4")) {
				player.useItem(kb, player);
			}
			else {
				System.out.println("Please enter a valid choice.");
			}
			if(!room4Won) {
				enemyTurn(player, (Gremlin) enemies4.get(0));
				try {
					enemyTurn(player, (Gremlin) enemies4.get(1));
				}
				catch(Exception e) {
					// do nothing
				}
			}
		}
		System.out.println("You've beaten room 4 " + player.name + "! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room4Won;
	}
	
	//room 5
	private static boolean room5(Scanner kb, Hero player) {
		playRoom3(kb, player);	// this should be renamed
		String choice = "";
		boolean room5Won = false;
		
		// set up room enemies
		int n = 1 + (int)(Math.random() * 3);
		ArrayList<Character> enemies5 = new ArrayList<Character>(n);
		for(int i = 0; i < n; i++) {
				enemies5.add(new Zombie ("Zombie " + (i+1)));
			}
				
				// launch room 5
				playRoom5(kb, player, enemies5);
				while(!room5Won) {
					
					choice = displayMenu(kb, choice, player);	
					
					if(player.isPoisioned) {
						player.isPoisioned();
					}
					
					switch(choice) {
					case "1": choice = "1"; {
						player.attack(kb, enemies5, player, room5Won);
						player.levelUp();
						if(enemies5.isEmpty()) {
							room5Won = true;
						}
						break;
					}
					case "2": choice = "2"; {
						player.castMagic(kb, enemies5, player, room5Won);
						player.levelUp();
						if(enemies5.isEmpty()) {
							room5Won = true;
						}
						break;
					}
					case "3": choice = "3"; {
						player.investigate((Monster) enemies5.get(0));
						break;
					}
					case "4": choice = "4"; {
						player.useItem(kb, player);
						break;
					}
					default: {
						System.out.println("Please enter a valid choice.");
						break;
					}
				}	
				enemyTurn(kb, enemies5, player);
			}	
			
			System.out.println("You've beaten room 5! Yay!");
			System.out.println("You gained " + player.xps + " experience.");
			return room5Won;
	}
	
	private static boolean room6(Scanner kb, Hero player) {
			// room 6
			String choice = "";
			boolean room6Won = false;
			String[] names = {"Arcanis ", "Golbez ", "Dumbledore ", "Gandolf ", "Voldermort "};
			String[] titles = {"the Omnipotent", "the wicked", "Clad in Darkness", "Lord of the Undead", "the Crazed", "the Mad"};
			String wizName = names[(int)(Math.random() * 5)];
			String wizTitle = titles[(int)(Math.random() * 6)];
			ArrayList<Character> boss = new ArrayList<Character>();
			boss.add(new DarkWizard(wizName + wizTitle));
			
			// launch room 6
			playRoom6(kb, player, boss);
			while(!room6Won) {
				
				choice = displayMenu(kb, choice, player);	
				
				if(player.isPoisioned) {
					player.isPoisioned();
				}
				
				switch(choice) {
				case "1": choice = "1"; {
					player.attack(kb, boss, player, room6Won);
					player.levelUp();
					if(boss.isEmpty()) {
						room6Won = true;
					}
					break;
				}
				case "2": choice = "2"; {
					player.castMagic(kb, boss, player, room6Won);
					player.levelUp();
					if(boss.isEmpty()) {
						room6Won = true;
					}
					break;
				}
				case "3": choice = "3"; {
					player.investigate((Monster)boss.get(0));
					break;
				}
				case "4": choice = "4"; {
					player.useItem(kb, player);
					break;
				}
				default: {
					System.out.println("Please enter a valid choice.");
					break;
				}
			}	
			bossTurn(kb, boss, player);
		}	
		System.out.println("You have defeated " + wizName + wizTitle + "!!");
		System.out.println("You gained " + player.xps + " experience.");
		return room6Won;
	}
	
	private static boolean room7(Scanner kb, Hero player) {
		String choice = "";
		boolean room7Won = false;
		int n = 2 + (int)(Math.random() * 2);
		ArrayList<Character> enemies = new ArrayList<Character>(n);
		enemies.add(new Lich ("Lich "));
		for(int i = 1; i < n; i++) {
			if(Math.random() > .5) {
				enemies.add(new Zombie ("Zombie " + (i)));
			}
			else {
				enemies.add(new Skeleton ("Skeleton " + (i)));
			}
		}
				playRoom7(kb, player, enemies);
				// launch room 7
				
				while(!room7Won) {
					
					choice = displayMenu(kb, choice, player);	
					
					if(player.isPoisioned) {
						player.isPoisioned();
					}
					
					switch(choice) {
					case "1": choice = "1"; {
						player.attack(kb, enemies, player, room7Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room7Won = true;
						}
						break;
					}
					case "2": choice = "2"; {
						player.castMagic(kb, enemies, player, room7Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room7Won = true;
						}
						break;
					}
					case "3": choice = "3"; {
						player.investigate((Monster) enemies.get(0));
						break;
					}
					case "4": choice = "4"; {
						player.useItem(kb, player);
						break;
					}
					default: {
						System.out.println("Please enter a valid choice.");
						break;
					}
				}	
				enemyTurn(kb, enemies, player);
			}	
			
		System.out.println("You've beaten room 7! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room7Won;
		
	}
	
	private static boolean room8(Scanner kb, Hero player) {
		String choice = "";
		boolean room8Won = false;
		int n = 2 + (int)(Math.random() * 2);
		ArrayList<Character> enemies = new ArrayList<Character>(n);
		for(int i = 0; i < n; i++) {
				enemies.add(new Lich ("Lich " + (i)));	
		}
		
		playRoom8(kb, player, enemies);
		
		// launch room 8
				while(!room8Won) {
					
					choice = displayMenu(kb, choice, player);	
					
					if(player.isPoisioned) {
						player.isPoisioned();
					}
					
					switch(choice) {
					case "1": choice = "1"; {
						player.attack(kb, enemies, player, room8Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room8Won = true;
						}
						break;
					}
					case "2": choice = "2"; {
						player.castMagic(kb, enemies, player, room8Won);
						player.levelUp();
						if(enemies.isEmpty()) {
							room8Won = true;
						}
						break;
					}
					case "3": choice = "3"; {
						player.investigate((Monster) enemies.get(0));
						break;
					}
					case "4": choice = "4"; {
						player.useItem(kb, player);
						break;
					}
					default: {
						System.out.println("Please enter a valid choice.");
						break;
					}
				}	
				enemyTurn(kb, enemies, player);
			}	
			
		System.out.println("You've beaten room 8! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room8Won;
	}
	
	private static boolean room9(Scanner kb, Hero player) {
		// lich army outside
		String choice = "";
		int n = 2 + (int)(Math.random() * 3);
		ArrayList<Character> enemies = new ArrayList<Character>(n);
		
		for(int i = 0; i < n; i++) {
			enemies.add(new Lich("Lich " + (i+1)));
		}
		
		boolean room9Won = false;
		while(!room9Won) {
			choice = displayMenu(kb, choice, player);	
			
			if(player.isPoisioned) {
				player.isPoisioned();
			}
			
			switch(choice) {
			case "1": choice = "1"; {
				player.attack(kb, enemies, player, room9Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room9Won = true;
				}
				break;
			}
			case "2": choice = "2"; {
				player.castMagic(kb, enemies, player, room9Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room9Won = true;
				}
				break;
			}
			case "3": choice = "3"; {
				player.investigate((Monster) enemies.get(0));
				break;
			}
			case "4": choice = "4"; {
				player.useItem(kb, player);
				break;
			}
			default: {
				System.out.println("Please enter a valid choice.");
				break;
			}
		}	
		
		enemyTurn(kb, enemies, player);
		
		}	
	
		System.out.println("You've beaten room 9! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		
		// should there be a town here ?
		
		playRoom10(kb, player);
		room10(kb, player);
		return true;
	}
		

	private static boolean room10(Scanner kb, Hero player) {
		// the Lich Queen
		String choice = "";
		Monster LQ = new LichQueen("The Lich Queen");
		boolean room10Won = false;
		while(!room10Won) {
			
			choice = displayMenu(kb, choice, player);	
			
			if (choice.equals("1")) {
				player.attack(player, LQ);
				player.levelUp();
				if(LQ.hps <= 0) {
					room10Won = true;
				}
			}
				
			else if (choice.equals("2")) {
				player.castMagic(kb, player, LQ);
				player.levelUp();
				if(LQ.hps <= 0) {
					room10Won = true;
				}
			}
			else if (choice.equals("3")) {
				player.investigate(LQ);	
			}
			else if (choice.equals("4")) {
				player.useItem(kb, player);
			}
			else {
				System.out.println("Please enter a valid choice.");
			}
			if(!room10Won) {
				enemyTurn(player, LQ);
			}
		}
		System.out.println(player.name + "! You've beaten the Lich Queen!! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room10Won;
	}
	
	public static Boolean room11(Scanner kb, Hero player) {
		String choice = "";
		ArrayList<Character> enemies = new ArrayList<Character>(); 
		int n = 2 + (int)(Math.random() * 4);
		for(int i = 0; i < n; i++) {
			enemies.add(getMonsterPlus());
		}
		
		playRoom11(kb, player);
		System.out.println("You see " + enemies.toString() + ".");
		
		boolean room11Won = false;
		while(!room11Won) {
			choice = displayMenu(kb, choice, player);	
			
			if(player.isPoisioned) {
				player.isPoisioned();
			}
			
			switch(choice) {
			case "1": choice = "1"; {
				player.attack(kb, enemies, player, room11Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room11Won = true;
				}
				break;
			}
			case "2": choice = "2"; {
				player.castMagic(kb, enemies, player, room11Won);
				player.levelUp();
				if(enemies.isEmpty()) {
					room11Won = true;
				}
				break;
			}
			case "3": choice = "3"; {
				player.investigate((Monster) enemies.get(0));
				break;
			}
			case "4": choice = "4"; {
				player.useItem(kb, player);
				break;
			}
			default: {
				System.out.println("Please enter a valid choice.");
				break;
			}
		}	
		
		enemyTurn(kb, enemies, player);
		
		}	
	
		System.out.println("You've beaten room 11! Yay!");
		System.out.println("You gained " + player.xps + " experience.");
		return room11Won;
		
	}
	
	// bonus play!
	public static void arenaFight(Scanner kb, Hero player) {
		boolean finished = false;
		int arenaRun = 0;
		while(!finished) {
			String choice = "";
			ArrayList<Character> enemies = new ArrayList<Character>(); 
			int n = 1 + (int)(Math.random() * (player.level - 2)); 
			for (int i = 0; i < n; i++) {
				enemies.add(getMonster());
			}
			System.out.println("You enter a room with ");
			System.out.println(enemies);
			System.out.println("Get ready to FIGHT!");
			boolean roomWon = false;
			while(!roomWon) {
				choice = displayMenu(kb, choice, player);	
				
				if(player.isPoisioned) {
					player.isPoisioned();
				}
				
				switch(choice) {
				case "1": choice = "1"; {
					player.attack(kb, enemies, player, roomWon);
					player.levelUp();
					if(enemies.isEmpty()) {
						roomWon = true;
					}
					break;
				}
				case "2": choice = "2"; {
					player.castMagic(kb, enemies, player, roomWon);
					player.levelUp();
					if(enemies.isEmpty()) {
						roomWon = true;
					}
					break;
				}
				case "3": choice = "3"; {
					player.investigate((Monster) enemies.get(0));
					break;
				}
				case "4": choice = "4"; {
					player.useItem(kb, player);
					break;
				}
				default: {
					System.out.println("Please enter a valid choice.");
					break;
				}
			}	
			enemyTurn(kb, enemies, player);				
			}
		
		arenaRun++;
		System.out.println("You have defeated arena room " + arenaRun + "! Congratulations!" );
		System.out.println("Retire? (y/n)");
		choice = kb.nextLine();
		if(choice.equals("y") || choice.equals("Y") || choice.toLowerCase().equals("yes")) {			
			System.out.println(player.name + " you have earned " + player.xps + " total experience at level " + player.level);
			System.out.println("Congratulations! You finished the game in Arena mode by retiring!");
			System.out.println("You are a true Hero! Woot!!!");
			System.exit(0);
		}
		else {
			displayTownMenu(kb, player, null);
			arenaFight(kb, player);		
		}
		}
		
	}
}
