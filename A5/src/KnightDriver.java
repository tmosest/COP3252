/*
Moses, Tyler
COP-3252
Assignment 5
04/02/2018
*/
import java.util.Scanner;

public class KnightDriver
{

  /**
   * Prints a header divider
   * ===========================
   */
  private static void printDivider()
  {
    System.out.println("==========================================");
  }

  /**
   * Prints a proper header
   *
   * ===========================
   * message
   * ===========================
   *
   * @param message Header title to print.
   */
  private static void printHeader(String message)
  {
    printDivider();
    System.out.println(message);
    printDivider();
  }

  private static void pressEnter(Scanner in)
  {
    System.out.println("Ready to continue? (Input anything)\n");
    in.nextLine();
  }

  private static void printError(Scanner in, String error)
  {
    System.out.println();
    printHeader("Error: " + error);
    System.out.println();
    pressEnter(in);
  }

  /**
   * Prints the introduction message
   */
  private static void printWelcomeMessage(Scanner in)
  {
    printHeader("Welcome to JavaBean Forest!");
    System.out.println();
    Utility.printFromFile("trees.txt");
    System.out.println();
    pressEnter(in);
  }

  /**
   * Function to help us create a custom kngith.
   * @param in
   * @return
   */
  private static Knight createKnight(Scanner in)
  {
    printHeader("Create a Knight");
    System.out.println();
    Utility.printFromFile("knight.txt");
    System.out.println();
    String defaultName = Knight.getRandomName();
    System.out.println("What is your knight's name? (" + defaultName + ")");
    String name = in.nextLine();
    // Check for a blank name
    if ("".equals(name.trim()))
    {
      name = defaultName;
    }
    // Now select your weapon
    int health = 100;
    int battles = 0;
    int age = 30;
    int gold = 10;

    Items.WEAPON weapon = ItemsDriver.selectAWeapon(in);
    Knight knight = new Knight(name, weapon, health, battles, age, gold);

    Items.ARMOR armor = ItemsDriver.selectAnArmor(in);
    knight.setArmor(armor);

    return knight;
  }

  /**
   * Prints a message to let the user know we are done with Knight creation.
   */
  private static void printAdventureBegins(Scanner in)
  {
    printHeader("Let the Adventures Begin!");
    System.out.println();
    Utility.printFromFile("adventure.txt");
    System.out.println("\n You set off into the woods\n  where you are suddenly attacked!\n");
    pressEnter(in);
  }

  /**
   * Helper function for creating enemies.
   * @param in Scanner
   * @return Enemies array.
   */
  private static Enemy[] createEnemies(Scanner in)
  {
    int min = 1;
    int max = 1000;
    printHeader("How many enemies do you see? [" + min + ", " + max + "]");
    Utility.printFromFile("aliens.txt");

    int enemiesCount = in.nextInt();
    in.nextLine();
    if (enemiesCount < min || enemiesCount > max)
    {
      printError(in,"Enemies must be from " + min + " to " + max + "!");
      return createEnemies(in);
    }

    Enemy[] enemies = new Enemy[enemiesCount];

    for (int i = 0 ; i < enemiesCount; i++)
    {
      enemies[i] = Enemy.getRandomEnemy();
      System.out.println("Enemy " + i + " is a " + enemies[i].getName());
    }

    pressEnter(in);

    return enemies;
  }

  /**
   * Prints stats about the knight.
   * @param knight Knight we need to know about.
   */
  private static void printKnight(Knight knight)
  {
    System.out.println();
    printDivider();
    System.out.println("Knight Stats");
    printDivider();
    System.out.println(knight.toString());
  }

  /**
   * Function for handling the users moves.
   * @param in Scanner
   * @param knight Knight
   * @param enemies Enemies
   */
  private static void handleMoves(Scanner in, Knight knight, Enemy[] enemies)
  {
    System.out.println();
    printDivider();
    System.out.println("Select a move: \n1) Attack\n2) Change Weapon\n3) Change Armor");
    printDivider();
    int move = in.nextInt();
    in.nextLine();
    // Do something with move
    switch (move) {
      case 2:
        // Select a new weapon
        System.out.println("(-1: back to moves)");
        Items.WEAPON weapon = ItemsDriver.selectAWeapon(in);
        if (weapon == null)
        {
          handleMoves(in, knight, enemies);
          return;
        }
        knight.setWeapon(weapon);
        break;
      case 3:
        // Select a new armor
        System.out.println("(-1: back to moves)");
        Items.ARMOR armor = ItemsDriver.selectAnArmor(in);
        if (armor == null)
        {
          handleMoves(in, knight, enemies);
          return;
        }
        knight.setArmor(armor);
        break;
      default:
        // Attack and enemy
        attack(in, knight, enemies);
    }
  }

