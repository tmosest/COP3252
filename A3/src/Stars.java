/*
Moses, Tyler
COP-3252
Assignment 3
01/13/2018
*/
public class Stars
{

  private int rows;
  private int columns;

  public Stars(int rows, int columns)
  {
    this.rows = rows;
    this.columns = columns;
  }

  public int getColumns()
  {
    return columns;
  }

  public int getRows()
  {
    return rows;
  }

  public void setColumns(int columns)
  {
    this.columns = columns;
  }

  public void setRows(int rows)
  {
    this.rows = rows;
  }

  public String toString() {
    return "rows: " + rows + "\ncolumns: " + columns;
  }
}
