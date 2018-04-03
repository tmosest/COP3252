public class Board
{
  Player[][] board;

  public Board()
  {
    this.board = new Player[3][3];
  }

  public String getMarking(int row, int col)
  {
    if (this.board == null || this.board[row][col] == null)
      return "";
    return this.board[row][col].getCharacter();
  }

  public void setMarking(int row, int col, Player player)
  {
    if (row >= this.board.length || col >= this.board[0].length)
      return;
    this.board[row][col] = player;
  }

  public Player getPlayer(int row, int col)
  {
    return this.board[row][col];
  }

  public boolean isStaleMate()
  {
    for (int i = 0; i < 3; i++)
    {
      for (int j = 0; j < 3; j++)
      {
        if (this.getMarking(i, j).equals(""))
          return false;
      }
    }
    return true;
  }

  public void draw()
  {
    for (int row = 0; row < 3; row++)
    {
      for (int col = 0; col < 3; col++)
      {
        System.out.print(this.getMarking(row,col));
        if (col != 2)
          System.out.print("|");
      }
      System.out.println("");
    }
  }
}
