
public class Items
{

  /**
   * Enum of Weapons
   */
  public static enum WEAPON {
    LongSword,
    BattleAxe,
    Spear,
    WarHammer
  }

  /**
   * Enum of Armors
   */
  public static enum ARMOR {
    Leather,
    Wood,
    Metal,
    Pillows
  }

  /**
   * Function used to determine the amount of damage to do.
   * @param weapon Attacker's weapon.
   * @param armor Defender's Armor.
   * @return Integer representing the amount of damage that is to be done.
   */
  public static int determineDamage(WEAPON weapon, ARMOR armor)
  {
    return determineDamage(weapon, armor, 10);
  }

  /**
   * Type damage with custom base amount.
   * @param weapon Attacker's weapon.
   * @param armor Defender's Armor.
   * @param damage Base damage amount.
   * @return Integer representing the amount of damage that is to be done.
   */
  public static int determineDamage(WEAPON weapon, ARMOR armor, int damage)
  {
    // If they have no armor or you have no weapon then it is base damage.
    if (weapon == null || armor == null)
    {
      return damage;
    }
    // Other wise determine calc
    if (weapon.ordinal() == armor.ordinal())
    {
      return 5 * damage;
    }
    if (weapon.ordinal() == armor.ordinal() - 1)
    {
      return damage / 2;
    }
    if (weapon.ordinal() == armor.ordinal() - 2)
    {
      return damage / 5;
    }
    if (weapon.ordinal() == armor.ordinal() - 3)
    {
      return damage * 2;
    }
    return damage;
  }

  /**
   * Returns a random weapon.
   * @return
   */
  public static WEAPON getRandomWeapon()
  {
    int weaponIndex = Utility.randomWithRange(
        Items.WEAPON.LongSword.ordinal(),
        Items.WEAPON.WarHammer.ordinal()
    );
    return Items.WEAPON.values()[weaponIndex];
  }

  /**
   * Returns a random armor.
   * @return
   */
  public static ARMOR getRandomArmor()
  {
    int armorIndex = Utility.randomWithRange(
        ARMOR.Leather.ordinal(),
        ARMOR.Pillows.ordinal()
    );
    return ARMOR.values()[armorIndex];
  }
}
