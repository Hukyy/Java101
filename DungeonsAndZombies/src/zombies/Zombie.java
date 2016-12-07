package zombies;

public class Zombie extends Character {
	private int damage;

	public Zombie(int health, int mana, int damage) {
		super(health, mana);
		this.damage = damage;
	}
	
	public int attack(String how){
		int result = damage;
		if (how.equalsIgnoreCase("weapon") && weapon != null){
			result = weapon.getDamage();
		}
		if (how.equalsIgnoreCase("magic") && spell != null && canCast()){
			result = spell.getDamage();
			this.mana -= spell.getManaCost();
		}
		return result;
	}

}