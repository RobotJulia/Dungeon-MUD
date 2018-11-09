import java.util.ArrayList;
import java.util.Scanner;

public class Zombie extends Monster {

	Zombie(String name) {
		super(name);
		hps = 120;
		fullHP = 165;
		attSpeed = 2;
		chanceToHit = .55;
		minDamage = 30;
		maxDamage = 50;
		chanceToHeal = .2;
		minHeal = 30;
		maxHeal = 60;
		pChance = .6;	// poision
		xps = 120;
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		grimoire.add("Poision");
		return grimoire;
	}

	@Override
	public String toString() {
		return "Zombie";
	}
	
	@Override
	protected void attack(Hero player, Monster monster) {
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println(monster.name + " heals " + healhps + " hitpoints");
			monster.hps = monster.hps + healhps;
		}
		if(player.chanceToBlock > Math.random()) {
				if(monster.pChance > Math.random()) {
					player.isPoisioned = true;
					System.out.println(player.name + " is poisioned!");
				}
				int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
				System.out.println(monster.name + " attacks dealing " + damRoll + " damage.");
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
	

