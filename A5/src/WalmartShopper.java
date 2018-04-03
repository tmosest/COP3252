/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Walmart Shopper Enemy Class
 */
public class WalmartShopper extends Enemy
{

  /**
   * Default Constructor for Walmart Shopper.
   */
  public WalmartShopper()
  {
    super();
    this.setHealth(100);
    this.setArmor(Items.ARMOR.Metal);
    this.setWeapon(Items.WEAPON.Spear);

    Reward reward = new Reward();
    reward.setGold(Utility.randomWithRange(100, 10000));
    reward.setHealth(Utility.randomWithRange(50, 100));
    this.setReward(reward);
  }

  /**
   * Base damage.
   * @return Base damage.
   */
  @Override
  public int fight() {
    return 5;
  }

  /**
   * Return enemy name.
   * @return "Walmart Shopper".
   */
  @Override
  public String getName() {
    return "Walmart Shopper";
  }
}
