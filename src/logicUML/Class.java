package logicUML;

import java.awt.Point;
import logicUML.behavior.Selectable;
import logicUML.behavior.EditableClass;
import logicUML.behavior.TypeClass;

public  class Class implements Selectable, EditableClass{
  
  private String name;
  private Point position;
  private TypeClass type;
  private boolean selected;
  
  public Class(String name, TypeClass type, Point position){
    this.name = name;
    this.type = type;
    this.position = position;
    this.selected = false;
  }

  @Override
  public void select() {
    this.selected = true;
  }

  @Override
  public void deselect() {
    this.selected = false;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  @Override
  public void changeName(String newName) {
    this.name = newName;
  }

  @Override
  public void changePosition(Point newPosition) {
    this.position = newPosition;
  }

  @Override
  public void changeType(TypeClass type) {
    this.type = type;
  }

  public TypeClass getType() {
    return type;
  }
  
  public String getName(){
    return name;
  }
  
  public Point getPosition(){
    return position;
  }
  
}
