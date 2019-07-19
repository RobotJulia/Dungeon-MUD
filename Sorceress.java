import java.util.ArrayList;
import java.util.Scanner;

public class Sorceress extends Hero {

	Sorceress(String name) {
		super(name);
		hps = 75;
		mps = 50;
		fullHP = 75;
		fullMP = 50;
		monies = 100;
		attSpeed = 5;
		chanceToHit = .7;
		chanceToBlock = .3;
		minDamage = 25;
		maxDamage = 75;
		minHeal = 25;	// todo 
		maxHeal = 50;	// add these to level-up
		spellDamage = 100;
		pChance = .6;
	}

	public int castMagic(Scanner kb, ArrayList<Character> enemies, Character player, boolean roomWon) {
		String choice = "";
		int damageRoll = player.minDamage + (int)(Math.random() * (player.spellDamage - player.minDamage));
		ArrayList<String> spellBook = getSpellBook();
		System.out.println("Spells: ");
		for(int i = 0; i < (spellBook.size()); i++) {
			System.out.println((i+1) + " " + spellBook.get(i));
		}
		System.out.println("Which spell would you like to cast?");
		choice = kb.nextLine();
		
		if(choice.equals("1")) {
			if(player.mps - 5 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 5;
			System.out.println("Choose a target:");
			choice = kb.nextLine();
			int index = Integer.parseInt(choice)-1;
			
			Monster enemy = (Monster) enemies.get(index);
			System.out.println("You cast " + spellBook.get(0) + " at " + enemy.name + " dealing " + damageRoll + " damage.");
			enemy.hps = enemy.hps - damageRoll;
			
			if(enemy.hps <= 0) {
				player.xps = enemy.xps + player.xps;
				player.monies = enemy.monies + player.monies;
				
				ArrayList<String>inventory = enemy.getInventory();
				
				System.out.println(enemy.name + " is dead." );
				
				if(!enemy.inventory.isEmpty()) {
					System.out.println(enemy.name + " dropped " + enemy.inventory.toString());
					System.out.println("You retrieve " + enemy.inventory.toString());
				}
				
				enemies.remove(index);
				System.out.println("There are " + enemies.size() + " enemies left.");
			}
			else {
				System.out.println(enemy.name + " has " + enemy.hps + " hitpoints left.");
			}
		}
		
		if(choice.equals("2")) {
			if(player.mps - 4 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 4;
			System.out.println("Choose a target:");
			choice = kb.nextLine();
			int index = Integer.parseInt(choice)-1;
			Monster enemy = (Monster) enemies.get(index);
			System.out.println("You cast" + spellBook.get(1) + " on " + enemy.name);
			if(Math.random() > .4) {
				enemy.maxDamage = enemy.maxDamage - (int)(Math.random() * 15);
				System.out.println(enemy.name + " has been weakened.");
			}
			else {
				System.out.println("but " + enemy.name + " resists.");
			}
		}
		
		if(choice.equals("3")) {
			if(player.mps - 4 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 4;
			System.out.println("Choose a target:");
			choice = kb.nextLine();
			int index = Integer.parseInt(choice)-1;
			Monster enemy = (Monster) enemies.get(index);
			System.out.println("You cast" + spellBook.get(2) + " on " + enemy.name);
			if(.4 > Math.random()) {
				System.out.println("You poision " + enemy.name);
				enemy.isPoisoned();
			}
			else {
				System.out.println("You try to poision " + enemy.name + " but fail.");
			}
		}
		
		if(choice.equals("4")) {
			if(player.mps - 6 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 6;
			player.heal();
		}	
		return player.xps;
	}
	
	
	@Override
	public ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Magic Missile");
		grimoire.add("Debuff");
		grimoire.add("Poision");
		grimoire.add("Heal");
		return grimoire;
	}
	
	@Override
	public void castMagic(Scanner kb, Hero player, Monster mon) {
		String choice = "";
		System.out.println("Which spell would you like to cast?");
		ArrayList<String> spells = player.getSpellBook();
		for(int i = 0; i< spells.size(); i++) {
			System.out.println((i+1) + " " + spells.get(i));
		}
		choice = kb.nextLine();
		if(choice.equals("1")) {
			if(player.mps - 5 > 0) {
				player.mps = player.mps - 5;
				
				if(player.chanceToHit > Math.random()) {
					int damRoll = player.minDamage + (int)(Math.random() * (player.spellDamage - player.minDamage));
					System.out.println("You cast magic missile at " + mon.name + " dealing " + damRoll + " damage.");
					mon.hps = mon.hps - damRoll;
					if(mon.hps <= 0) {
						player.xps = mon.xps + player.xps;
						player.monies = player.monies + mon.monies;
						player.inventory.addAll(mon.inventory);
						System.out.println(mon.name + " is dead." );
						if(!mon.inventory.isEmpty()) {
							System.out.println(mon.name + " dropped " + mon.inventory.toString());
							System.out.println("You retrieve " + mon.inventory.toString());
						}
						
					}
					else {
						System.out.println(mon.name + " has " + mon.hps + " hitpoints left.");
					}
				}
				else {
					System.out.println("You cast magic missile but missed " + mon.name);
				}
				
			}	
			else {
				System.out.println("You have insufficient mana.");
			}
		}
		if(choice.equals("2")) {
			if(player.mps - 4 > 0) {
				player.mps = player.mps - 4;
				System.out.println("You cast debuff on " + mon.name);
				if(Math.random() > .4) {
					int damMod = mon.maxDamage - (int)(Math.random() * 20);
					System.out.println(mon.name + " has been weakened. Max damage down by " + damMod);
					mon.maxDamage = mon.maxDamage - damMod;
				}
				else {
					System.out.println("but " + mon.name + " resists.");
				}
			}
			else {
				System.out.println("You have insufficient mana.");
			}
		}
		if(choice.equals("3")) {
			if(player.mps -4 > 0) {
				player.mps = player.mps - 4;
				if(.4 > Math.random()) {
					System.out.println("You poision " + mon.name);
					mon.isPoisoned();
				}
				else {
					System.out.println("You try to poision " + mon.name + " but fail.");
				}
			} else {
				System.out.println("You have insufficient mana.");
			}
		}
		if(choice.equals("4")) {
			if(player.mps - 6 > 0) {	
				player.mps = player.mps - 6;
				player.heal();
			} else {
				System.out.println("You have insufficient mana.");
			}
		}
	}
}
