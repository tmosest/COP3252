import java.awt.Color;
import java.awt.Container;
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

public class TipTacToe {
  private static final String CREATER = "Tyler Moses";
  private static final Font APP_FONT = new Font("Serif", 1, 28);

  private static School[] schools = SchoolManager.ALL_SCHOOL;
  private static School school = SchoolManager.FSU;

  private static Color BG_COLOR = school.getPrimaryColor();
  private static Color FG_COLOR = school.getSecondaryColor();

  public static void main(String[] args) {
    JFrame app = new JFrame("TicTacToe");

    JLabel header = new JLabel(school.getAbr(), 0);
    header.setFont(APP_FONT);
    header.setForeground(FG_COLOR);
    header.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));

    JLabel footer = new JLabel(CREATER, 0);
    footer.setFont(APP_FONT);
    footer.setForeground(FG_COLOR);
    footer.setBorder(header.getBorder());

    TTTPanel gamePanel = new TTTPanel();

    JMenu schoolMenu = new JMenu("Schools");
    ActionListener schoolMenuHandler = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < schools.length; i++) {
          if (schools[i].getName().equals(e.getActionCommand())) {
            school = schools[i];
            header.setText(school.getAbr());
            header.setForeground(school.getSecondaryColor());
            footer.setForeground(school.getSecondaryColor());
            app.getContentPane().setBackground(school.getPrimaryColor());
            app.getContentPane().setForeground(school.getSecondaryColor());
          }
        }
      }
    };

    for(int i = 0; i < schools.length; i++) {
      JMenuItem newSchoolItem = new JMenuItem(schools[i].getName());
      newSchoolItem.setActionCommand(newSchoolItem.getName());
      newSchoolItem.addActionListener(schoolMenuHandler);
      schoolMenu.add(newSchoolItem);
    }

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
          gamePanel.newGame();
        } else if ("reset scores".equals(e.getActionCommand())) {
          gamePanel.resetScores();
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
        TTTPanel.PlayerType type;
        if (e.getActionCommand().charAt(1) == 'H') {
          type = TTTPanel.PlayerType.HUMAN;
        } else {
          type = TTTPanel.PlayerType.COMPUTER;
        }
        if (e.getActionCommand().charAt(0) == 'X') {
          gamePanel.setXPlayType(type);
        } else
          gamePanel.setOPlayType(type);
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


    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu);
    menuBar.add(optionsMenu);
    menuBar.add(schoolMenu);

    app.add(header, "North");
    app.add(footer, "South");
    app.add(gamePanel, "Center");
    app.setJMenuBar(menuBar);

    app.setDefaultCloseOperation(3);
    app.getContentPane().setBackground(BG_COLOR);
    app.getContentPane().setForeground(FG_COLOR);
    app.pack();
    app.setResizable(false);
    app.setVisible(true);

    gamePanel.run();
  }
}
