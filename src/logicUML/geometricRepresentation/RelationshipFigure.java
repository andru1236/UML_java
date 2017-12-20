package logicUML.geometricRepresentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import logicUML.behavior.TypeRelationship;

public class RelationshipFigure extends Figure<TypeRelationship>{

  private final double EPS = 0.01;

  private final int OFFSET = 10;
  private final int SIDES_4 = 4;
  private final int SIDES_2 = 2;
  private GeneralPath node;
  private boolean filled;
  private Polygon polygon;
  private boolean dotted;
  private int xf;
  private int yf;
  private final float[] DASHED_STROKE = new float[]{5, 2};
  private final int OFFSET1_DIAGONAL = 5;
  private final int OFFSET_PARALLEL = 3;

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
    clon.name = name;
    clon.selected = selected;
    clon.type = type;
    return (Figure) clon;
  }

  @Override
  public void draw(Graphics g) {
    
    if(selected){
      g.setColor(Color.RED);
    }
    else{
      g.setColor(Color.BLACK);
    }
    
    switch (type) {
      case AGGREGATION:
        filled = false;
        Graphics2D g2d = (Graphics2D) g.create();
        drawRhombus(g2d, x1, y1, x2, y2);
        Graphics2D graph = (Graphics2D) g;
        graph.drawLine(x1, y1, x2, y2);
        break;
      case COMPOSITION:
        filled = true;
        g2d = (Graphics2D) g.create();
        drawRhombus(g2d, x1, y1, x2, y2);
        graph = (Graphics2D) g;
        graph.drawLine(x1, y1, x2, y2);
        break;
      case INHERITANCE:

        g2d = (Graphics2D) g.create();
        g2d.drawLine(x1, y1, x2, y2);
        g2d.setStroke(new BasicStroke());
        drawTriangle(g2d, x1, y1, x2, y2);
        break;
      case IMPLEMENTS:
        g2d = (Graphics2D) g.create();
        basicStroke(g2d);
        g2d.drawLine(x1, y1, x2, y2);
        g2d.setStroke(new BasicStroke());
        drawTriangle(g2d, x1, y1, x2, y2);
        break;
      case ASSOCIATION:
        g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke());
        g2d.drawLine(x1, y1, x2, y2);
        drawTwoLines(g2d, x1, y1, x2, y2);
        break;
    }

  }
  
  public void basicStroke(Graphics2D g2d) {
    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
        BasicStroke.JOIN_BEVEL, 0, DASHED_STROKE, 0));
  }
  
  public void drawTriangle(Graphics2D g2g, int x1, int y1, int x2, int y2) {

    polygon = new Polygon();

    xf = x2;
    yf = y2;

    if (x1 < x2 && y1 == y2) {
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf + OFFSET, yf);
      polygon.addPoint(xf + OFFSET, yf);
    }

    if (x1 > x2 && y1 == y2) {
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf - OFFSET, yf);
      polygon.addPoint(xf - OFFSET, yf);
    }

    if (x1 == x2 && y1 > y2) {
      polygon.addPoint(xf + OFFSET, yf);
      polygon.addPoint(xf - OFFSET, yf);
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf, yf - OFFSET);
    }

    if (x1 == x2 && y1 < y2) {
      polygon.addPoint(xf + OFFSET, yf);
      polygon.addPoint(xf - OFFSET, yf);
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf, yf + OFFSET);
    }

    if (x2 > x1 && y1 > y2) {
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf + OFFSET, yf);
      polygon.addPoint(xf + OFFSET, yf);
    }

    if (x1 > x2 && y1 > y2) {
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf - OFFSET, yf);
      polygon.addPoint(xf - OFFSET, yf);
    }

    if (x1 > x2 && y1 < y2) {
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf - OFFSET, yf);
      polygon.addPoint(xf - OFFSET, yf);
    }

    if (x1 < x2 && y1 < y2) {
      polygon.addPoint(xf, yf - OFFSET);
      polygon.addPoint(xf, yf + OFFSET);
      polygon.addPoint(xf + OFFSET, yf);
      polygon.addPoint(xf + OFFSET, yf);
    }

    g2g.drawPolygon(polygon);
  }
  
  public void drawTwoLines(Graphics2D g2d, int x1, int y1, int x2, int y2) {

    node = new GeneralPath(GeneralPath.WIND_EVEN_ODD, SIDES_2);
    xf = x2;
    yf = y2;

    if (x2 > x1 && y1 == y2) {
      node.moveTo(xf - OFFSET_PARALLEL, yf - OFFSET_PARALLEL);
      node.lineTo(xf, yf);
      node.moveTo(xf - OFFSET_PARALLEL, yf + OFFSET_PARALLEL);
      node.lineTo(xf, yf);
    }

    if (x1 == x2 && y1 > y2) {
      node.moveTo(xf - OFFSET_PARALLEL, yf + OFFSET_PARALLEL);
      node.lineTo(xf, yf);
      node.moveTo(xf + OFFSET_PARALLEL, yf + OFFSET_PARALLEL);
      node.lineTo(xf, yf);
    }

    if (x1 > x2 && y1 == y2) {
      node.moveTo(xf + OFFSET_PARALLEL, yf - OFFSET_PARALLEL);
      node.lineTo(xf, yf);
      node.moveTo(xf + OFFSET_PARALLEL, yf + OFFSET_PARALLEL);
      node.lineTo(xf, yf);
    }

    if (x1 == x2 && y1 < y2) {
      node.moveTo(xf - OFFSET_PARALLEL, yf - OFFSET_PARALLEL);
      node.lineTo(xf, yf);
      node.moveTo(xf + OFFSET_PARALLEL, yf - OFFSET_PARALLEL);
      node.lineTo(xf, yf);
    }

    if (x2 > x1 && y1 > y2) {
      node.moveTo(xf - OFFSET1_DIAGONAL, yf);
      node.lineTo(xf, yf);
      node.moveTo(xf, yf + OFFSET1_DIAGONAL);
      node.lineTo(xf, yf);
    }

    if (x1 > x2 && y1 > y2) {
      node.moveTo(xf + OFFSET1_DIAGONAL, yf);
      node.lineTo(xf, yf);
      node.moveTo(xf, yf + OFFSET1_DIAGONAL);
      node.lineTo(xf, yf);
    }

    if (x1 > x2 && y1 < y2) {
      node.moveTo(xf + OFFSET1_DIAGONAL, yf);
      node.lineTo(xf, yf);
      node.moveTo(xf, yf - OFFSET1_DIAGONAL);
      node.lineTo(xf, yf);
    }

    if (x1 < x2 && y1 < y2) {
      node.moveTo(xf - OFFSET1_DIAGONAL, yf);
      node.lineTo(xf, yf);
      node.moveTo(xf, yf - OFFSET1_DIAGONAL);
      node.lineTo(xf, yf);
    }

    g2d.draw(node);
  }
  
  public void drawRhombus(Graphics2D g2d, int x1, int y1, int x2, int y2) {

    node = new GeneralPath(GeneralPath.WIND_EVEN_ODD, SIDES_4);

    if ((x2 > x1 && y1 > y2) || (x2 > x1 && y1 == y2)) {
      xf = x2 + OFFSET;
      yf = y2;
      buildRhombus();
    }

    if ((x1 > x2 && y1 > y2) || (x1 == x2 && y1 > y2)) {
      xf = x2;
      yf = y2 - OFFSET;
      buildRhombus();
    }

    if ((x1 > x2 && y1 < y2) || (x1 > x2 && y1 == y2)) {
      xf = x2 - OFFSET;
      yf = y2;
      buildRhombus();
    }

    if ((x1 < x2 && y1 < y2) || (x1 == x2 && y1 < y2)) {
      xf = x2;
      yf = y2 + OFFSET;
      buildRhombus();
    }

    if (filled) {
      g2d.fill(node);
    } else {
      g2d.draw(node);
    }
  }

  public void buildRhombus() {
    node.moveTo(xf, yf - OFFSET);
    node.lineTo(xf - OFFSET, yf);
    node.lineTo(xf, yf + OFFSET);
    node.lineTo(xf + OFFSET, yf);
    node.lineTo(xf, yf - OFFSET);
  }

  @Override
  public void setType(TypeRelationship type) {
    this.type = type;
  }
  
}
