package zombies;
import java.util.HashMap;
import java.util.Random;

public class Dungeon {
	private char[][] map;
	private Hero hero;
	private Hero orig_hero;
	private int hero_x;
	private int hero_y;
	private char lastFromThePath = '.';
	private HashMap<Position, Zombie> enemies = new HashMap<>();
	private Position lastMetZombie;
	private boolean stillRunning = true;
	
	private boolean isObstacle(int x,int y){
		return map[x][y] == '#';
	}
	private boolean isWalkable(int x,int y){
		return map[x][y] != '#';
	}
	private boolean isInside(int x,int y){
		int height = map.length;
		if (x<0 || x>height-1) 
			return false;
		
		int width = map[x].length;
		if (y<0 || y>width-1)
			return false;
		
		return true;
	}
	private boolean revive(){
		if (!hero.isAlive()){
			map[hero_x][hero_y] = '.';
			return spawn(orig_hero);			
		}
		return true;
	}
	private void getTreasure(){
		Treasure treasure = null;
		Random random = new Random();
		int treasureKind = random.nextInt(4);
		System.out.print("Found a trasure: ");
		switch (treasureKind){
			case(0): treasure = Weapon.loot(); break;
			case(1): treasure = Spell.loot(); break;
			case(2): treasure = HealthPotion.loot(); break;
			case(3): treasure = ManaPotion.loot(); break;
		}
		treasure.use(hero);
		treasure.use(orig_hero);
		
	}
	
	private void setEnemies(){
		Random random = new Random();
		int height = map.length;
		for (int i=0;i<height; i++){
			int width = map[i].length;
			for (int j=0;j<width;j++){
				if (map[i][j] == 'E'){
					int health = random.nextInt(501);
					int mana = random.nextInt(151);
					int damage = random.nextInt(101);
					Position pos = new Position(i,j);
					Zombie zombie = new Zombie(health,mana,damage);
					
					Treasure spell = Spell.loot();
					spell.use(zombie);
					//System.out.println(zombie);
					enemies.put(pos, zombie);
					System.out.println(enemies.get(pos));
				}
			}
		}
	}
	
	private Position findTarget(){
		int new_x = hero_x;
		int new_y = hero_y;
		int range = hero.getSpell().getCastRange();
		for (int i=1;i<=range;i++){
			if (!isInside(new_x, new_y+i) || !isWalkable(new_x, new_y+i)){
				break;
			}
			if (map[new_x][new_y+i] == 'E')
				return new Position(new_x,new_y+i);
		}
		for (int i=1;i<=range;i++){
			if (!isInside(new_x, new_y-i) || isWalkable(new_x, new_y-i)){
				break;
			}
			if (map[new_x][new_y-i] == 'E')
				return new Position(new_x,new_y-i);
		}
		for (int i=1;i<=range;i++){
			if (!isInside(new_x+i, new_y) || isWalkable(new_x+i, new_y)){
				break;
			}
			if (map[new_x+i][new_y] == 'E')
				return new Position(new_x+i,new_y);
		}
		for (int i=1;i<=range;i++){
			if (!isInside(new_x-i, new_y) ||isWalkable(new_x-i, new_y)){
				break;
			}
			if (map[new_x-i][new_y] == 'E')
				return new Position(new_x-i,new_y);
		}
		return null;
	}
	
	public Dungeon(String[] map){
		int len = map.length;
		//int len1 =map[1].length();
		this.map = new char[len][];
		for (int i=0;i<len;i++){
			this.map[i] = map[i].toCharArray();
		}
		setEnemies();
		
	}
	
	public void printMap(){
		int len = map.length;
		for (int i=0;i<len;i++){
			System.out.println(this.map[i]);
		}
	}
	
	public boolean spawn(Hero hero){
		int i, j;
		int height = map.length;
		for (i=0;i< height;i++){
			int width = map[i].length;
			for (j=0;j<width;j++){
				if (map[i][j] == 'S'){
					map[i][j] = 'H';
					if (this.hero == null){
						this.orig_hero = new Hero(hero);
					} else {
						map[hero_x][hero_y]='.';
						map[lastMetZombie.getX()][lastMetZombie.getY()] = 'E';
					}
					
					this.hero = new Hero(orig_hero);
					this.hero_x = i;
					this.hero_y = j;
					lastFromThePath='.';
					return true;
				}
			}
		}
		map[hero_x][hero_y] = 'E';
		System.out.println("Game over");
		stillRunning = false;
		return false;
	}
	
	public void moveHero(String direction){
		if (!stillRunning){
			return;
		}
		int x= hero_x;
		int y= hero_y;
		int current_x = hero_x;
		int current_y = hero_y;
		switch(direction){
			case("up"): x--; break;
			case("down"): x++; break;
			case("left"): y--; break;
			case("right"): y++; break;
		}
		if (isInside(x,y) && isWalkable(x, y)){
			hero_x = x;
			hero_y = y;
			if (lastFromThePath=='S'){
				map[current_x][current_y] = 'S';
			} else {
				map[current_x][current_y] ='.';
			}
			switch(map[x][y]){
			case('T'): getTreasure(); break;
			case('E'): heroAttack("weapon"); break;
			case('S'): lastFromThePath = 'S'; break;
			case('.'): lastFromThePath = '.'; break;
			}
			hero.takeMana(hero.getManaRegen());
			
//			hero_x = x;
//			hero_y = y;
			if (hero.isAlive()){
				map[hero_x][hero_y] = 'H';
			}
		}
	}
	private void attack_help(Position position, Zombie zombi){
		if (!stillRunning){
			return;
		}
		Fight fight = new Fight(hero,zombi);
		Position oldPos = new Position(position);
		fight.setPositions(new Position(hero_x,hero_y), position);
		Position coords = fight.le_fight();
		if (!enemies.get(oldPos).isAlive()){
			enemies.remove(oldPos);
			map[oldPos.getX()][oldPos.getY()]='.';
			map[hero_x][hero_y] = '.';
			hero_x = coords.getX();
			hero_y = coords.getY();
			map[hero_x][hero_y] ='H';
		}
		else if (!revive()){
			System.out.println("Game over");
		}
	}
	public void heroAttack(String by){
		if (!stillRunning){
			return;
		}
		if (by.equals("magic")){
			Position position = findTarget();
			if (position != null){
				lastMetZombie = new Position(position);
				Zombie zombi = enemies.get(position);
				attack_help(position,zombi);
			} else {
				System.out.println("There are no enemies in range.");
			}
		} else if (by.equals("weapon")){
			Position position = new Position(hero_x,hero_y);
			lastMetZombie = new Position(position);
			Zombie zombi = enemies.get(position);
			attack_help(position,zombi);
		}
	}
}
