package zombies;

public class Hero extends Character {
	private String name;
	private String title;
	private int manaRegen;
	
	
	
	
	public Hero(String Name,String Title, int Health, int Mana, int ManaRegen){
		super(Health,Mana);
		this.name=Name;
		this.title=Title;
		this.manaRegen=ManaRegen;
		this.weapon = new Weapon("staff", 2);
	}
	
	public Hero(Hero other){
		super(other);
		this.name=other.name;
		this.title=other.title;
		this.manaRegen=other.manaRegen;
	}
	
	public String knownAs(){
		return name + " the " + title;
	}
	public int attack(String how){
		int result = 0;
		if (how.equalsIgnoreCase("weapon") && weapon != null){
			result = weapon.getDamage();
		}
		if (how.equalsIgnoreCase("magic") && spell != null && canCast()){
			result = spell.getDamage();
			this.mana -= spell.getManaCost();
		}
		return result;
	}
	public int getManaRegen(){
		return manaRegen;
	}
	
	
	
	
//	public void takeMana(int manaPoints){
//		//TODO: moving mana regen
//		mana += manaPoints;
//		mana = Math.min(maxMana, mana);
//	}
}
