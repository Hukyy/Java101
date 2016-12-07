package zombies;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int zombiesCount = input.nextInt();
		int zombiesHealth = input.nextInt();
		int hitCount = 0;
		
		
		
		HashMap<String, Weapon> weapons = new HashMap<>();
		
		weapons.put("axe", new Axe());
		weapons.put("shotgun", new Shotgun());
		weapons.put("sword", new Sword());
		weapons.put("revolver", new Revolver());

		Weapon weapon = weapons.get(input.next());

		for (int i = 0; i < zombiesCount; i++) {
			int oneZombieHealth = zombiesHealth;
			while (oneZombieHealth > 0) {
				oneZombieHealth -= weapon.hit();
				hitCount += 1;
			}
		}

		System.out.println(hitCount);

		input.close();
	}
}
