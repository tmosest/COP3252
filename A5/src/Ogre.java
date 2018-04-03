/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/

/**
 * Ogre Enemy Class
 */
public class Ogre extends Enemy
{

  /**
   * Default Ogre Constructor
   */
  public Ogre()
  {
    super();
    this.setHealth(200);
    this.setArmor(Items.ARMOR.Leather);
    this.setWeapon(Items.WEAPON.BattleAxe);

    Reward reward = new Reward();
    reward.setGold(Utility.randomWithRange(0, 10));
    reward.setHealth(Utility.randomWithRange(10, 40));
    this.setReward(reward);
  }

  /**
   * Returns the base damage for this enemy.
   * @return Int of base damage.
   */
  @Override
  public int fight()
  {
    return 5;
  }

  /**
   * Returns the enemy name.
   * @return "Ogre".
   */
  @Override
  public String getName() {
    return "Ogre";
  }
}
