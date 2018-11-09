import java.util.ArrayList;
import java.util.Scanner;

public class Warrior extends Hero {

	Warrior(String name) {
		super(name);
		hps = 125;
		mps = 20;
		fullHP = 125;
		fullMP = 20;
		monies = 100;
		attSpeed = 4;
		chanceToHit = .8;
		chanceToBlock = .2;
		minDamage = 35;
		maxDamage = 60;
	}

	@Override
	public ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("berserk");
		grimoire.add("Crushing Blow");
		return grimoire;
	}
	
	public void castMagic(Scanner kb, Hero player, Monster enemy) {
		String choice = "";
		int damRoll;
		ArrayList<String> spellBook = getSpellBook();
		System.out.println("Spells: ");
		for(int i = 0; i < (spellBook.size()); i++) {
			System.out.println((i+1) + " " + spellBook.get(i));
		}
		System.out.println("Cast which spell?");
		choice = kb.nextLine();
		if(choice.equals("1")) {
			if(player.mps - 4 < 0) {
				System.out.println("You do not have enough mana.");
				return;
			}
			player.mps = player.mps - 4;
			System.out.println(player.name + " casts " + spellBook.get(Integer.parseInt(choice)));
			if(Math.random() > .5) {
				player.attSpeed = player.attSpeed + 2;
			}
			else {
				player.attSpeed = player.attSpeed + 1;
			}
		}
		if(choice.equals("2")) {
			if(player.mps - 5 < 0) {
				System.out.println("You do not have enough mana.");
				return;
			}
			player.mps = player.mps - 5;
			System.out.println("You cast " + spellBook.get(Integer.parseInt(choice)) + " at " + enemy.name);
		if(Math.random() > .6) {
			System.out.println("You feel a surge of energy pulsing through you!");
			System.out.println("Now is the time to strike!");
			damRoll = 75 + (int)(Math.random() * 100);
			System.out.println("You deal a whopping " + damRoll + " to " + enemy.name + "!");
			enemy.hps = enemy.hps - damRoll;
			if(enemy.hps <= 0) {
				player.xps = enemy.xps + player.xps;
				player.monies = enemy.monies + player.monies;
				player.inventory.addAll(enemy.inventory);
				System.out.println(enemy.name + " is dead." );
				if(!enemy.inventory.isEmpty()) {
					System.out.println(enemy.name + " dropped " + enemy.inventory.toString());
					System.out.println("You retrieve " + enemy.inventory.toString());
				}	
			}
			else {
				System.out.println(enemy.name + " has " + enemy.hps + " hitpoints left.");
			}
		}
		}
	}

	@Override
	public int castMagic(Scanner kb, ArrayList<Character> enemies, Character player, boolean roomWon) {
		String choice = "";
		ArrayList<String> spellBook = getSpellBook();
		System.out.println("Spells: ");
		for(int i = 0; i < (spellBook.size()); i++) {
			System.out.println((i+1) + " " + spellBook.get(i));
		}
		System.out.println("Cast which spell?");
		choice = kb.nextLine();
		if(choice.equals("1")) {
			if(player.mps - 4 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 4;
			System.out.println(player.name + " casts " + spellBook.get(Integer.parseInt(choice)));
			if(Math.random() > .5) {
				player.attSpeed = player.attSpeed + 2;
			}
			else {
				player.attSpeed = player.attSpeed + 1;
			}
		}
		if(choice.equals("2")) {
			if(player.mps - 5 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 5;
			System.out.println("Choose a target: ");
			choice = kb.nextLine();
			try {
				int index = Integer.parseInt(choice) - 1;
				Monster enemy = (Monster) enemies.get(index);
				System.out.println("You cast " + spellBook.get(index) + " at " + enemy.name);
				if(Math.random() > .6) {
					System.out.println("You feel a surge of energy pulsing through you!");
					System.out.println("Now is the time to strike!");
					int damRoll = player.maxDamage + (int)(Math.random() * 100);
					System.out.println("You deal a whopping " + damRoll + " to " + enemy.name);
					enemy.hps = enemy.hps - damRoll;
					
					if(enemy.hps <= 0) {
						player.xps = enemy.xps + player.xps;
						player.monies = enemy.monies + player.monies;
						enemies.remove(index);
						System.out.println(enemy.name + " is dead." );	
						System.out.println("There are " + enemies.size() + " left.");
					}
					else {
						System.out.println(enemy.name + " has " + enemy.hps + " hitpoints left.");
					}
					if(enemies.isEmpty()) {
						roomWon = true;
						return player.xps;
					}
				}
				else {
					System.out.println("Spell failed.");
				}
			}
			catch (Exception e){
				System.out.println("Please select a valid target:");
			}
		}
		return player.xps;
	}
}
