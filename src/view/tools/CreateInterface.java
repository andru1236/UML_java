package view.tools;

import java.awt.Point;
import logicUML.Scheme;
import logicUML.Class;
import logicUML.behavior.TypeClass;

public class CreateInterface implements Tool {

  private String nameTool;
  private String name;
  private int number;

  public CreateInterface() {
    nameTool = "Interface";
    number = 1;
    name = "Interface" + number;
  }

  @Override
  public String getName() {
    return nameTool;
  }

  @Override
  public void startAction(Point p, Scheme scheme) {
    Class newClass = new Class(name, TypeClass.INTERFACE, p);
    scheme.addClass(newClass);

    number++;
    name = "Interface" + number;
  }

  @Override
  public void continueAction(Point p, Scheme scheme) {
  }

  @Override
  public void endAction(Point p, Scheme scheme) {
  }

}
