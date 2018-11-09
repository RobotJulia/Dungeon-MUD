import java.util.ArrayList;
import java.util.Scanner;

public class WhiteDragon extends Monster {

	WhiteDragon(String name) {
		super(name);
		hps = 200;
		mps = 120;
		fullHP = 175;
		chanceToHit = .8;
		chanceToHeal = .25;
		chanceToBlock = .25;
		attSpeed = 4;
		minDamage = 25;
		maxDamage = 60;
		minHeal = 20;
		maxHeal = 60;
		attSpeed = 4;
		xps = 175;
		pChance = .6;	// Holy Nova
		spellDamage = 75;
	}

	@Override
	public String toString() {
		return "White Dragon";
	}
	@Override
	protected ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Holy Nova");
		return null;
	}

	@Override
	public void attack(Scanner kb, ArrayList<Character> monsters, Character player) {
		try {
			Monster monster = (Monster) monsters.get(1);
		
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
				if(monster.pChance > Math.random()) {
					if(monster.mps >= 30) {
						monster.mps = monster.mps - 30;
						int damRoll = monster.minDamage + (int)(Math.random()* (monster.spellDamage - monster.minDamage));
						System.out.println(monster.name + " casts Holy Nova! dealing " + damRoll + " damage.");
						player.hps = player.hps - damRoll;
						if(player.hps <= 0) {
							MainDriver.gameOver(new Scanner(System.in), player);
						}
					}
				}
				else if(monster.chanceToHit > Math.random()) {
					int damRoll = monster.minDamage + (int)(Math.random() * (monster.maxDamage - monster.minDamage)); 
					System.out.println(monster.name + " attacks dealing " + damRoll + " damage.");
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
		catch (IndexOutOfBoundsException e) {
			Monster monster = (Monster) monsters.get(0);
		}
		
	}
	
	@Override
	protected void attack(Hero player, Monster monster) {
		System.out.println("Not used in this battle.");
	}
	

}
