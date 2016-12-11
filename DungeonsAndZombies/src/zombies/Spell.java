package zombies;

import java.util.Random;

public class Spell extends Treasure {
	private String name;
	private int damage;
	private int manaCost;
	private int castRange;
	public Spell(String name, int damage, int manaCost, int castRange){
		this.name=name;
		this.damage =damage;
		this.manaCost=manaCost;
		this.castRange=castRange;
	}
	public Spell(Spell other){
		this.name=other.name;
		this.damage=other.damage;
		this.manaCost=other.manaCost;
		this.castRange=other.castRange;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	public int getCastRange() {
		return castRange;
	}
	public void setCastRange(int castRange) {
		this.castRange = castRange;
	}
	public String toString(){
		return name + " Damage: " + damage + " manaCost: " + manaCost + " castRange: " + castRange;
	}
	@Override
	public void use(Hero hero) {
		System.out.print("Spell:");
		System.out.println(this);
		if (hero.getSpell() == null || this.damage>hero.getSpell().getDamage()){
			hero.learn(this);
		}
	}
	
	public static Treasure loot() {
		String[] names = {"Dragon Shower",
						"Sunlight Typhoon",
						"Life Burn",
						"Divinity",
						"Silence",
						"Beam of Life Draining",
						"Surge of Spirits",
						"Reprisal of Time",
						"Deflection of the Stars",
						"Corruption of Control"};
		Random random = new Random();
		String name = names[random.nextInt(names.length)];
		int damage = random.nextInt(200) + 50; //damage range [50,250)
		int manaNeed = random.nextInt(80)+20; // mana range [20,100)
		int spellRange = random.nextInt(3) + 1;
		
		return new Spell(name,damage,manaNeed,spellRange);
	}
		
	
	
}
