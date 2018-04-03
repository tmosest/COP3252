public class Player
{
  public static final String PLAYER_X = "X";
  public static final String PLAYER_O = "O";

  String character;
  boolean isBot;

  public Player(String character, boolean isBot)
  {
    this.character = character;
    this.isBot = isBot;
  }

  public boolean isBot()
  {
    return isBot;
  }

  public void setBot(boolean bot)
  {
    isBot = bot;
  }

  public String getCharacter()
  {
    return character;
  }

  public boolean isWinner(Board board)
  {
    for (int i = 0; i < 3; i++)
    {
      String player = board.getMarking(i,i);
      if (!this.getCharacter().equals(player))
        break;
      if (i == 2)
        return true;
    }
    for (int i = 0; i < 3; i++)
    {
      String player = board.getMarking(i, 3 - 1 - i);
      if (!this.getCharacter().equals(player))
        break;
      if (i == 2)
        return true;
    }
    for (int a = 0; a < 2; a++)
    {
      // Check Rows
      for (int i = 0; i < 3; i++)
      {
        // Check each column in the row
        for (int j = 0; j < 3; j++)
        {
          String player = board.getMarking(i, j);
          if (a == 1) player = board.getMarking(j, i);
          if (!this.getCharacter().equals(player))
            break;
          if (j == 2)
            return true;
        }
      }
    }
    return false;
  }
}
