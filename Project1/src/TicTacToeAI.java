import java.util.ArrayList;

public class TicTacToeAI {

  public enum AI_LEVEL
  {
    RANDOM;
  }

  public static void makeMove(Board board, AI_LEVEL level, Player player)
  {
    System.out.println("Making move " + level + " for player " + player.getCharacter());
    switch (level) {
      case RANDOM:
      makeRandomMove(board, player);
      return;
    }
  }

  private static void makeRandomMove(Board board, Player player)
  {
    ArrayList<Board> moves = getAllMoves(board, player);
    int  move = (int)(Math.random() * moves.size());
    System.out.println("move : " + move + " selected");
    copyMove(moves.get(move), board);
  }

  // TODO Alpha-Beta pruning...
  private static void makeSmartMove(Board board, Player player)
  {
    ArrayList<Board> moves = getAllMoves(board, player);
  }

  private static ArrayList<Board> getAllMoves(Board board, Player player)
  {
    ArrayList<Board> moves = new ArrayList<Board>();
    for (int i = 0; i < 3; i++)
    {
      for (int j = 0; j < 3; j++)
      {
        if (board.getPlayer(i, j) == null)
        {
          Board move = copyBoard(board);
          move.setMarking(i, j, player);
          moves.add(move);
        }
      }
    }
    return moves;
  }

  private static void copyMove(Board toCopy, Board copyTo)
  {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        copyTo.setMarking(i, j, toCopy.getPlayer(i, j));
  }

  private static Board copyBoard(Board board)
  {
    Board copy = new Board();
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        copy.setMarking(i, j, board.getPlayer(i, j));
    return  copy;
  }
}
