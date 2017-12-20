package view.tools;

import java.awt.Point;
import logicUML.Scheme;

public interface Tool {
  
  public String getName();

  public void startAction(Point p, Scheme scheme);

  public void continueAction(Point p);

  public void endAction(Point p);
}
