package bck;

import java.awt.Graphics2D;

public class TTTPanel extends javax.swing.JPanel implements Runnable {
  private static final int CELL_DIMENSION = 100;
  private static final int INFO_HEIGHT = 50;
  private TTTEngine game;
  private TTTPanel.PlayerType XPlayType;
  private TTTPanel.PlayerType OPlayType;
  private Boolean playAgain;
  private Boolean restartGame;
  private int XWins;
  private int OWins;

  public static enum PlayerType {
    HUMAN,  COMPUTER;
  }

  public TTTPanel()
  {
    game = new TTTEngine();
    XPlayType = (this.OPlayType = TTTPanel.PlayerType.HUMAN);
    XWins = (this.OWins = 0);
    playAgain = Boolean.valueOf(true);
    restartGame = Boolean.valueOf(false);

    setPreferredSize(new java.awt.Dimension(300,
        350));
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent e) {
        super.mousePressed(e);
        TTTPanel.this.handleClick(e.getX(), e.getY());
      }
    });
  }

  public void newGame() {
    restartGame = Boolean.valueOf(true);
    playAgain = Boolean.valueOf(true);
  }

  public void resetScores() {
    XWins = (this.OWins = 0);
    repaint();
  }

  public void setXPlayType(TTTPanel.PlayerType t) {
    if (XPlayType != t)
      restartGame = Boolean.valueOf(true);
    XPlayType = t;
  }

  public void setOPlayType(TTTPanel.PlayerType t) {
    if (OPlayType != t)
      restartGame = Boolean.valueOf(true);
    OPlayType = t;
  }

  public void run()
  {
    for (;;) {
      restartGame = Boolean.valueOf(false);
      game.reset();
      repaint();
      gameLoop();
      while (!playAgain.booleanValue())
      {

        try
        {

          Thread.sleep(500L);
        } catch (InterruptedException localInterruptedException) {} }
    }
  }

  private void handleClick(int x, int y) {
    switch (game.getState()) {
      case CAT:
        if (XPlayType == TTTPanel.PlayerType.COMPUTER)
          return;
        break;
      case O_TURN:
        if (OPlayType == TTTPanel.PlayerType.COMPUTER)
          return;
        break;
      default:
        return;
    }

    int row = y / 100;
    int col = x / 100;
    if (game.makeMove(row, col).booleanValue()) {}
    repaint();
  }

  private void makeMoveAI()
  {
    try
    {
      Thread.sleep(350L);
    } catch (InterruptedException localInterruptedException) {}
    game.makeMoveAI();
    repaint();
  }

  private void gameLoop()
  {
    for (;;) {
      if (restartGame.booleanValue()) {
        return;
      }
      switch (game.getState()) {
        case CAT:
          if (XPlayType == TTTPanel.PlayerType.HUMAN) {
            try { Thread.sleep(100L);

            }
            catch (InterruptedException localInterruptedException) {}
          } else
            makeMoveAI();
          break;
        case O_TURN:
          if (OPlayType == TTTPanel.PlayerType.HUMAN) {
            try { Thread.sleep(100L);
            }
            catch (InterruptedException localInterruptedException1) {}
          } else
            makeMoveAI();
          break;
        case O_WON:
          gameOver("Cat Game! Would you like to play again?");
          return;
        case X_TURN:
          XWins += 1;
          repaint();
          gameOver("X Wins! Would you like to play again?");
          return;
        case X_WON:
          OWins += 1;
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
        switch (game.getCell(row, col))
        {
          case O:
            g2.drawLine(col * 100 + space, row * 100 + space,
                (col + 1) * 100 - space, (row + 1) * 100 - space);

            g2.drawLine(col * 100 + space, (row + 1) * 100 - space,
                (col + 1) * 100 - space, row * 100 + space);
            break;
          case X:
            g2.drawOval(col * 100 + space, row * 100 + space,
                100 - 2 * space, 100 - 2 * space);
        }

      }
    }

    String xScore = String.format("X (%s): %d", new Object[] { XPlayType == TTTPanel.PlayerType.HUMAN ?
        "Human" : "Computer", Integer.valueOf(XWins) });
    String oScore = String.format("O (%s): %d", new Object[] { OPlayType == TTTPanel.PlayerType.HUMAN ?
        "Human" : "Computer", Integer.valueOf(OWins) });
    g2.drawString(xScore, space, 300 + space);
    g2.drawString(oScore, space, 300 + 2 * space);
    String status = new String();
    switch (game.getState()) {
      case CAT:
        status = "X Turn";
        break;
      case O_TURN:
        status = "O Turn";
        break;
      case O_WON:
        status = "Cat Game";
        break;
      case X_TURN:
        status = "X Wins";
        break;
      case X_WON:
        status = "O Wins";
    }

    g2.drawString(status, 200, 300 + (int)(1.5D * space));
  }
}
