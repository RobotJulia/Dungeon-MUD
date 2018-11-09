import java.util.ArrayList;
import java.util.Scanner;

public class Druid extends Hero {

	Druid(String name) {
		super(name);
		hps = 100;
		mps = 40;
		fullHP = 100;
		fullMP = 40;
		monies = 100;
		attSpeed = 6;
		chanceToHit = .8;
		chanceToBlock = .4;
		minDamage = 25;
		maxDamage  = 40;
		
	}
		
	public int castMagic(Scanner kb, ArrayList<Character> kobolds, Character player, boolean roomWon) {
		String choice = "";
		ArrayList<String> spellBook = getSpellBook();
		System.out.println("Spells: ");
		for(int i = 0; i < (spellBook.size()); i++) {
			System.out.println((i+1) + " " + spellBook.get(i));
		}
		System.out.println("Cast which spell?");
		choice = kb.nextLine();
		if(choice.equals("1")) {
			if(player.mps - 7 < 0) {
				System.out.println("You do not have enough mana.");
				return 0;
			}
			player.mps = player.mps - 7;
			player = new Werewolf(player.name);
			System.out.println("You transmute into a werewolf!!");
			System.out.println("                              __\r\n" + 
					"                             .d$$b\r\n" + 
					"                           .' TO$;\\\r\n" + 
					"                          /  : TP._;\r\n" + 
					"                          |  :  DO.>\r\n" +
					"                         / _.;  :Tb|\r\n" + 
					"                        /   /   ;j$>\r\n" + 
					"                    _.-\"       d$$$$\r\n" + 
					"                  .' ..       d$$$$;\r\n" + 
					"                 /  /P'      d$$$$P. |\\\r\n" + 
					"                /   \"      .d$$$P' |\\^\"l\r\n" + 
					"              .'           `T$P^\"\"\"\"\"  :\r\n" + 
					"          ._.'      _.'                ;\r\n" + 
					"       `-.-\".-'-' ._.       _.-\"    .-\"\r\n" + 
					"     `.-\" _____  ._              .-\"\r\n" + 
					"    -(.g$$$$$$$b.              .'\r\n" + 
					"      \"\"^^T$$$P^)            .(:\r\n" + 
					"        _/  -\"  /.'         /:/;\r\n" + 
					"     ._.'-'`-'  \")/         /;/;\r\n" + 
					"  `-.-\"..--\"\"   \" /         /  ;\r\n" + 
					" .-\" ..--\"\"        -'          :\r\n" + 
					" ..--\"\"--.-\"         (\\      .-(\\\r\n" + 
					"   ..--\"\"              `-\\(\\/;`\r\n" + 
					"     _.                      :\r\n");
		}
		return player.xps;
	}

	@Override
	public ArrayList<String> getSpellBook() {
		ArrayList<String> grimoire = new ArrayList<String>();
		grimoire.add("Transmute Werewolf");
		return grimoire;
	}

	@Override
	public void castMagic(Scanner kb, Hero player, Monster mon) {
		String choice = "";
		System.out.println("Which spell would you like to cast?");
		ArrayList<String> spellBook = player.getSpellBook();
		for(int i = 0; i < spellBook.size(); i++) {
			System.out.println((i+1) + " " + spellBook.get(i));
		}
		choice = kb.nextLine();
		if(choice.equals("1")) {
			if(player.mps - 7 < 0) {
				System.out.println("You do not have enough mana.");
				return;
			}
			player.mps = player.mps - 7;
			player = new Werewolf(player.name);
			System.out.println("You transmute into a werewolf!!");
			System.out.println("                              __\r\n" + 
					"                             .d$$b\r\n" + 
					"                           .' TO$;\\\r\n" + 
					"                          /  : TP._;\r\n" + 
					"                          |  :  DO.>\r\n" +
					"                         / _.;  :Tb|\r\n" + 
					"                        /   /   ;j$>\r\n" + 
					"                    _.-\"       d$$$$\r\n" + 
					"                  .' ..       d$$$$;\r\n" + 
					"                 /  /P'      d$$$$P. |\\\r\n" + 
					"                /   \"      .d$$$P' |\\^\"l\r\n" + 
					"              .'           `T$P^\"\"\"\"\"  :\r\n" + 
					"          ._.'      _.'                ;\r\n" + 
					"       `-.-\".-'-' ._.       _.-\"    .-\"\r\n" + 
					"     `.-\" _____  ._              .-\"\r\n" + 
					"    -(.g$$$$$$$b.              .'\r\n" + 
					"      \"\"^^T$$$P^)            .(:\r\n" + 
					"        _/  -\"  /.'         /:/;\r\n" + 
					"     ._.'-'`-'  \")/         /;/;\r\n" + 
					"  `-.-\"..--\"\"   \" /         /  ;\r\n" + 
					" .-\" ..--\"\"        -'          :\r\n" + 
					" ..--\"\"--.-\"         (\\      .-(\\\r\n" + 
					"   ..--\"\"              `-\\(\\/;`\r\n" + 
					"     _.                      :\r\n");
			
		}
	
	} 
}