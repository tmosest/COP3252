/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Reward POJO object.
 */
public class Reward {
  private int gold;
  private int health;

  /**
   * Default Constructor
   */
  public Reward()
  {
    this.gold = 10;
    this.health = 10;
  }

  /**
   * More specific constructor
   * @param gold
   * @param health
   */
  public Reward(int gold, int health)
  {
    this.gold = gold;
    this.health = health;
  }

  /**
   * Getter for gold.
   * @return Gold!
   */
  public int getGold() {
    return gold;
  }

  /**
   * Setter for gold
   * @param gold Gold!
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Getter for health.
   * @return Health!
   */
  public int getHealth() {
    return health;
  }

  /**
   * Setter for the health.
   * @param health Health!
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * String method.
   * @return String of information.
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Reward's Gold: ").append(this.gold);
    sb.append("\nReward's Health: ").append(this.health);
    return sb.toString();
  }
}
