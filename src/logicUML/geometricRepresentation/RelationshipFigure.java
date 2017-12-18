package logicUML.geometricRepresentation;

import java.awt.Point;

public class RelationshipFigure extends Figure{

  private final double EPS = 0.01;

  public RelationshipFigure(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }

  @Override
  public boolean intersects(Point p) {
    int xv1 = p.x - x1;
    int yv1 = p.y - y1;
    int xv2 = x2 - x1;
    int yv2 = y2 - y1;
    double v1xv2 = (xv1 * yv2 - xv2 * yv1) / (Math.hypot(x1, y1)
            * Math.hypot(x2, y2));
    double angle = Math.asin(v1xv2);
    boolean res = Math.abs(angle) < EPS && Math.abs(p.x - x2)
            + Math.abs(xv1) == Math.abs(xv2) && Math.abs(p.y - y2)
            + Math.abs(yv1) == Math.abs(yv2);
    return res;
  }
  
  @Override
  public Figure clonFigure() {
    RelationshipFigure clon = new RelationshipFigure(x1, y1, x2, y2);
    return (Figure) clon;
  }
  
}
