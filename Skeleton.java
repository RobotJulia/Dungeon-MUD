import java.util.ArrayList;
import java.util.Scanner;

public class Skeleton extends Monster {

	Skeleton(String name) {
		super(name);
		hps = 120;
		mps = 25;
		fullHP = 100;
		fullMP = 25;
		attSpeed = 3;
		chanceToHit = .8;
		chanceToHeal = .3;
		chanceToBlock = .2;
		minDamage = 30;
		maxDamage = 50;
		minHeal = 30;
		maxHeal = 50;
		xps = 75;
				
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Heal");
		return grimoire;
	}
	
	@Override
	public String toString() {
		return "Skeleton";
	}

	@Override
	protected void attack(Hero player, Monster monster) {
		if(monster.chanceToHeal > Math.random()) {
			int healhps = monster.minHeal + (int)(Math.random() * (monster.maxHeal - monster.minHeal));
			System.out.println("Skeleton heals " + healhps + " hitpoints");
			monster.hps = monster.hps + healhps;
			if(monster.hps > monster.fullHP) {
				monster.hps = monster.fullHP;
			}
			return;
		}	
		
		if(monster.chanceToHit > Math.random()) {
			if(player.chanceToBlock > Math.random()) {
				System.out.println(player.name + " blocks " + monster.name);
				return;
			}
			int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
			System.out.println("The Skeleton swings his rusty blade dealing " + damRoll + " damage.");
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
