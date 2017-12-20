package logicUML.geometricRepresentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import logicUML.behavior.TypeClass;

public class ClassFigure extends Figure<TypeClass>{
  
  private static final String classAbstract = "< A >";
  private static final String classInterface = "< I >";  

  public ClassFigure(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }
  
  @Override
  public boolean intersects(Point p) {
    return p.x > x1 && p.y > y1 && p.x < x2 && p.y < y2;
  }
  
  @Override
  public Figure clonFigure() {
    ClassFigure clon = new ClassFigure(x1, y1, x2, y2);
    clon.name = name;
    clon.positionName = new Point(positionName.x, positionName.y);
    clon.selected = selected;
    clon.type = type;
    return (Figure) clon;
  }

  @Override
  public void draw(Graphics g) {
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1; 
    int h = Math.abs(y1 - y2) + 1; 
    
    g.setColor(Color.WHITE);
    g.fillRect(x, y, w, h);
    
    if(selected){
      g.setColor(Color.RED);
    }
    else{
      g.setColor(Color.BLACK);
    }
    
    g.drawRect(x, y, w, h); 
    g.drawString(name, positionName.x - 20 , positionName.y - 10);
    g.drawLine(x1, y1 + 25, x2, y2 - 25);
    
    switch(type){
      case ABTRACT:
        g.drawString(classAbstract, positionName.x + 15 , positionName.y - 28);
        break;
      case INTERFACE:
        g.drawString(classInterface, positionName.x + 15, positionName.y - 28);        
        break;
    }
  }

  @Override
  public void setType(TypeClass type) {
    this.type = type;
  }

}
