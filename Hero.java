import java.util.ArrayList;
import java.util.Scanner;

public abstract class Hero extends Character {
	
	Hero(String name) {
		super(name);
	}
	
	protected void attack(Scanner kb, ArrayList<Character> baddies, Character player, boolean roomWon) {
		
		int n = baddies.size();
		String choice = "";
		int attRoll = player.minDamage + (int)(Math.random() * (player.maxDamage - player.minDamage));
		System.out.println("Choose a target: (1-" + n + ")");
		choice = kb.nextLine();
		try {
			int target = Integer.parseInt(choice)-1;
			Monster enemy = (Monster) baddies.get(target);
			enemy.hps = enemy.hps - attRoll;
			System.out.println("You have done " + attRoll + " damage to " + enemy.name);
			
			if(enemy.hps <= 0) {
				player.xps = enemy.xps + player.xps;
				player.monies = enemy.monies + player.monies;
				player.inventory.addAll(enemy.inventory);
				System.out.println(enemy.name + " is dead." );
				if(!enemy.inventory.isEmpty()) {
					System.out.println(enemy.name + " dropped " + enemy.inventory.toString());
					System.out.println("You retrieve " + enemy.inventory.toString());
				}
				baddies.remove(target);
				System.out.println("There are " + baddies.size() + " enemies left.");	
			}
			else {
				System.out.println(enemy.name + " has " + enemy.hps + " hitpoints left.");
			}
			if(baddies.isEmpty()) {
				roomWon = true;
				return;
			}
			
		}
		catch (Exception e) {
			System.out.println("Choose a valid target: ");
			attack(kb, baddies, player, roomWon);
		}
	}
	
	
	@Override
	protected void attack(Hero player, Monster monster) {
		int turns = 1;
		if(player.attSpeed > 2 * monster.attSpeed ) {
			turns = (player.attSpeed / monster.attSpeed);
			System.out.println("You get " + turns + " turns this round.");
		}
		for (int i = 0; i < turns; i++) {
				if(monster.chanceToBlock < Math.random()) {
				int attRoll = player.minDamage + (int)(Math.random() * (player.maxDamage - player.minDamage));
				System.out.println("You deal " + attRoll +  " damage to " + monster.name);
				monster.hps = monster.hps - attRoll;
				if(monster.hps <= 0) {
					player.xps = player.xps + monster.xps;
					player.monies = player.monies + monster.monies;
					player.inventory.addAll(monster.inventory);
					System.out.println("You have defeated " + monster.name);
					if(!monster.inventory.isEmpty()) {
						System.out.println(monster.name + " dropped " + monster.inventory.toString());
						System.out.println("You retrieve " + monster.inventory.toString());
					}
				}
				else {
					System.out.println(monster.name + " has " + monster.hps + " hitpoints left.");
				}
			}
			else {
				System.out.println(monster.name + " has blocked " + player.name + "'s attack!");
			}
			}
	}
	
	protected void investigate(Monster monster) {
		System.out.println(monster.name);
		System.out.println("HPs:" + monster.hps + "/" + monster.fullHP + " MPs:" + monster.mps + "/" + monster.fullMP);
		System.out.println("Abilities:" + monster.getSpellBook());
		System.out.println("Inventory: " + monster.inventory.toString());
	}

	@Override
	protected abstract ArrayList<String> getSpellBook();

	protected abstract int castMagic(Scanner kb, ArrayList<Character> baddies, Character player, boolean roomWon);

	protected abstract void castMagic(Scanner kb, Hero player, Monster mon);

	protected void useItem(Scanner kb, Hero player) {
		String choice = "";
		int healRoll = 50 + (int)(Math.random() * 50); 
		
		System.out.println("Inventory: ");
		for(int i=0; i < inventory.size(); i++) {
			System.out.println((i+1) + " " + player.inventory.get(i));
		}
		System.out.println("Which item to use?");
		choice = kb.nextLine();
		try {
			int itemNum = Integer.parseInt(choice);
			choice = player.inventory.get(itemNum-1);
			if(player.inventory.contains(choice)) {
				if(choice.equals("small potion")) {	
					System.out.println("You use the potion healing " + healRoll + " hitpoints.");
					player.inventory.remove("small potion");
					player.hps = player.hps + healRoll;
					if(player.hps > player.fullHP) {
						player.hps = player.fullHP;
					}		
				}
				if(choice.equals("antidote")) {
					System.out.println("You are no longer poisioned.");
					player.inventory.remove("antidote");
					player.isPoisioned = false;
				}
				if(choice.equals("maiden's kiss")) {
					if(player.isFrog) {
						player.isFrog = false;
						player.minDamage = player.minFrogDamage;
						player.maxDamage = player.maxFrogDamage;
					}
					player.inventory.remove("maiden's kiss");
					System.out.println("You are no longer a frog.");
				}
				if(choice.equals("eyedrops")) {
					if(player.isBlind) {
						player.chanceToHit = player.chanceToHit + .1;
						player.chanceToBlock = player.chanceToBlock + .1;
					}
					player.inventory.remove("eyedrops");
					player.isBlind = false;
					System.out.println("You are no longer blind.");
				}
				if(choice.equals("small sword")) {
					System.out.println("You equip the small sword gaining 5 maximum damage.");
					player.inventory.remove("small sword");
					player.maxDamage = player.maxDamage + 5;
				}
				if(choice.equals("small labrys")) {
					System.out.println("You equip the small labrys. You gain 4 maximum damage.");
					player.inventory.remove("small labrys");
					player.maxDamage = player.maxDamage + 4;
				}
				if(choice.equals("headgear")) {
					System.out.println("You gain +1 to your speed.");
					player.inventory.remove("headgear");
					player.attSpeed++;
				}
				if(choice.equals("elbowpads")) {
					System.out.println("Your magic points go up by 5.");
					player.inventory.remove("elbowpads");
					player.fullMP = player.fullMP + 5;	
				}
				if(choice.equals("small charm")) {
					int plusMps = 2 + (int)(Math.random() * 8);
					System.out.println("You use the magic charm and gain " + plusMps + " magic points and recover all magic points!");
					player.inventory.remove("small charm");
					player.fullMP = player.fullMP + plusMps;
					player.mps = player.fullMP;
				}
				if(choice.equals("metal rod")) {
					System.out.println("Your magic points go up by 4.");
					player.inventory.remove("metal rod");
					player.fullMP = player.fullMP + 4;
				}
				if(choice.equals("woodstaff")) {
					System.out.println("Your magic points go up by 3.");
					player.inventory.remove("woodstaff");
					player.fullMP = player.fullMP + 3;
				}
				if(choice.equals("feather")) {
					int mps = 25 + (int)(Math.random() * 25);
					System.out.println("You gain " + mps + " magic points." );
					player.inventory.remove("feather");
				}
			} else {
				System.out.println("Sorry, that item is not in your inventory.");
			}
		}
		catch (Exception e) {
			System.out.println("Please enter a valid choice.");
		}
	}

	protected boolean isBlind() {
		this.chanceToBlock = this.chanceToBlock - .1;
		this.chanceToHit = this.chanceToHit - .1;
		return isBlind;
	}
	
	protected boolean isPoisioned() {
		this.hps = this.hps - 4;
		System.out.println("You lose 4 life from the poision.");
		return isPoisioned;	
	}
	
}
