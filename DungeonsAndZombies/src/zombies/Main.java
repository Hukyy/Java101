package zombies;

public class Main {

	public static void main(String[] args) {
		Hero Hukyy = new Hero("Hukyy","noob",1500,300,2);
//		System.out.println(Hukyy.getHealth());
//		Hukyy.takeDamage(50);
//		System.out.println(Hukyy.getHealth());
//		Hukyy.takeHealing(800);
//		System.out.println(Hukyy.getHealth());
//		//System.out.println(Hukyy.knownAs());
//		
//		Weapon bradva = new Weapon("Axe",30);
//		Hukyy.equip(bradva);
//		Hukyy.takeDamage(Hukyy.attack("weapon"));
//		System.out.println(Hukyy.getHealth());
		String[] map = { "S.##.....T","#T##..###.","#.###E###E", "#.E...###.", "###T#####G"};
//		Spell spell = new Spell("fireBall",20,1,1);
//		Hukyy.learn(spell);
//		System.out.println(Hukyy.getSpell());
	
		
		Dungeon dung = new Dungeon(map);
		dung.printMap();
		System.out.println(dung.spawn(Hukyy));
		System.out.println();
		dung.printMap();
		dung.moveHero("right");
		System.out.println();
		dung.printMap();
		System.out.println();
		dung.moveHero("down");
		dung.printMap();
		System.out.println(Hukyy.getWeapon());
		System.out.println(Hukyy.getSpell());
		
		
	
		
		

	}

}
