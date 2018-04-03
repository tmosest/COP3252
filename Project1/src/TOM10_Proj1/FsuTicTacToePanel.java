import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FsuTicTacToePanel extends TicTacToePanel {

  private static final School[] SCHOOLS = SchoolManager.ALL_SCHOOL;

  public FsuTicTacToePanel()
  {
    super(
        SchoolManager.FSU.getAbr(),
        TicTacToe.CREATER,
        SchoolManager.FSU.getPrimaryColor(),
        SchoolManager.FSU.getSecondaryColor()
    );
    initSchoolsMenu();
  }

  public FsuTicTacToePanel(School school, String footer)
  {
    super(school.getAbr(), footer, school.getPrimaryColor(), school.getSecondaryColor());
    initSchoolsMenu();
  }

  public void initSchoolsMenu()
  {
    JMenuBar menuBar = this.getMenuBar();
    JMenu schoolMenu = new JMenu("Schools");
    FsuTicTacToePanel ticTacToePanel = this;
    ActionListener schoolMenuHandler = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < SCHOOLS.length; i++) {
          if (SCHOOLS[i].getName().equals(e.getActionCommand())) {
            ticTacToePanel.updateSchool( SCHOOLS[i]);
            return;
          }
        }
      }
    };

    for(int i = 0; i < SCHOOLS.length; i++) {
      JMenuItem newSchoolItem = new JMenuItem(SCHOOLS[i].getName());
      newSchoolItem.setActionCommand(newSchoolItem.getName());
      newSchoolItem.addActionListener(schoolMenuHandler);
      schoolMenu.add(newSchoolItem);
    }

    menuBar.add(schoolMenu);
  }

  private void updateSchool(School school)
  {
    JLabel header = this.getHeader();
    header.setText(school.getAbr());
    header.setForeground(school.getSecondaryColor());
    JLabel footer = this.getFooter();
    footer.setForeground(school.getSecondaryColor());
    JFrame app = this.getApp();
    app.getContentPane().setBackground(school.getPrimaryColor());
    app.getContentPane().setForeground(school.getSecondaryColor());
  }
}
