package zombies;

import java.util.Random;

public class Treasure {
	private int[] healthPot = {15,30,70,56,48,32,55,96,120};
	private int[] manaPot = {15,30,70,56,48,32,55,96,120};
	private Weapon[] weapons = {
			new Weapon("Axe", 80), new Weapon("Spear",50),
			new Weapon("Sword",70), new Weapon("Mace",69)
	};
	private Spell[] spells = { 
			new Spell("Fireball",100,50,1), new Spell("IceBlast",100,50,1),
			new Spell("Lightning",100,50,1), new Spell("TonsOfDamage",500,50,1)
	};
	
	public int getHealthPot(){
		Random random = new Random();
		int index = random.nextInt(healthPot.length);
		return healthPot[index];
	}
	public int getManaPot(){
		Random random = new Random();
		int index = random.nextInt(manaPot.length);
		return manaPot[index];
	}
	public Weapon getWeapon(){
		Random random = new Random();
		int index = random.nextInt(weapons.length);
		return new Weapon(weapons[index]);
	}
	public Spell getSpell(){
		Random random = new Random();
		int index = random.nextInt(spells.length);
		return new Spell(spells[index]);
	}
}
