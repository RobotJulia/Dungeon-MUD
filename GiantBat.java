import java.util.ArrayList;
import java.util.Scanner;

public class GiantBat extends Monster {

	GiantBat(String name) {
		super(name);
		hps = 95;
		fullHP = 95;
		mps = 25;
		fullMP = 25;
		attSpeed = 2;
		chanceToHit = .6;
		minDamage = 30;
		maxDamage = 60;
		chanceToHeal = .1;
		minHeal = 30;
		maxHeal = 60;
		spellDamage = 70;
		xps = 30;
		pChance = .6;	// Blaze level 1
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		grimoire.add("Blaze");
		return grimoire;
	}

	@Override
	public String toString() {
		return "Giant Bat";
	}
	
	@Override
	protected void attack(Hero player, Monster monster) {
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println(monster.name + " heals " + healhps + " hitpoints");
			monster.hps = monster.hps + healhps;
			if(monster.hps > monster.fullHP) {
				monster.hps = monster.fullHP;
			}
		}
		if(monster.chanceToHit > Math.random()) {
			if(player.chanceToBlock > Math.random()) {
				System.out.println(player.name + " blocks " + monster.name);
				return;
			}
			if(Math.random() > pChance) {
				System.out.print(monster.name + " casts blaze level 1!");
				if(monster.mps - 5 <= 0) {
					System.out.println(" but cannot cast the spell!");
				}
				else {
					int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.spellDamage));
					player.hps = player.hps - damRoll;
					monster.mps = monster.mps - 5;
					System.out.println(" dealing " + damRoll + " damage.");
					if(player.hps <= 0) {
						MainDriver.gameOver(new Scanner(System.in), player);
					}
					return;
				}
				
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println("The giant bat swoops in dealing " + damRoll + " damage.");
			player.hps = player.hps - damRoll;
			if(player.hps <= 0) {
				MainDriver.gameOver(new Scanner(System.in), player);
			}
		}
		else {
			System.out.println(monster.name + " attacked but missed!");
		}
	}
		
	}
	
	@Override
	public ArrayList<String> getInventory() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("small healing potion");
		return list;
	}
	
}	
	

