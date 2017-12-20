package view.tools;

import view.tools.Tool;
import java.awt.Point;
import logicUML.Scheme;
import logicUML.Class;

import logicUML.behavior.TypeClass;
public class CreateClass implements Tool{
  
  private String nameTool;
  private String name;
  private int number;

  public CreateClass() {
    nameTool = "Class";
    number = 1;
    name = "Class" + number;
  }
  @Override
  public String getName() {
    return nameTool;
  }

  @Override
  public void startAction(Point p, Scheme scheme) {
    Class newClass = new Class(name, TypeClass.CONCRETE_CLASS, p);
    scheme.addClass(newClass);
    
    number ++;
    name = "Class" + number;
  }

  @Override
  public void continueAction(Point p) {
  }

  @Override
  public void endAction(Point p) {
  }
  
}