  /**
   * Attack Sequence
   * @param in Scanner
   * @param knight Knight
   * @param enemies Enemies
   */
  private static void attack(Scanner in, Knight knight, Enemy[] enemies)
  {
    System.out.println();
    printHeader("Select An Enemy (-1: back to moves)");
    Utility.printFromFile("sword.txt");
    for (int i = 0 ; i < enemies.length; i++)
    {
      if (enemies[i].getHealth() > 0)
      {
        System.out.println(i + ")");
        System.out.println(enemies[i].toString());
        System.out.println();
      }
    }

    int enemy = in.nextInt();
    in.nextLine();
    if (enemy == -1)
    {
      handleMoves(in, knight, enemies);
      return;
    }

    if (enemy < 0 || enemy >= enemies.length || enemies[enemy].getHealth() <= 0)
    {
      printError(in,"Must pick a valid enemy!");
      attack(in, knight, enemies);
      return;
    }

    System.out.println();
    printHeader(knight.getName() + " Turn!");
    Utility.printFromFile("smallaxe.txt");
    System.out.println();
    int damage = knight.fight(enemies[enemy]);
    System.out.println(knight.getName() + " did " + damage + " to " + enemies[enemy].getName());
    if (enemies[enemy].getHealth() <= 0)
    {
      knight.giveRewards(enemies[enemy].getReward());
      printHeader("Enemy Defeated:\n" + enemies[enemy].getReward().toString());
      System.out.println();
    }
    pressEnter(in);
  }

  /**
   * Function to let enemies attack the knight.
   * @param knight Knight.
   * @param enemies Enemies.
   * @return If all of the enemies are dead.
   */
  public static boolean enemiesAttack(Scanner in, Knight knight, Enemy[] enemies)
  {
    System.out.println();
    printHeader("Enemies Turn!");
    System.out.println();
    Utility.printFromFile("goblin.txt");
    System.out.println();
    boolean allEnemiesAreDead = true;
    for (Enemy enemy : enemies)
    {
      // Attack!
      if (enemy.getHealth() > 0)
      {
        allEnemiesAreDead = false;
        int damage = knight.takeDamage(enemy);
        System.out.println("You took " + damage + " from " + enemy.getName());
      }
    }

    if (allEnemiesAreDead)
    {
      System.out.println("No damage was taken!");
    }

    pressEnter(in);
    return allEnemiesAreDead;
  }

  /**
   * Deal with Knight's death
   * @param knight Knight.
   * @return If dead or not.
   */
  public static boolean handleKnightDeath(Scanner in, Knight knight)
  {
    boolean dead = knight.getHealth() <= 0;
    if (dead)
    {
      printHeader("You Died, GAMEOVER!");
      Utility.printFromFile("gameover.txt");
      System.out.println();
      pressEnter(in);
    }
    return dead;
  }

  public static boolean playAgain(Scanner in)
  {
    printHeader("Would you like to play again? (y/n)");
    String input = in.next();
    System.out.println("\n");
    // Check for no's
    if ("n".equalsIgnoreCase(input) || "no".equalsIgnoreCase(input))
      return false;
    // Default is true
    return true;
  }

  /**
   * Game function!
   */
  public static void javaBeanForest()
  {
    Scanner in = new Scanner(System.in);
    boolean playAgain = true;
    while (playAgain)
    {
      boolean gameOver = false;
      // Print welcome message
      printWelcomeMessage(in);
      // Create Knight
      Knight knight = createKnight(in);
      // Start Adventure
      printAdventureBegins(in);
      // Game Loop
      while (!gameOver)
      {
        // Create enemies
        Enemy[] enemies = createEnemies(in);
        // Fight loop
        boolean fighting = true;
        while (fighting)
        {
          // Print Knight stats
          printKnight(knight);
          // Ask for my move
          handleMoves(in, knight, enemies);
          // Attack with enemies
          boolean allEnemiesAreDead = enemiesAttack(in, knight, enemies);
          // Check if I am dead
          if (handleKnightDeath(in, knight))
          {
            gameOver = true;
            fighting = false;
            playAgain = playAgain(in);
          }
          // Check if I won
          if (allEnemiesAreDead)
          {
            knight.levelUp();
            printHeader(knight.getName() + " killed all the enemies!");
            Utility.printFromFile("victory.txt");
            System.out.println(knight.toString());
            System.out.println();
            pressEnter(in);
            fighting = false;
          }
        } // end fighting loop
      } // end game loop
    }
    in.close();
  }

  public static void main(String[] args)
  {
    javaBeanForest();
  }
}
