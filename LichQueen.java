import java.util.ArrayList;
import java.util.Scanner;

public class LichQueen extends Monster{

	LichQueen(String name) {
		super(name);
		hps = 550;
		fullHP = 550;
		mps = 75;
		fullMP = 75;
		attSpeed = 4;
		chanceToHit = .6;
		minDamage = 30;
		maxDamage = 60;
		chanceToHeal = .1;
		minHeal = 35;
		maxHeal = 70;
		spellDamage = 90;
		xps = 700;
		monies = 110 + (int)(Math.random() * 40);
		inventory.add("Sword of the Leech");
	}

	@Override
	public String toString() {
		return "Lich King";
	}
	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Life Drain");
		grimoire.add("Poision");
		//grimoire.add("Chaos");
		//grimoire.add("Summon Lich");
		return grimoire;
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
		if(monster.chanceToHit < Math.random()) {
			if(player.chanceToBlock > Math.random()) {
				System.out.println(player.name + " blocks " + monster.name);
				return;
			}
			if(Math.random() > .6) {
				int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
				System.out.println("The Lich King attacks causing " + damRoll + " damage.");
				System.out.println("The Lich King absorbs " + damRoll + " hitpoints.");
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
				System.out.println("The Lich King attacks causing " + damRoll + " damage.");
				player.hps = player.hps - damRoll;
				if(Math.random() > .6) {
					System.out.println("You have been poisioned!");
					player.isPoisioned();
				}
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
