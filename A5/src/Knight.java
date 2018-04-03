/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/
public class Knight
{

  /**
   * Character names from Monty Python
   */
  private static final String[] AWESOME_NAMES = {
      "Gumbys",
      "Rabbit of Caerbannog",
      "Mr Praline",
      "King Arthur",
      "The Colonel",
      "The Black Knight",
      "Ron Obvious",
      "Knight Who Says Ni",
      "Sir Robin, The Not-Quite-So-Brave",
      "Lancelot",
      "King of Swamp Castle",
      "Nude Organist",
      "Dead Collector"
  };

  /**
   * Get a random default name for the Knight
   * @return
   */
  public static String getRandomName()
  {
    int index = Utility.randomWithRange(0, AWESOME_NAMES.length - 1);
    return AWESOME_NAMES[index];
  }

  private String name;
  private int health;
  private int battles;
  private int age;
  private int gold;
  private Items.WEAPON weapon;
  private Items.ARMOR armor;

  public Knight()
  {
    this.name = Knight.getRandomName();
    this.battles = Utility.randomWithRange(0, 10);
    this.health = Utility.randomWithRange(100, 100 + 10 * this.battles);
    this.age = Utility.randomWithRange(30, 30 + this.battles);
    this.gold = Utility.randomWithRange(1, 10 + 5 * this.battles);
    this.weapon = Items.getRandomWeapon();
    this.armor = Items.getRandomArmor();
  }

  public Knight(int level)
  {
    this.name = Knight.getRandomName();
    this.battles = Utility.randomWithRange(0, level);
    this.health = Utility.randomWithRange(100, 100 + 10 * this.battles);
    this.age = Utility.randomWithRange(30, 30 + this.battles);
    this.gold = Utility.randomWithRange(1, 10 + 5 * this.battles);
    this.weapon = Items.getRandomWeapon();
    this.armor = Items.getRandomArmor();
  }

  public Knight(String name, Items.WEAPON weapon, int health, int battles, int age, int gold)
  {
    this.name = name;
    this.weapon = weapon;
    this.health = health;
    this.battles = battles;
    this.age = age;
    this.gold = gold;
    this.armor = Items.getRandomArmor();
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

  public Items.WEAPON getWeapon() { return weapon; }

  public Items.ARMOR getArmor() { return armor; }

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

  public void setWeapon(Items.WEAPON weapon) { this.weapon = weapon; }

  public void setArmor(Items.ARMOR armor) { this.armor = armor; }

  public int fight(Enemy enemy)
  {
    int damage = Items.determineDamage(this.weapon, enemy.getArmor());
    try {
      enemy.takeDamage(damage);
    }
    catch (NegativeDamageException e)
    {
      System.out.println("Damage cannot be negative!");
    }
    return damage;
  }

  public void giveRewards(Reward reward)
  {
    this.health += reward.getHealth();
    this.gold += reward.getGold();
  }

  public void levelUp()
  {
    this.setAge(this.getAge() + 1);
    this.setBattles(this.getBattles() + 1);
    this.setGold(this.getGold() + 100);
    this.setHealth(this.getHealth() + 75);
  }

  /**
   * Function to calculate damage and apply it.
   * @param enemy Enemy that is attacking.
   * @return The amount of damage done.
   */
  public int takeDamage(Enemy enemy)
  {
    int damage = Items.determineDamage(
        enemy.getWeapon(),
        this.getArmor(),
        enemy.fight()
    );
    this.setHealth(this.getHealth() - damage);
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
