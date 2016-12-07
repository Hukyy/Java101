package zombies;

public class Revolver extends ToShoot {

	public Revolver() {
		super(15, 6);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "Revolver " + damage + " Durability " + durability;
	}

}
