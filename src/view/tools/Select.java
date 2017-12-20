package view.tools;

import java.awt.Point;
import logicUML.Scheme;

public class Select implements Tool {

  private String nameTool;

  public Select() {
    nameTool = "Select";
  }

  @Override
  public String getName() {
    return nameTool;
  }

  @Override
  public void startAction(Point p, Scheme scheme) {
    scheme.select(p);
  }

  @Override
  public void continueAction(Point p, Scheme scheme) {

  }

  @Override
  public void endAction(Point p, Scheme scheme) {

  }

}
