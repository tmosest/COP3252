/*
Moses, Tyler
COP-3252
Assignment 3
01/13/2018
*/
public class Knight
{
  private String name;
  private int health;
  private int battles;
  private int age;
  private int gold;

  public Knight()
  {}

  public Knight(String name, int health, int battles, int age, int gold)
  {
    this.name = name;
    this.health = health;
    this.battles = battles;
    this.age = age;
    this.gold = gold;
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

  public String toString() {
    String result = "Knight Name: " + name +
        "\nKnight Health: " + health +
        "\nKnight Battles: " + battles +
        "\nKnight Age: " + age +
        "\nKnight Gold: $" + gold;
    return result;
  }
}
