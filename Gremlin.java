import java.util.ArrayList;
import java.util.Scanner;

public class Gremlin extends Monster {

	Gremlin(String name) {
		super(name);
		hps = 95;
		mps = 0;
		fullHP = 70;
		chanceToHit = .8;
		chanceToHeal = .4;
		chanceToBlock = .2;
		minDamage = 15;
		maxDamage = 30;
		minHeal = 20;
		maxHeal = 40;
		attSpeed = 5;
		xps = 60;
		pChance = .4;	// Blind
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		grimoire.add("Blind");
		return grimoire;
	}

	@Override
	public String toString() {
		return "Gremlin";
	}
	
	@Override
	protected void attack(Hero player, Monster monster) {
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
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println(monster.name + " attacks with both claws dealing " + damRoll + " damage.");
			player.hps = player.hps - damRoll;
			if(player.isBlind == false && pChance > Math.random()) {
				System.out.println(player.name + " is blinded!");
				player.isBlind = true;
			}
			
			if(player.hps <= 0) {
				MainDriver.gameOver(new Scanner(System.in), player);
			}
		
		else {
			System.out.println(monster.name + " attacked but missed!");
		}
	}

}
