
package logicUML.geometricRepresentation;

import java.awt.Point;

public abstract class Figure {

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;
  
  public Figure(int x1, int y1, int x2, int y2){
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public void move(Point p) {
    x1 = x1 + p.x;
    x2 = x2 + p.x;
    y1 = y1 + p.y;
    y2 = y2 + p.y;
  }
  
  public abstract boolean intersects(Point p);
}
