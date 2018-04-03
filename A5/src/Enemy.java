/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/
import java.util.Random;

public abstract class Enemy {

  /**
   * Static method used to generate enemies of various types.
   * @return
   */
  public static Enemy getRandomEnemy()
  {
    // Get a random number from [0, 5)
    Random rand = new Random();
    int random = rand.nextInt(5);
    // Look at the values
    switch (random)
    {
      case 1:
        return new Sorcerer();
      case 2:
        return new Troll();
      case 3:
        return new WalmartShopper();
      case 4:
        return new OldCatLady();
      default:
        return new Ogre();
    }
  }

  /**
   * Enemy's health.
   */
  private int health;
  /**
   * Enemy's armor.
   */
  private Items.ARMOR armor;
  /**
   * Enemy's weapon.
   */
  private Items.WEAPON weapon;
  /**
   * Reward for killing the enemy.
   */
  private Reward reward = new Reward();

  /**
   * Function used for dealing damage to the enemy
   * @param damage Positive value representing how much to take away.
   * @throws NegativeDamageException Throws an exception if damage is negative
   */
  public void takeDamage(int damage) throws NegativeDamageException
  {
    if (damage < 0)
      throw new NegativeDamageException("Tried to set damage to " + damage);
    health -= damage;
  }

  /**
   * Getter for enemy health.
   * @return The amount of health.
   */
  public int getHealth()
  {
    return health;
  }

  /**
   * Setter for the enemy health.
   * @param health Amount of health.
   */
  public void setHealth(int health)
  {
    this.health = health;
  }

  /**
   * Getter for enemy armor.
   * Used in danage calcumlations.
   * @return Armor from Armor Enum.
   */
  public Items.ARMOR getArmor() {
    return armor;
  }

  /**
   * Function to get the enemies weapon.
   * @return Weapon from enum.
   */
  public Items.WEAPON getWeapon() {
    return weapon;
  }

  /**
   * Function to set the weapon.
   * @param weapon Weapon to set to.
   */
  public void setWeapon(Items.WEAPON weapon) {
    this.weapon = weapon;
  }

  /**
   * Setter for the armor.
   * @param armor Instance from ARMOR enum.
   */
  public void setArmor(Items.ARMOR armor) { this.armor = armor; }

  /**
   * Get the rewards
   * @return Rewards
   */
  public Reward getReward() { return this.reward; }

  /**
   * Set the rewards
   * @param reward Rewards
   */
  public void setReward(Reward reward) {
    this.reward = reward;
  }

  /**
   * String representation of an Enemy
   * @return String of enemy information
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Enemy's Name: ").append(this.getName());
    sb.append("\nEnemy's Health: ").append(this.getHealth());
    sb.append("\nEnemy's Weapon: ").append(this.getWeapon());
    sb.append("\nEnemy's Armor: ").append(this.getArmor());
    return sb.toString();
  }

  /**
   * Abstract fight method to be overriden
   * @return Amount of damage to do.
   */
  public abstract int fight();

  /**
   * Abstract name method.
   * @return The name of the enemy. Ex: Ogre
   */
  public abstract String getName();
}
