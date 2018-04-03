/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Troll Enemy Class
 */
public class Troll extends Enemy
{

  /**
   * Default Troll Constructor.
   */
  public Troll()
  {
    super();
    this.setHealth(100);
    this.setArmor(Items.ARMOR.Wood);
    this.setWeapon(Items.WEAPON.WarHammer);

    Reward reward = new Reward();
    reward.setGold(Utility.randomWithRange(0, 50));
    reward.setHealth(Utility.randomWithRange(0, 35));
    this.setReward(reward);
  }

  /**
   * Returns base damage.
   * @return base damage.
   */
  @Override
  public int fight() {
    return 5;
  }

  /**
   * Returns the name.
   * @return "Troll".
   */
  @Override
  public String getName() {
    return "Troll";
  }
}
