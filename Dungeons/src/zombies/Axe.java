package zombies;

public class Axe extends ToSmash{

	public Axe() {
		super(10, 50);
		maxDurability = 50;
	}
	public String toString(){
		return "Axe " + damage + " Durability " + durability;
	}
}
