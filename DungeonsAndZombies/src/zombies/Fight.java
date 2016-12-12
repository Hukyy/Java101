package zombies;

public class Fight {
	private Hero hero;
	private Zombie zombie;
	private Position heroPosition;
	private Position zombiePosition;
	
	public Fight(Hero h, Zombie z){
		hero=h;
		zombie=z;
	}
	
	public void setPositions(Position hero, Position zombie){
		heroPosition= hero;
		zombiePosition = zombie;
	}
	
	private void moveHero(){
		int heroX = heroPosition.getX() - zombiePosition.getX();
		int heroY = heroPosition.getY() - zombiePosition.getY();
		if (heroX>0){
			heroX--;
			heroPosition.setX(heroPosition.getX()-1);
			System.out.println("Hero moves one square up in order to get to the zombie. ");
		}
		if (heroX<0){
			heroX++;
			heroPosition.setX(heroPosition.getX()+1);
			System.out.println("Hero moves one square down in order to get to the zombie. ");
		}
		if (heroY>0){
			heroY--;
			heroPosition.setY(heroPosition.getY()-1);
			System.out.println("Hero moves one square to the left in order to get to the zombie. ");
		}
		if (heroY<0){
			heroY++;
			heroPosition.setY(heroPosition.getY()+1);
			System.out.println("Hero moves one square to the right in order to get to the zombie. ");
		}
	}
	
	private void moveZombie(){
		int zombieX = heroPosition.getX() - zombiePosition.getX();
		int zombieY = heroPosition.getY() - zombiePosition.getY();
		if (zombieX<0){
			zombieX++;
			zombiePosition.setX(zombiePosition.getX()-1);
			System.out.println("Enemy moves one square up in order to get to the hero. ");
		}
		if (zombieX>0){
			zombieX--;
			zombiePosition.setX(zombiePosition.getX()+1);
			System.out.println("Enemy moves one square down in order to get to the hero. ");
		}
		if (zombieY>0){
			zombieY--;
			zombiePosition.setY(zombiePosition.getY()+1);
			System.out.println("Enemy moves one square to the right in order to get to the hero. ");
		}
		if (zombieY<0){
			zombieY++;
			zombiePosition.setY(zombiePosition.getY()-1);
			System.out.println("Enemy moves one square to the left in order to get to the hero. ");
		}
		//return new Position(zombieX,zombieY);
	}
	
	private boolean samePosition(){
		return heroPosition.getX() == zombiePosition.getX() && heroPosition.getY() == zombiePosition.getY();
	}
	
	private void zombie_attack(){
		
		int damage_autoattack = zombie.getDamage();
		int damage_weapon = 0;
		if (zombie.getWeapon()!=null){
			damage_weapon = zombie.getWeapon().getDamage();
		}
		int damage_spell = 0;
		if (zombie.getSpell()!=null && zombie.canCast()){
			damage_spell=zombie.getSpell().getDamage();
		}
		int max = Math.max(damage_weapon, Math.max(damage_autoattack, damage_spell));
		if (max==damage_autoattack){
			System.out.println("Zombie attacks with auto attack. Hero took " + max + "damage.");
			
		} else if (max==damage_weapon){
			System.out.println("Zombie hits our hero with " + zombie.getWeapon().getName() + "for "+ max + "damage" );
			
		} else {
			zombie.attack("magic");
			System.out.println("Zombie casts " + zombie.getSpell().getName() +"Hero took" + max + "damage.");
		}
		hero.takeDamage(max);
	}
	
	private void range_fight(){
		boolean lastMoveByHero=false;
		boolean notEnoughMana = false;
		boolean zombieNotEnoughMana = false;
		while (!samePosition() && zombie.isAlive() && hero.isAlive()){
			System.out.println("Our hero (" + hero.getHealth() + " health, " + hero.getMana() + " mana).    Zombie ("  + zombie.getHealth() + " health, "  + zombie.getMana() + " mana).");
			System.out.println("It's our turn now");
			int hero_damage = hero.attack("magic");
			if (hero_damage == 0){
				if (!notEnoughMana){
					System.out.print("Hero has not enough mana, ");
					notEnoughMana = true;
				}
				moveHero();
			}
			else{
				zombie.takeDamage(hero_damage);
				System.out.println("Hero casts " + hero.getSpell().getName() + " .Zombie took " + hero_damage + " damage.");
			}
			lastMoveByHero = true;
			if (!zombie.isAlive()){
				System.out.println("The enemy is dead");
				return;
			}
			if (samePosition()){
				break;
			}
			int zombie_damage = 0;
			if(zombie.getSpell()!= null && zombie.canCast()){
				zombie_damage = zombie.attack("magic");
			}
			if (zombie_damage != 0){
				hero.takeDamage(zombie_damage);
				System.out.println("Zombie casts " + zombie.getSpell().getName() + " .Hero took " + zombie_damage + " damage.");
			} else {
				if (!zombieNotEnoughMana){
					System.out.print("Zombie has not enough mana, ");
					zombieNotEnoughMana=true;
				}
				moveZombie();
			}
			lastMoveByHero=false;
		}
		if (lastMoveByHero){
			zombie_attack();
		}
	}
	
	private void melee_fight(){
		while (zombie.isAlive() && hero.isAlive()){
			System.out.println("Our hero (" + hero.getHealth() + " health, " + hero.getMana() + " mana) is attacking the enemy ("  + zombie.getHealth() + " health, "  + zombie.getMana() + " mana).");
			int spell_damage = 0;
			if (hero.canCast()){
				spell_damage= hero.getSpell().getDamage();
			}
			int melee_damage = hero.getWeapon().getDamage();
			if (spell_damage > melee_damage){
				zombie.takeDamage(hero.attack("magic"));
				System.out.println("Hero casts " + hero.getSpell().getName() + " .Zombie took " + spell_damage + " damage.");
			}
			else {
				zombie.takeDamage(hero.attack("weapon"));
				
				System.out.println("Hero attaks with " + hero.getWeapon().getName() + " .Zombie took " + melee_damage + " damage.");
			}
			if (!zombie.isAlive()){
				break;
			}
			zombie_attack();
		}
	}
	
	public Position le_fight(){
		System.out.println("A fight is started between our Hero("+ hero.getHealth() + " health, " + hero.getMana() + " mana)"
				+ " and Enemy("+ zombie.getHealth() + " health, "  + zombie.getMana() + " mana).");
		range_fight();
		melee_fight();
		if (!zombie.isAlive()){
			System.out.println("Zombie is dead!");
			return new Position(heroPosition);
		} else{
			System.out.println("Hero is dead!");
			return null;
		}
	}
}
