package view.tools;

import java.awt.Point;
import logicUML.Scheme;
import logicUML.Class;
import logicUML.behavior.TypeClass;

public class CreateAbstract implements Tool{

  private String nameTool;
  private String name;
  private int number;
  
  public CreateAbstract() {
    nameTool = "AbstractClass";
    number = 1;
    name = "Abstract" + number;
  }
  
  @Override
  public String getName() {
    return nameTool;
  }

  @Override
  public void startAction(Point p, Scheme scheme) {
    Class newClass = new Class(name, TypeClass.ABTRACT, p);
    scheme.addClass(newClass);
    
    number ++;
    name = "Abstract" + number;
  }

  @Override
  public void continueAction(Point p, Scheme scheme) {
  }

  @Override
  public void endAction(Point p, Scheme scheme) {
  }
  
}
