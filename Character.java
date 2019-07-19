import java.util.ArrayList;
import java.util.Scanner;

public abstract class Character {
	protected String name = "";
	protected int hps;
	protected int fullHP;
	protected int fullMP;
	protected int level = 1;
	protected int xps;
	protected int xpLimit = 50;
	protected int mps;
	protected int monies;
	protected int attSpeed;
	protected int maxDamage;
	protected int minDamage;
	protected int spellDamage;
	protected double chanceToHit;
	protected double chanceToBlock;
	protected int minHeal;
	protected int maxHeal;
	protected int chanceToHeal;
	protected double pChance;
	protected boolean isPoisioned = false;
	protected boolean isFrog = false;
	protected boolean isBlind = false;
	protected int minFrogDamage;
	protected int maxFrogDamage;
	
	
	Character(String name) {
		this.name = name;
	}
	
	protected ArrayList<String> inventory = new ArrayList<String>();
	
	protected abstract ArrayList<String> getSpellBook();
	
	protected void levelUp() {
		if(xps >= xpLimit) {
			System.out.println("You have levelled up! Yay!");
			level++;
			if (xpLimit >= xpLimit * 2) {
				xpLimit = xpLimit *2;
				levelUp();
			}
			xpLimit = xpLimit * 2;
			
			this.fullHP = this.fullHP + 1 + (int)(Math.random() * 3);
			this.hps = this.fullHP;
			this.fullMP = this.fullMP + 1 + (int)(Math.random() * 4);
			this.mps = this.fullMP;
			this.maxDamage = this.maxDamage + 1 + (int)(Math.random() * 3);
			this.minDamage = this.minDamage + 1 + (int)(Math.random() * 3);
			this.spellDamage = this.spellDamage + 1 + (int)(Math.random() * 5);
			
		}
	}

	protected boolean isPoisoned() {
		this.hps = this.hps - 4;
		System.out.println("You lose 4 life from the poison.");
		return isPoisoned();	
	}

	protected boolean isFrog(Character player) {
		this.minFrogDamage = this.minDamage;
		this.maxFrogDamage = this.maxDamage;
		this.minDamage = this.minDamage /4;
		this.maxDamage = this.maxDamage /4;
		return isFrog(player);	
	}
	
	protected abstract void attack(Hero player, Monster monster);	
	
	protected void heal() {
		int healed = this.minHeal + (int)(Math.random() * (this.maxHeal - this.minHeal));
		System.out.println(this.name + " heals " + healed + " hitpoints.");
		this.hps = this.hps + healed;
		if(this.hps > this.fullHP) {
			this.hps = this.fullHP;
		}
	}

	protected void attack(Scanner kb, ArrayList<Character> enemies, Character player) {
		System.out.println("reserved for the boss fight.");
	}

}
