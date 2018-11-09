import java.util.ArrayList;
import java.util.Scanner;

abstract class Monster extends Character {
	double chanceToHeal;
	int minHeal;
	int maxHeal;
	double pChance;
	int spellDamage;
	int monies = 10 + (int)(Math.random() * 40);
	Monster(String name) {
		super(name);
	}

	@Override
	protected abstract ArrayList<String> getSpellBook();

	@Override
	protected void heal() {
		int healed = this.minHeal + (int)(Math.random() * (this.maxHeal - this.minHeal));
		System.out.println(this.name + " heals " + healed + " hitpoints.");
		this.hps = this.hps + healed;
		if(this.hps > this.fullHP) {
			this.hps = this.fullHP;
		}
	}

	public void attack(Scanner kb, Hero player, ArrayList<Monster> enemies) {
		System.out.println("This method is reserved for the boss fight.");
	}

	public void attack(Scanner kb, ArrayList<Character> enemies, Character player) {
		System.out.println("Monster class attack accessed. Whoops.");	
	}
	
	public void setInventory(Monster monster) {
		for(int i = 0; i < 3; i++) {
			if((boolean)(Math.random() > .5)) {
				monster.inventory.add("small potion");
			}
			else if((boolean)(Math.random() > .5)) {
				monster.inventory.add("antidote");
			}
			else if((boolean)(Math.random() > .6)) {
				monster.inventory.add("maiden's kiss");
			}
			
			
			
			
		}
		
		
		
		
	}
}
