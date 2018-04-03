import java.awt.Graphics2D;

public class TicTacToeGameView extends javax.swing.JPanel implements Runnable {

  private TicTacToeGameEngine gameEngine;
  private boolean restartGame = false;
  private boolean playAgain = true;
  private int wins1 = 0;
  private int wins2 = 0;

  public TicTacToeGameView()
  {
    gameEngine = new TicTacToeGameEngine();
    gameEngine.newGame();
    setPreferredSize(
        new java.awt.Dimension(300,350)
    );
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent e) {
        super.mousePressed(e);
        TicTacToeGameView.this.handleClick(e.getX(), e.getY());
      }
    });
  }

  public void resetScores()
  {
    this.wins1 = this.wins2 = 0;
  }

  private void handleClick(int x, int y) {
    int row = y / 100;
    int col = x / 100;
    System.out.println("row: " + row + " col: " + col);
    gameEngine.makeMove(row, col);
    switch (gameEngine.getState()) {
      case PLAYER_ONE_TURN:
        if (gameEngine.getPlayer1().isBot())
          gameEngine.makeMove(0, 0);
        break;
      case PLAYER_TWO_TURN:
        if (gameEngine.getPlayer2().isBot())
          gameEngine.makeMove(0, 0);
        break;
    }
    repaint();
  }


  public void newGame()
  {
    restartGame = false;
    gameEngine.newGame();
    this.repaint();
  }

  public void run()
  {
    for (;;) {
      restartGame = false;
      repaint();
      gameLoop();
      while (!playAgain)
      {

        try
        {

          Thread.sleep(500L);
        } catch (InterruptedException localInterruptedException) {} }
    }
  }

  public void gameLoop()
  {
    if (restartGame)
    {
      restartGame = false;
      gameEngine.newGame();
      this.repaint();
    }
    for (;;) {
      if (restartGame) {
        return;
      }
      switch (gameEngine.getState()) {
        case PLAYER_ONE_TURN:
          if (!gameEngine.getPlayer1().isBot()) {
            try { Thread.sleep(100L);

            }
            catch (InterruptedException localInterruptedException) {}
          } else {
            gameEngine.makeMoveAI();
            try { Thread.sleep(100L);

            }
            catch (InterruptedException localInterruptedException) {}
            repaint();
          }
          break;
        case PLAYER_TWO_TURN:
          if (!gameEngine.getPlayer2().isBot()) {
            try { Thread.sleep(100L);
            }
            catch (InterruptedException localInterruptedException1) {}
          } else {
            gameEngine.makeMoveAI();
            try { Thread.sleep(100L);

            }
            catch (InterruptedException localInterruptedException) {}
            repaint();
          }
          break;
        case DRAW:
          gameOver("Cat Game! Would you like to play again?");
          return;
        case WINNER_PLAYER_1:
          wins1 += 1;
          repaint();
          gameOver("X Wins! Would you like to play again?");
          return;
        case WINNER_PLAYER_2:
          wins2 += 1;
          repaint();
          gameOver("O Wins! Would you like to play again?");
          return;
      }
    }
  }

  private void gameOver(String msg)
  {
    int choice = javax.swing.JOptionPane.showOptionDialog(this, msg, "Game Over",
        0, 3,
        null, null, javax.swing.JOptionPane.UNINITIALIZED_VALUE);

    playAgain = Boolean.valueOf(choice == 0);
    if (playAgain) {
      newGame();
      repaint();
    }
  }

  public TicTacToeGameEngine getGameEngine() {
    return gameEngine;
  }

  public void paintComponent(java.awt.Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;


    g2.setColor(getParent().getBackground());
    g2.fillRect(0, 0, getWidth(), getHeight());


    g2.setColor(getParent().getForeground());
    g2.setStroke(new java.awt.BasicStroke(3.0F));
    g2.drawLine(100, 0, 100, 300);
    g2.drawLine(200, 0, 200, 300);
    g2.drawLine(0, 100, 300, 100);
    g2.drawLine(0, 200, 300, 200);


    int space = 20;
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (gameEngine.isPlayerOne(gameEngine.getCell(row, col)))
        {

            g2.drawLine(col * 100 + space, row * 100 + space,
                (col + 1) * 100 - space, (row + 1) * 100 - space);

            g2.drawLine(col * 100 + space, (row + 1) * 100 - space,
                (col + 1) * 100 - space, row * 100 + space);
        }
        else if(gameEngine.isSecondPlayer(gameEngine.getCell(row, col)))
        {
            g2.drawOval(col * 100 + space, row * 100 + space,
                100 - 2 * space, 100 - 2 * space);
        }

      }
    }

   String xScore = String.format(
       gameEngine.getPlayer1().getCharacter() +
       " (%s): %d", !gameEngine.getPlayer1().isBot() ?
        "Human" : "Computer", wins1);
   String oScore = String.format(
       gameEngine.getPlayer2().getCharacter() +
       " (%s): %d", !gameEngine.getPlayer2().isBot() ?
        "Human" : "Computer", wins2);
   g2.drawString(xScore, space, 300 + space);
   g2.drawString(oScore, space, 300 + 2 * space);
   g2.drawString(gameEngine.convertStateToName(), 200, 300 + (int)(1.5D * space));
  }
}
