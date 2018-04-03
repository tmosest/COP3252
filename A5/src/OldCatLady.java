/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Old Cat Lady Enemy Class
 */
public class OldCatLady extends Enemy
{

  /**
   * Default OldCatLady Constructor.
   */
  public OldCatLady()
  {
    super();
    this.setHealth(100);
    this.setArmor(Items.getRandomArmor());
    this.setWeapon(Items.getRandomWeapon());

    Reward reward = new Reward();
    reward.setGold(Utility.randomWithRange(0, 100));
    reward.setHealth(Utility.randomWithRange(50, 100));
    this.setReward(reward);
  }

  /**
   * Character base damage.
   * @return The amount of base damage.
   */
  @Override
  public int fight() {
    return 5;
  }

  /**
   * Returns the characters name.
   * @return "Old Cat Lady".
   */
  @Override
  public String getName() {
    return "Old Cat Lady";
  }
}
