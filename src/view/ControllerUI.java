package view;

import view.tools.Tool;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import logicUML.Scheme;

public class ControllerUI implements MouseListener, MouseMotionListener{
  
  private Tool tool;
  private Scheme scheme;
  private DesingUML view;
  
  public ControllerUI(){
    scheme = new Scheme();
    view = new DesingUML("UML Diagram", this);
    
    view.addScheme(scheme);
    view.addController(this);
  }
  
  public void undo(){
    scheme.undo();
    view.draw();
  }

  public void redo(){
    scheme.redo();
    view.draw();
  }
  
  public void inheritance(){
    
  }
  
  public void association(){
    
  }
  
  public void aggregation(){
    
  }
  
  public void composition(){
    
  }
  
  public void implementsRelation(){
    
  }
  
  @Override
  public void mousePressed(MouseEvent me) {
    Point p = me.getPoint();
    tool.startAction(p, scheme);
  }
  
  @Override
  public void mouseReleased(MouseEvent me) {
    view.draw();
  }
  
  public void setTool(Tool tool){
    this.tool = tool;
  }

  public Tool getTool(){
    return tool;
  }
  
  @Override
  public void mouseDragged(MouseEvent me) {
  }

  @Override
  public void mouseEntered(MouseEvent me) {
  }

  @Override
  public void mouseExited(MouseEvent me) {
  }

  @Override
  public void mouseMoved(MouseEvent me) {
  }
  
  @Override
  public void mouseClicked(MouseEvent me) {
  }
  
}
