import java.util.ArrayList;
import java.util.Scanner;

public class DarkWizard extends Monster {

	DarkWizard(String name) {
		super(name);
		hps = 1000;
		fullHP = 1000;
		mps = 150;
		fullMP = 150;
		chanceToBlock = .25;
		chanceToHit = .7;
		chanceToHeal = .05;
		attSpeed = 5;
		minDamage = 20;
		maxDamage = 40;
		spellDamage = 55;
		inventory.add("Antidote");
		
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Magic Missile");
		grimoire.add("Toad");
		grimoire.add("Summon Dragon");
		return grimoire;
	}

	@Override
	public void attack(Scanner kb, ArrayList<Character> enemies, Character player) {
		
		Monster monster = (Monster) enemies.get(0);
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println(monster.name + " heals " + healhps + " hitpoints.");
			monster.hps = monster.hps + healhps;
			if(monster.hps > monster.fullHP) {
				monster.hps = monster.fullHP;
			}
			return;
		}
		if(player.chanceToBlock > Math.random()) {
			System.out.println(player.name + " blocks " + monster.name);
			return;
		}
		if(monster.mps > 100) {
			monster.mps = monster.mps - 100;
			System.out.println(monster.name + " casts summon dragon! A white dragon appears!");
			enemies.add(new WhiteDragon("White Dragon"));
		}
		else if(monster.hps > 25) {
			if(player.isFrog) {
				monster.mps = monster.mps - 5;
				int damRoll = monster.minDamage + (int)(Math.random() * (monster.spellDamage - monster.minDamage));
				System.out.println(monster.name + " casts magic missile dealing " + damRoll + " damage.");
				player.hps = player.hps - damRoll;
				if(player.hps <= 0) {
					MainDriver.gameOver(new Scanner(System.in), player);
				}		
			}
			else {
			monster.mps = monster.mps - 25;
			System.out.print(monster.name + " casts toad!");
			if(player.chanceToBlock > Math.random()) {
				System.out.println(" but you dodge the attack!");
			}
			else {
				System.out.println(" You are turned into a toad! How tragic!");
				player.isFrog = true;
			}
			}
		}
		else if(monster.mps >= 5) {
			monster.mps = monster.mps - 5;
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.spellDamage - monster.minDamage));
			System.out.println(monster.name + " casts magic missile dealing " + damRoll + " damage.");
			
		}
		
		else if(monster.mps < 5) {
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println(monster.name + " attacks with a lightning staff causing " + damRoll + " damage.");
			player.hps = player.hps - damRoll;
			if(player.hps <= 0) {
				MainDriver.gameOver(new Scanner(System.in), player);
			}
		}
		else {
			System.out.println(monster.name + " attacked but missed!");
		}
	}

	@Override
	protected void attack(Hero player, Monster monster) {
		// not used for boss fight
		System.out.println("Wrong fighting mechanic.");
	}

}
