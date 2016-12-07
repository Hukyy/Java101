package zombies;

public class Shotgun extends ToShoot{

	public Shotgun() {
		super(25, 10);
		// TODO Auto-generated constructor stub
	}
	public String toString(){
		return "Shotgun " + damage + " Durability " + durability;
	}
}
