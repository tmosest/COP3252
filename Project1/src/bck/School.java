package bck;

import java.awt.Color;

public class School {

  private String name;
  private String abr;
  private Color primaryColor;
  private Color secondaryColor;
  private Color thirdColor;

  public School(String name, String abr, Color primaryColor, Color secondaryColor) {
    this.name = name;
    this.abr = abr;
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.thirdColor = null;
  }

  public School(String name, String abr, Color primaryColor, Color secondaryColor, Color thirdColor) {
    this.name = name;
    this.abr = abr;
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.thirdColor = thirdColor;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbr() {
    return abr;
  }

  public void setAbr(String abr) {
    this.abr = abr;
  }

  public Color getPrimaryColor() {
    return primaryColor;
  }

  public void setPrimaryColor(Color primaryColor) {
    this.primaryColor = primaryColor;
  }

  public Color getSecondaryColor() {
    return secondaryColor;
  }

  public void setSecondaryColor(Color secondaryColor) {
    this.secondaryColor = secondaryColor;
  }

  public Color getThirdColor() {
    if (this.thirdColor == null) {
      return this.secondaryColor;
    }
    return this.thirdColor;
  }

  public void setThirdColor(Color thirdColor) {
    this.thirdColor = thirdColor;
  }
}
