package zombies;

public class Weapon {
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
}