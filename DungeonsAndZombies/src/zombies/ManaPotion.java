package zombies;

import java.util.Random;

public class ManaPotion extends Treasure {

	int amount;
	public ManaPotion(int amount){
		this.amount=amount;
	}
	public ManaPotion(ManaPotion other){
		this.amount=other.amount;
	}
	@Override
	public void use(Hero hero) {
		System.out.println("Mana potion "+ this.amount);
		hero.takeMana(this.amount);
	}

	
	public static Treasure loot() {
		Random random = new Random();
		int amountt = random.nextInt(100) + 50;
		return new ManaPotion(amountt);
	}

}
