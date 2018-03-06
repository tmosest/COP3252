/*
Moses, Tyler
COP-3252
Assignment 3
01/13/2018
*/
import javax.swing.JOptionPane;

public class KnightDriver
{

  public static void main(String[] args)
  {
    boolean noMore = false;
    while (!noMore) {
      // Print welcome message
      JOptionPane.showMessageDialog(null, "Welcome to Knight Fight!");
      // Ask for name
      String name = JOptionPane.showInputDialog("What is the knight's name?");
      // Now select your weapon
      int health = 100;
      int battles = 0;
      int age = 30;
      int gold = 10;
      boolean gameover = false;
      if (name == null) {
        noMore = true;
        return;
      }
      Knight.WEAPON newWeapon = Knight.WEAPON.LongSword;
      Knight knight = new Knight(name, newWeapon, health, battles, age, gold);
      while (!gameover) {
        String weapon = JOptionPane.showInputDialog(
            "Select a weapon: \n1) Long Sword\n2) Battle Axe\n3) Spear\n4) War Hammer\n");
        if (weapon == null) {
          noMore = true;
          return;
        }
        switch (weapon) {
          case "2":
            newWeapon = Knight.WEAPON.BattleAxe;
            break;
          case "3":
            newWeapon = Knight.WEAPON.Spear;
            break;
          case "4":
            newWeapon = Knight.WEAPON.WarHammer;
            break;
        }
        knight.setWeapon(newWeapon);
        Knight opponent;
        String choice = JOptionPane
            .showInputDialog("Would you like to autogenerate an opponent? (y/n)");
        if (choice == null) {
          noMore = true;
          return;
        }
        opponent = new Knight(knight.getBattles());
        if (choice.equals("y"))
        {
          name = JOptionPane.showInputDialog("What is the knight's name?");
          opponent.setName(name);
          weapon = JOptionPane.showInputDialog(
              "Select a weapon: \n1) Long Sword\n2) Battle Axe\n3) Spear\n4) War Hammer\n");
          switch (weapon) {
            case "2":
              newWeapon = Knight.WEAPON.BattleAxe;
              break;
            case "3":
              newWeapon = Knight.WEAPON.Spear;
              break;
            case "4":
              newWeapon = Knight.WEAPON.WarHammer;
              break;
          }
          opponent.setWeapon(newWeapon);
        }
        JOptionPane.showMessageDialog(null, knight.toString() + "\n vs. \n" + opponent.toString());
        while (knight.getHealth() > 0 && opponent.getHealth() > 0) {
          int damage1 = knight.fight(opponent);
          int damage2 = opponent.fight(knight);
          JOptionPane.showMessageDialog(null,
              "You did " + damage1 +
                  " to " + opponent.getName() +
                  "\n" + opponent.getName() +
                  " did " + damage2 +
                  " to you!\n" +
                  knight.getName() + " " +
                  knight.getHealth() + " vs " +
                  opponent.getName() + " " +
                  opponent.getHealth()
          );
        }
        if (knight.getHealth() <= 0) {
          JOptionPane.showMessageDialog(null, "You lost the match!");
          gameover = true;
        } else {
          JOptionPane.showMessageDialog(null, "You won the match!");
        }
      }
    }
  }
}
