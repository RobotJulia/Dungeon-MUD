import java.util.ArrayList;
import java.util.Scanner;

public class Rogue extends Hero {

	Rogue(String name) {
		super(name);
		hps = 100;
		mps = 35;
		fullHP = 75;
		fullMP = 35;
		monies = 120;
		attSpeed = 6;
		chanceToHit = .8;
		chanceToBlock = .4;
		minDamage = 25;
		maxDamage = 45;
		pChance = .65;
	}
	
	@Override
	public ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("steal");
		grimoire.add("Suprise Attack");
		return grimoire;
	}
	
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
			if(player.mps - 5 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 5;
			System.out.println("Choose a target:");
			choice = kb.nextLine();
			try {
				int index = Integer.parseInt(choice)-1;
				Character enemy = enemies.get(index);
				System.out.println("You cast " + spellBook.get(index) + " at " + enemy.name);
				if(Math.random() < player.pChance) {
					System.out.println("Stolen an item from " + enemy.name);
					player.inventory.add(enemy.inventory.get(0));
					try {
						enemy.inventory.remove(0);
					}
					catch (Exception e) {
						// literally do nothing
					}
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Please input a valid choice.");
			}
		}
		if (choice.equals("2")) {
			if(player.mps - 6 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 6;
			if(Math.random() < .8) {
				System.out.println("Select a target: "+ enemies.toString());
				choice = kb.nextLine();
				int index = Integer.parseInt(choice) - 1;
				Monster monster = (Monster) enemies.get(index);
				
				if(Math.random() > .4) {
					System.out.println("Supriiiiiiiiiise, sucker!!!");
					player.attack((Hero) player, monster);
					player.attack((Hero) player, monster);
				
				}
				else if(Math.random() > .4) {
					player.attack((Hero) player, monster);
				}
			}
			
			else {
				System.out.println("Sneak failed. No suprise attack.");
			}
		}
		
		return player.xps;
	}


	@Override
	protected void castMagic(Scanner kb, Hero player, Monster mon) {
		String choice;
		ArrayList<String> spells = getSpellBook();
		for(int i = 0; i < spells.size(); i++) {
			System.out.println((i+1) + " " + spells.get(i));
		}
		System.out.println("Which spell would you like to use?");
		choice = kb.nextLine();
		
		if(choice.equals("1")) {
			if(player.mps - 5 < 0) {
				System.out.println("You do not have enough mana.");
				return;
			}
			player.mps = player.mps - 5;
			System.out.println("You cast " + spells.get(0) + " at " + mon.name);
			if(Math.random() < player.pChance) {
				System.out.println("Stolen an item from " + mon.name);
				player.inventory.add(mon.inventory.get(0));
			}
		}
		
		if (choice.equals("2")) {
			if(player.mps - 5 <= 0) {
				System.out.println("You do not have enough mana.");
				return;
			}
			player.mps = player.mps - 6;
			if(Math.random() > .8) {
				System.out.println("Sneak failed! No suprise attack.");
				return;
			}
			else if(Math.random() > .4) {
				System.out.println("Supriiiiiiiiiise, sucker!");
				player.attack(player, mon);
				player.attack(player, mon);
				return;
			}
			else if(Math.random() > .4) {
				player.attack(player, mon);
				return;
			}
			else {
				System.out.println("'How could I miss?!' (Attack failed)");
			}
			return;
		}
	}
}
