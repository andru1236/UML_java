package view.tools;

import java.awt.Point;
import logicUML.Scheme;

public class Move implements Tool{

  private String nameTool;

  public Move() {
    nameTool = "Move";
  }
  
  @Override
  public String getName() {
    return nameTool;
  }

  @Override
  public void startAction(Point p, Scheme scheme) {
    scheme.move(p);
  }

  @Override
  public void continueAction(Point p, Scheme scheme) {
  }

  @Override
  public void endAction(Point p, Scheme scheme) {
    
  }
  
}
