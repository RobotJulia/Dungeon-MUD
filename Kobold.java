import java.util.ArrayList;
import java.util.Scanner;

public class Kobold extends Monster {

	Kobold(String name) {
		super(name);
		hps = 80;
		fullHP = 80;
		attSpeed = 3;
		chanceToHit = .6;
		minDamage = 0;
		maxDamage = 26;
		chanceToHeal = .1;
		chanceToBlock = .4;
		minHeal = 20;
		maxHeal = 40;
		xps = 20;
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		return grimoire;
	}

	@Override
	public String toString() {
		return "Kobold";
	}

	@Override
	protected void attack(Hero player, Monster monster) {
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println("Kobold heals an amazing " + healhps + " hitpoints.");
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
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println("The Kobold attacks in a screaming rage dealing " + damRoll + " damage.");
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
	

