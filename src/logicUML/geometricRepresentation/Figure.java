package logicUML.geometricRepresentation;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class Figure<T> implements Editable<T>, Serializable {

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;

  protected String name;
  protected boolean selected;
  protected Point positionName;
  protected T type;

  public Figure(int x1, int y1, int x2, int y2) {
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

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setPositionName(Point p) {
    this.positionName = p;
    this.x1 = p.x - 50;
    this.y1 = p.y - 25;
    this.x2 = p.x + 50;
    this.y2 = p.y + 25;
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public void setType(T type) {
    this.type = type;
  }

  public abstract boolean intersects(Point p);

  public abstract Figure clonFigure();

  public abstract void draw(Graphics g);

}
