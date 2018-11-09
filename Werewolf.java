import java.util.ArrayList;
import java.util.Scanner;

public class Werewolf extends Hero {
	
	Werewolf(String name) {
		super(name);
		// hps not set here, inherited when the player transforms
		mps = 20;
		fullHP = 150;
		fullMP = 20;
		// monies also inherited
		attSpeed = 4;
		chanceToHit = .8;
		chanceToBlock = .4;
		minDamage = 35;
		maxDamage  = 60;
		
	}

	@Override
	public ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		return grimoire;
	}
	
	public int castMagic(Scanner kb, ArrayList<Character> enemies, Character player, boolean roomWon) {
		System.out.println("Werewolves are not exactly renowned for their arcane casting skills...");
		// None, this guy is already O/P
		return 0;
		
	}

	@Override
	public void castMagic(Scanner kb, Hero player, Monster mon) {
		// None, this guy is already O/P
		System.out.println("Werewolves are not exactly renowned for their arcane casting skills...");
	}
}
