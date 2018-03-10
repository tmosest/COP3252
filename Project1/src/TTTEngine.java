import java.util.Random;

public class TTTEngine { private TTTEngine.Cell[][] cells;
  private TTTEngine.GameState state;
  private int filledCells;

  public static enum Cell { EMPTY,  X,  O}

  public static enum GameState { X_TURN,  O_TURN,  CAT,  X_WON,  O_WON}

  public TTTEngine()
  {
    cells = new TTTEngine.Cell[3][3];
    reset();
  }

  public TTTEngine.Cell getCell(int row, int col)
  {
    return cells[row][col];
  }

  private void setCell(int row, int col, TTTEngine.Cell entry)
  {
    cells[row][col] = entry;
  }

  public TTTEngine.GameState getState()
  {
    return state;
  }

  private void setState(TTTEngine.GameState st)
  {
    state = st;
  }

  public void reset()
  {
    Random rand = new Random();
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        setCell(i, j, TTTEngine.Cell.EMPTY);
    filledCells = 0;
    setState(rand.nextBoolean() ? TTTEngine.GameState.X_TURN : TTTEngine.GameState.O_TURN);
  }




  private Boolean isWinningCell(int row, int col)
  {
    if (getCell(row, col) == TTTEngine.Cell.EMPTY) {
      return Boolean.valueOf(false);
    }

    if ((getCell(row, 0) == getCell(row, 1)) &&
        (getCell(row, 1) == getCell(row, 2))) {
      return Boolean.valueOf(true);
    }

    if ((getCell(0, col) == getCell(1, col)) &&
        (getCell(1, col) == getCell(2, col))) {
      return Boolean.valueOf(true);
    }

    if ((row == col) &&
        (getCell(0, 0) == getCell(1, 1)) &&
        (getCell(1, 1) == getCell(2, 2)))
      return Boolean.valueOf(true);
    if ((row + col == 2) &&
        (getCell(0, 2) == getCell(1, 1)) &&
        (getCell(1, 1) == getCell(2, 0))) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }



  public Boolean makeMove(int row, int col)
  {
    if ((row < 0) || (row > 2) || (col < 0) || (col > 2))
      return Boolean.valueOf(false);
    if (getCell(row, col) != TTTEngine.Cell.EMPTY)
      return Boolean.valueOf(false);
    TTTEngine.Cell entry;
    switch (getState()) {
      case CAT:
        entry = TTTEngine.Cell.X;
        break;
      case O_TURN:
        entry = TTTEngine.Cell.O;
        break;
      default:
        return Boolean.valueOf(false);
    }

    setCell(row, col, entry);
    filledCells += 1;


    if (isWinningCell(row, col).booleanValue()) {
      setState(getState() == TTTEngine.GameState.X_TURN ?
          TTTEngine.GameState.X_WON : TTTEngine.GameState.O_WON);
    } else if (filledCells == 9) {
      setState(TTTEngine.GameState.CAT);
    } else {
      setState(getState() == TTTEngine.GameState.X_TURN ?
          TTTEngine.GameState.O_TURN : TTTEngine.GameState.X_TURN);
    }

    return Boolean.valueOf(true);
  }




  public boolean makeMoveAI()
  {
    TTTEngine.Cell opp;
    TTTEngine.Cell mark;
    switch (getState()) {
      case CAT:
        mark = TTTEngine.Cell.X;
        opp = TTTEngine.Cell.O;
        break;
      case O_TURN:
        mark = TTTEngine.Cell.O;
        opp = TTTEngine.Cell.X;
        break;
      default:
        return false;
    }
    java.util.ArrayList<int[]> possibleMoves = new java.util.ArrayList();
    int col; for (int row = 0; row < 3; row++) {
    for (col = 0; col < 3; col++) {
      if (getCell(row, col) == TTTEngine.Cell.EMPTY)
        possibleMoves.add(new int[] { row, col });
    }
  }
    Boolean winner = Boolean.valueOf(false);
    for (int[] move : possibleMoves)
    {
      setCell(move[0], move[1], mark);
      winner = isWinningCell(move[0], move[1]);
      setCell(move[0], move[1], TTTEngine.Cell.EMPTY);

      if (winner.booleanValue())
      {
        return makeMove(move[0], move[1]).booleanValue();
      }
    }


    for (int[] move : possibleMoves) {
      setCell(move[0], move[1], opp);
      winner = isWinningCell(move[0], move[1]);
      setCell(move[0], move[1], TTTEngine.Cell.EMPTY);

      if (winner.booleanValue())
      {
        return makeMove(move[0], move[1]).booleanValue();
      }
    }


    Random rand = new Random();
    int[] move = (int[])possibleMoves.get(rand.nextInt(possibleMoves.size()));
    return makeMove(move[0], move[1]).booleanValue();
  }
}
