import java.awt.Color;

public class SchoolManager {

  /*
    Florida Schools
   */

  public static final School FAU = new School(
      "Florida Atlantic University:",
      "FAU",
      new Color(193, 4, 53),
      new Color(0, 4,98)
  );

  public static final School FIU = new School(
    "Florida International University",
      "FIU",
      new Color(8, 30, 63),
      new Color(182,134,44)
  );

  public static final School FSU = new School(
      "Florida State University",
      "FSU",
      new Color(84, 1, 21),
      new Color(205, 192, 146)
  );

  public static final School UF = new School(
      "University of Florida",
      "UF",
      new Color(0, 33, 165),
      new Color(250, 70, 22)
  );

  public static final School[] ALL_SCHOOL = new School[] {
      FAU, FIU, FSU, UF
  };
}
