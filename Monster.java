import java.util.ArrayList;
import java.util.Scanner;

abstract class Monster extends Character {
	
	int hps;
	int mps;
	int fullHP;
	int fullMP;
	int minHeal;
	int maxHeal;
	double pChance;
	int spellDamage;
	int attSpeed;
	double chanceToHit;
	double chanceToHeal;
	double chanceToBlock;
	int minDamage = 30;
	int maxDamage = 60;
	int xps;

	int monies = 10 + (int)(Math.random() * 40);
	
	Monster(String name) {
		super(name);
	}
	
	protected abstract ArrayList<String> getSpellBook();

	protected void heal() {
		int healed = this.minHeal + (int)(Math.random() * (this.maxHeal - this.minHeal));
		System.out.println(this.name + " heals " + healed + " hitpoints.");
		this.hps = this.hps + healed;
		if(this.hps > fullHP) {
			this.hps = fullHP;
		}
	}

	public void attack(Scanner kb, Hero player, ArrayList<Monster> enemies) {
		System.out.println("This method is reserved for the boss fight.");
	}

	public void attack(Scanner kb, ArrayList<Character> enemies, Character player) {
		System.out.println("Monster class attack accessed. Whoops.");	
	}

	// Override if monster carries something
	public ArrayList<String> getInventory() {
		return null;
	}

	protected void attack(Hero player, Monster monster) {
		System.out.println("This method is always overridden as well.");
		
	}
	
}
