import java.util.Scanner;

/**
 * GUI class for handling Item information
 */
public class ItemsDriver
{

  /**
   * Function to allow a user to select a weapon.
   * @return Selected weapon.
   */
  public static Items.WEAPON selectAWeapon(Scanner in)
  {
    System.out.println("Select a weapon \n1) Long Sword\n2) Battle Axe\n3) Spear\n4) War Hammer");
    Items.WEAPON weapon = Items.WEAPON.LongSword;

    int weaponSelection = in.nextInt();
    in.nextLine();

    switch (weaponSelection) {
      case -1:
        return null;
      case 2:
        weapon = Items.WEAPON.BattleAxe;
        break;
      case 3:
        weapon = Items.WEAPON.Spear;
        break;
      case 4:
        weapon = Items.WEAPON.WarHammer;
        break;
    }
    return weapon;
  }

  /**
   * Function to allow a user to select an armor.
   * @return Selected armor.
   */
  public static Items.ARMOR selectAnArmor(Scanner in)
  {
    System.out.println("Select an armor: \n1) Leather\n2) Wood\n3) Metal\n4) Pillows");
    int armorSelection = in.nextInt();
    in.nextLine();
    Items.ARMOR armor = Items.ARMOR.Leather;
    switch (armorSelection) {
      case -1:
        return null;
      case 2:
        armor = Items.ARMOR.Wood;
        break;
      case 3:
        armor = Items.ARMOR.Metal;
        break;
      case 4:
        armor = Items.ARMOR.Pillows;
        break;
    }
    return armor;
  }
}
