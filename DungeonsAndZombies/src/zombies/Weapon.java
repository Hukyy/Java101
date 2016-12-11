package zombies;

import java.util.Random;

public class Weapon extends Treasure {
	private String name;
	private int damage;
	
	public Weapon(String name, int damage){
		this.name=name;
		this.damage=damage;
	}
	
	public Weapon(Weapon weapon) {
		this.name = weapon.name;
		this.damage = weapon.damage;
	}

	public int getDamage(){
		return damage;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return name + " " + damage + " damage";
	}

	@Override
	public void use(Hero hero) {
		System.out.print("Weapon: ");
		System.out.println(this);
		if (hero.getWeapon() == null || this.damage>hero.getWeapon().getDamage()){
			hero.equip(this);
		}
		
	}

	
	public static Treasure loot() {
		String[] names = {	"Storm",
							"Blade of the Grave",
							"Despair",
							"Military Skewer",
							"Protector's Defender",
							"Singed Skeletal Slicer",
							"Vengeance Gold Claymore",
							"Valkyrie, Glory of Corruption",
							"Hell's Scream, Saber of the Lone Wolf",
							"Blazeguard, Favor of Suffering's End"};
		Random random = new Random();
		String name = names[random.nextInt(names.length)];
		int damage = random.nextInt(100) + 30; //damage range [30,130)
		

		return new Weapon(name,damage);
	}
}
