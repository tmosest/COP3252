/*
Moses, Tyler
COP-3252
Assignment 2
01/13/2018
*/
import java.util.Scanner;

public class Assignment2
{

  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    System.out.printf("%s\n%s", "Lets create a new knight", "Please enter his name: ");
    String name = in.next();
    System.out.printf("Ok his name is %s.\nHow much health does he have? ", name);
    int health = in.nextInt();
    System.out.printf("He has %d units of health.\nHow many battles has he fought in? ", health);
    int battles = in.nextInt();
    System.out.printf("So he has fought in %d battles.\nHow old is he? ", battles);
    int age = in.nextInt();
    System.out.printf("Wow he is %d years old!\nHow much gold has he aquired? ", age);
    int gold = in.nextInt();
    double avgGoldPerBattle = ((double) gold) / battles;
    System.out.printf("Total gold = %d, and Average gold per battle = %f! Not bad, not bad!\n", gold, avgGoldPerBattle);
  }
}