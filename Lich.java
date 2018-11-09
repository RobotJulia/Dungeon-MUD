import java.util.ArrayList;
import java.util.Scanner;

public class Lich extends Monster{

	Lich(String name) {
		super(name);
		hps = 250;
		fullHP = 250;
		mps = 75;
		fullMP = 75;
		attSpeed = 2;
		chanceToHit = .6;
		minDamage = 30;
		maxDamage = 60;
		chanceToHeal = .1;
		minHeal = 35;
		maxHeal = 70;
		spellDamage = 90;
		xps = 100;
	}

	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Life Drain");
		grimoire.add("Poision");
		return grimoire;
	}

	@Override
	public String toString() {
		return "Lich";
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
			if(Math.random() > .6) {
				int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
				System.out.println("The Lich attacks causing " + damRoll + " damage.");
				System.out.println("The Lich absorbs " + damRoll + " hitpoints.");
				monster.hps = monster.hps + damRoll;
				if(monster.hps > monster.fullHP) {
					monster.hps = monster.fullHP;
				}
				
				player.hps = player.hps - damRoll;
				if(player.hps <= 0) {
					MainDriver.gameOver(new Scanner(System.in), player);
			}
			else {	
				damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
				System.out.println("The Lich attacks causing " + damRoll + " damage.");
				player.hps = player.hps - damRoll;
				if(player.hps <= 0) {
					MainDriver.gameOver(new Scanner(System.in), player);
				}
			}
		}
		else {
			System.out.println(monster.name + " attacked but missed!");
		}
	}	
	}

}
