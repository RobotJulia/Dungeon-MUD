import java.util.ArrayList;
import java.util.Scanner;

public class Ogre extends Monster {

	Ogre(String name) {
		super(name);
		hps = 200;
		fullHP = 200;
		attSpeed = 2;
		chanceToHit = .6;
		minDamage = 30;
		maxDamage = 60;
		chanceToHeal = .1;
		chanceToBlock = .25;
		minHeal = 30;
		maxHeal = 60;
		xps = 100;
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		return grimoire;
	}
	
	@Override 
	public String toString() {
		return "Ogre";
	}
	
	protected void attack(Hero player, Monster monster) {
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println(monster.name + " heals " + healhps + " hitpoints");
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
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println(monster.name + " swings his mighty club dealing " + damRoll + " damage.");
			player.hps = player.hps - damRoll;
			if(player.hps <= 0) {
				MainDriver.gameOver(new Scanner(System.in), player);
			}
		else {
			System.out.println(monster.name + " attacked but missed!");
		}
	}

}	
	

