public class TicTacToeGameEngine
{
  public enum GAME_STATES {
    PLAYER_ONE_TURN,
    PLAYER_TWO_TURN,
    WINNER_PLAYER_1,
    WINNER_PLAYER_2,
    DRAW
  }

  private Player player1;
  private Player player2;
  private Board board;
  private GAME_STATES state;
  private TicTacToeAI.AI_LEVEL aiLevel;

  public TicTacToeGameEngine()
  {
    this.aiLevel = TicTacToeAI.AI_LEVEL.RANDOM;
    player1 = new Player(Player.PLAYER_X, false);
    player2 = new Player(Player.PLAYER_O, false);
    this.newGame();
  }

  public void setAiLevel(TicTacToeAI.AI_LEVEL aiLevel) {
    this.aiLevel = aiLevel;
  }

  public boolean makeMoveAI()
  {
    return makeMove(0,0);
  }

  public boolean makeMove(int row, int col)
  {
    // Check if the entry is empty
    if (!getCell(row, col).equals(""))
      return false;
    // Set the cell value
    setCell(row, col);
    // Handle State Changes
    if (state == GAME_STATES.PLAYER_ONE_TURN)
    {
      state = GAME_STATES.PLAYER_TWO_TURN;
      if (isPlayerOneWinner())
      {
        state = GAME_STATES.WINNER_PLAYER_1;
      }
    }
    else if (state == GAME_STATES.PLAYER_TWO_TURN)
    {
      state = GAME_STATES.PLAYER_ONE_TURN;
      if (isPlayerTwoWinner())
      {
        state = GAME_STATES.WINNER_PLAYER_2;
      }
    }
    if (isStaleMate())
    {
      state = GAME_STATES.DRAW;
    }
    System.out.println(this.convertStateToName());
    return true;
  }

  public void newGame()
  {
    board = new Board();
    state = GAME_STATES.PLAYER_ONE_TURN;
  }

  public GAME_STATES getState()
  {
    return state;
  }

  public void setState(GAME_STATES state)
  {
    this.state = state;
  }

  public Player getPlayer1()
  {
    return player1;
  }

  public Player getPlayer2()
  {
    return player2;
  }

  public String getCell(int row, int col)
  {
    return this.board.getMarking(row, col);
  }

  public void setCell(int row, int col)
  {
    switch (state)
    {
      case PLAYER_ONE_TURN:
        if (this.getPlayer1().isBot())
          TicTacToeAI.makeMove(board, this.aiLevel, player1);
        else
          this.board.setMarking(row, col, player1);
        break;
      case PLAYER_TWO_TURN:
        if (this.getPlayer2().isBot())
          TicTacToeAI.makeMove(board, this.aiLevel, player2);
        else
          this.board.setMarking(row, col, player2);
        break;
    }
    // Draw debug of the board
    this.board.draw();
  }

  public boolean isPlayerOne(String character)
  {
    return this.player1.character.equals(character);
  }

  public boolean isSecondPlayer(String character)
  {
    return this.player2.character.equals(character);
  }

  public boolean isPlayerOneWinner()
  {
   return this.getPlayer1().isWinner(this.board);
  }

  public boolean isPlayerTwoWinner()
  {
    return this.getPlayer2().isWinner(this.board);
  }

  public boolean isStaleMate()
  {
    return this.board.isStaleMate();
  }

  public String convertStateToName()
  {
    switch (this.state)
    {
      case PLAYER_ONE_TURN:
        return "Player " + this.getPlayer1().getCharacter() + " turn";
      case PLAYER_TWO_TURN:
        return "Player " + this.getPlayer2().getCharacter() + " turn";
      case WINNER_PLAYER_1:
        return "Player " + this.getPlayer1().getCharacter() + " won!";
      case WINNER_PLAYER_2:
        return "Player " + this.getPlayer2().getCharacter() + " won!";
      default:
        return "Draw!";
    }
  }
}
