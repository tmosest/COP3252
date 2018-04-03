import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Utility class.
 */
public class Utility
{

  /**
   * Selects a random number between [min, max]
   * @param min Minimum number within the range.
   * @param max Maximum number within the range.
   * @return Random integer.
   */
  public static int randomWithRange(int min, int max)
  {
    int range = (max - min) + 1;
    return (int)(Math.random() * range) + min;
  }

  public static void printFromFile(String filename)
  {
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }
    catch (Exception e)
    {
      System.out.println("Error Reading file from " + filename + " message: " + e.getMessage());
    }
  }
}
