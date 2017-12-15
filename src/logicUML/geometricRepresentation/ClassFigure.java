package logicUML.geometricRepresentation;

import java.awt.Point;

public class ClassFigure extends Figure{

  public ClassFigure(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }

  @Override
  public boolean intersects(Point p) {
    return p.x > x1 && p.y > y1 && p.x < x2 && p.y < y2;
  }

}
