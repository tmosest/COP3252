/*
Moses, Tyler
COP-3252
Assignment 3
01/13/2018
*/
public class Knight
{
  public static enum WEAPON {
    LongSword,
    BattleAxe,
    Spear,
    WarHammer
  }

  public static enum ARMOR {
    Leather,
    Wood,
    Metal,
    Pillows
  }

  private static int determineDamage(WEAPON weapon, ARMOR armor)
  {
    int damage = 10;
    if (weapon.ordinal() == armor.ordinal())
    {
      return 2 * damage;
    }
    if (weapon.ordinal() == armor.ordinal() - 1)
    {
      return damage / 2;
    }
    if (weapon.ordinal() == armor.ordinal() - 2)
    {
      return damage / 5;
    }
    return damage;
  }

  private static int randomWithRange(int min, int max)
  {
    int range = (max - min) + 1;
    return (int)(Math.random() * range) + min;
  }

  private String name;
  private int health;
  private int battles;
  private int age;
  private int gold;
  private WEAPON weapon;
  private ARMOR armor;

  public Knight()
  {
    this.name = "Random";
    this.battles = randomWithRange(0, 10);
    this.health = randomWithRange(100, 100 + 10 * this.battles);
    this.age = randomWithRange(30, 30 + this.battles);
    this.gold = randomWithRange(1, 10 + 5 * this.battles);
    this.weapon = WEAPON.values()[randomWithRange(WEAPON.LongSword.ordinal(), WEAPON.WarHammer.ordinal())];
    this.armor = ARMOR.values()[randomWithRange(ARMOR.Leather.ordinal(), ARMOR.Pillows.ordinal())];
  }

  public Knight(int level)
  {
    this.name = "Random lvl" + level;
    this.battles = randomWithRange(0, level);
    this.health = randomWithRange(100, 100 + 10 * this.battles);
    this.age = randomWithRange(30, 30 + this.battles);
    this.gold = randomWithRange(1, 10 + 5 * this.battles);
    this.weapon = WEAPON.values()[randomWithRange(WEAPON.LongSword.ordinal(), WEAPON.WarHammer.ordinal())];
    this.armor = ARMOR.values()[randomWithRange(ARMOR.Leather.ordinal(), ARMOR.Pillows.ordinal())];
  }

  public Knight(String name, WEAPON weapon, int health, int battles, int age, int gold)
  {
    this.name = name;
    this.weapon = weapon;
    this.health = health;
    this.battles = battles;
    this.age = age;
    this.gold = gold;
    this.armor = ARMOR.values()[randomWithRange(ARMOR.Leather.ordinal(), ARMOR.Pillows.ordinal())];
  }

  public int getAge()
  {
    return age;
  }

  public int getBattles()
  {
    return battles;
  }

  public int getGold()
  {
    return gold;
  }

  public int getHealth()
  {
    return health;
  }

  public String getName()
  {
    return name;
  }

  public WEAPON getWeapon() { return weapon; }

  public ARMOR getArmor() { return armor; }

  public void setAge(int age)
  {
    this.age = age;
  }

  public void setBattles(int battles)
  {
    this.battles = battles;
  }

  public void setGold(int gold)
  {
    this.gold = gold;
  }

  public void setHealth(int health)
  {
    this.health = health;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setWeapon(WEAPON weapon) { this.weapon = weapon; }

  public void setArmor(ARMOR armor) { this.armor = armor; }

  public int fight(Knight knight)
  {
    int damage = determineDamage(this.weapon, knight.armor);
    int health = knight.getHealth();
    health -= damage;
    knight.setHealth(health);
    if (knight.getHealth() <= 0)
    {
      this.setAge(this.getAge() + 1);
      this.setBattles(this.getBattles() + 1);
      this.setGold(this.getGold() + knight.getGold());
      this.setHealth(this.getHealth() + 75);
    }
    return damage;
  }

  public String toString()
  {
    String result = "Knight Name: " + name +
        "\nKnight's Weapon: " + weapon +
        "\nKnight's Armor: " + armor +
        "\nKnight Health: " + health +
        "\nKnight Battles: " + battles +
        "\nKnight Age: " + age +
        "\nKnight Gold: $" + gold;
    return result;
  }
}
