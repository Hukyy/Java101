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
		Weapon bradva = new Weapon("Axe",30);
		Hukyy.equip(bradva);
//		Hukyy.takeDamage(Hukyy.attack("weapon"));
//		System.out.println(Hukyy.getHealth());
		String[] map = { "SS##.....T","#.##..###.","#.###E###E", "#....E###.", "###T#####G"};
		Spell spell = new Spell("fireBall",50,250,3);
		Hukyy.learn(spell);
		System.out.println(Hukyy.getSpell());
	
		
		Dungeon dung = new Dungeon(map);
		dung.printMap();
		System.out.println(dung.spawn(Hukyy));
//		System.out.println();
//		dung.printMap();
		dung.moveHero("right");
		dung.printMap();
		dung.moveHero("down");
		dung.printMap();
		dung.moveHero("down");
		dung.printMap();
		dung.moveHero("down");
		dung.printMap();
		dung.moveHero("right");
		dung.printMap();
		dung.heroAttack("magic");
		dung.printMap();
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("right");
		dung.moveHero("right");
		dung.moveHero("right");
		dung.moveHero("right");
		dung.printMap();
		dung.moveHero("right"); 
		dung.printMap();
		dung.moveHero("down");
		dung.printMap();
		dung.moveHero("up");
		dung.printMap();
		dung.moveHero("right"); 
		dung.printMap();
		dung.moveHero("right");
		dung.printMap();
		dung.moveHero("up"); 
		dung.printMap();
		dung.moveHero("up"); 
		dung.printMap();
		dung.moveHero("up");
		dung.printMap();
		dung.moveHero("right");
		dung.printMap();
		dung.moveHero("right"); 
		dung.printMap();
		dung.moveHero("right"); 
		dung.moveHero("right");
		dung.moveHero("down");
		dung.moveHero("down"); 
		dung.printMap();
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("down");
		dung.moveHero("right");
		dung.moveHero("right");
		dung.moveHero("right");
		dung.moveHero("right");
//		
		//dung.heroAttack("magic");
		dung.printMap();
//		System.out.println(Hukyy.getWeapon());
//		System.out.println(Hukyy.getSpell());
		
		
	
		
		

	}

}
