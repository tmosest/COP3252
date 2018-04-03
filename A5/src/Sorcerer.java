/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Sorcerer Enemy Class.
 */
public class Sorcerer extends Enemy
{

  /**
   * Default Constructor Class.
   */
  public Sorcerer()
  {
    super();
    this.setHealth(50);
    this.setArmor(Items.ARMOR.Pillows);
    this.setWeapon(Items.WEAPON.LongSword);

    Reward reward = new Reward();
    reward.setGold(Utility.randomWithRange(100, 200));
    reward.setHealth(Utility.randomWithRange(0, 200));
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
   * Enemy name
   * @return "Sorcerer".
   */
  @Override
  public String getName() {
    return "Sorcerer";
  }
}
