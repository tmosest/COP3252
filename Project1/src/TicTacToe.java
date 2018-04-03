public class TicTacToe {
  public static final String CREATER = "Tyler Moses";

  private FsuTicTacToePanel ticTacToeView;

  public TicTacToe()
  {
    ticTacToeView = new FsuTicTacToePanel();
  }

  public void start()
  {
    ticTacToeView.start();
  }

  public static void main(String[] args)
  {
    TicTacToe ticTacToe = new TicTacToe();
    ticTacToe.start();
  }
}
