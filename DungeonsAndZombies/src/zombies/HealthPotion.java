package zombies;

import java.util.Random;

public class HealthPotion extends Treasure {

	int amount;
	public HealthPotion(int amount){
		this.amount=amount;
	}
	public HealthPotion(HealthPotion other){
		this.amount=other.amount;
	}
	@Override
	public void use(Hero hero) {
		System.out.println("Healing potion " + this.amount);
		hero.takeHealing(this.amount);
	}

	
	public static Treasure loot() {
		Random random = new Random();
		int amountt = random.nextInt(100) + 50;
		return new HealthPotion(amountt);
	}

}
