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
    String name = JOptionPane.showInputDialog("What is the knight's name?");
    int health = Integer.parseInt(JOptionPane.showInputDialog("How much health does he have?"));
    int battles = Integer.parseInt(JOptionPane.showInputDialog("How many battles has he fought in?"));
    int age = Integer.parseInt(JOptionPane.showInputDialog("How old is the knight?"));
    int gold = Integer.parseInt(JOptionPane.showInputDialog("How much gold does the knight have?"));
    Knight knight = new Knight(name, health, battles, age, gold);
    int rows = Integer.parseInt(JOptionPane.showInputDialog("How many rows?"));
    int columns = Integer.parseInt(JOptionPane.showInputDialog("How many columns?"));
    Stars stars = new Stars(rows, columns);
    JOptionPane.showMessageDialog(null, knight.toString() + "\n" + stars.toString());
  }
}
