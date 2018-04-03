import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class TicTacToePanel implements TicTacToeView {

  private static final Font APP_FONT = new Font("Serif", 1, 28);
  private static final String TITLE = "Tic Tac Toe";

  private String headerText;
  private String footerText;

  private JFrame app;
  private JLabel header;
  private JLabel footer;
  private JMenuBar menuBar;

  private Color backgroundColor;
  private Color forgroundColor;

  private TicTacToeGameView gameView;

  private boolean isOneBot = false;
  private boolean isTwoBot = true;

  public TicTacToePanel()
  {
    this.headerText = "Tic Tac Toe";
    this.footerText = "Tyler Moses";
    this.backgroundColor = new Color(0, 0,0);
    this.forgroundColor = new Color(255, 255, 255);
    this.init();
  }

  public TicTacToePanel(String headerText, String footerText, Color primary, Color secondary)
  {
    this.headerText = headerText;
    this.footerText = footerText;
    this.backgroundColor = primary;
    this.forgroundColor = secondary;
    this.init();
  }

  public JFrame getApp() {
    return app;
  }

  public JLabel getHeader() {
    return header;
  }

  public JLabel getFooter() {
    return footer;
  }

  public JMenuBar getMenuBar() {
    return menuBar;
  }

  private void init()
  {
    // Create app
    this.app = new JFrame(this.headerText);
    this.gameView = new TicTacToeGameView();
    this.initHeader();
    this.initFooter();
    this.initMenu();
  }

  private void initHeader()
  {
    header = new JLabel(TITLE, 0);
    header.setFont(APP_FONT);
    header.setText(this.headerText);
    header.setForeground(this.forgroundColor);
    header.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));
  }

  private void initFooter()
  {
    footer = new JLabel(this.footerText, 0);
    footer.setFont(APP_FONT);
    footer.setForeground(this.forgroundColor);
    footer.setBorder(header.getBorder());
  }

  private void initMenu()
  {
    TicTacToePanel self = this;
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(70);
    JMenuItem newGameItem = new JMenuItem("New Game");
    newGameItem.setActionCommand("new game");
    JMenuItem resetScoresItem = new JMenuItem("Reset Scores");
    resetScoresItem.setActionCommand("reset scores");
    JMenuItem exitItem = new JMenuItem("Exit");
    exitItem.setActionCommand("exit");
    ActionListener fileMenuHandler = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if ("new game".equals(e.getActionCommand())) {
          gameView.newGame();
          gameView.repaint();
        } else if ("reset scores".equals(e.getActionCommand())) {
          gameView.resetScores();
          gameView.repaint();
        } else if ("exit".equals(e.getActionCommand()))
          System.exit(0);
      }
    };
    newGameItem.addActionListener(fileMenuHandler);
    resetScoresItem.addActionListener(fileMenuHandler);
    exitItem.addActionListener(fileMenuHandler);
    fileMenu.add(newGameItem);
    fileMenu.add(resetScoresItem);
    fileMenu.add(exitItem);

    JMenu optionsMenu = new JMenu("Options");
    ButtonGroup xGroup = new ButtonGroup();
    JRadioButtonMenuItem xHumanButton = new JRadioButtonMenuItem("X - Human Player");
    xHumanButton.setActionCommand("XH");
    xHumanButton.setSelected(true);
    JRadioButtonMenuItem xCompButton = new JRadioButtonMenuItem("X - Computer Player");
    xCompButton.setActionCommand("XC");
    xGroup.add(xHumanButton);
    xGroup.add(xCompButton);
    ButtonGroup oGroup = new ButtonGroup();
    JRadioButtonMenuItem oHumanButton = new JRadioButtonMenuItem("O - Human Player");
    oHumanButton.setActionCommand("OH");
    oHumanButton.setSelected(true);
    JRadioButtonMenuItem oCompButton = new JRadioButtonMenuItem("O - Computer Player");
    oCompButton.setActionCommand("OC");
    oGroup.add(oHumanButton);
    oGroup.add(oCompButton);
    ActionListener optionsMenuHandler = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Player player;
        boolean isPlayerOne = false;
        if (e.getActionCommand().charAt(0) == 'X') {
          player = gameView.getGameEngine().getPlayer1();
          isPlayerOne = true;
        } else {
          player = gameView.getGameEngine().getPlayer2();
        }
        if (e.getActionCommand().charAt(1) == 'H') {
          player.setBot(false);
        } else {
          player.setBot(true);
          if (isPlayerOne) {
            self.isOneBot = player.isBot();
          } else {
            self.isTwoBot = player.isBot();
          }
        }
      }
    };
    xHumanButton.addActionListener(optionsMenuHandler);
    xCompButton.addActionListener(optionsMenuHandler);
    oHumanButton.addActionListener(optionsMenuHandler);
    oCompButton.addActionListener(optionsMenuHandler);
    optionsMenu.add(xHumanButton);
    optionsMenu.add(xCompButton);
    optionsMenu.addSeparator();
    optionsMenu.add(oHumanButton);
    optionsMenu.add(oCompButton);


    menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    menuBar.add(optionsMenu);
  }

  public void start()
  {
    app.add(header, "North");
    app.add(footer, "South");
    app.add(gameView, "Center");
    app.setJMenuBar(menuBar);

    app.setDefaultCloseOperation(3);
    app.getContentPane().setBackground(this.backgroundColor);
    app.getContentPane().setForeground(this.forgroundColor);
    app.pack();
    app.setResizable(false);
    app.setVisible(true);

    gameView.run();
  }
}
