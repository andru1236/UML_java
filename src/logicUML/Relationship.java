package logicUML;

import logicUML.behavior.EditableRelationship;
import logicUML.behavior.Selectable;
import logicUML.behavior.TypeRelationship;

public class Relationship implements Selectable, EditableRelationship{
  
  private TypeRelationship type;
  private boolean selected;
  
  public Relationship(TypeRelationship type){
    this.type = type;
    this.selected = false;
  }

  @Override
  public void select() {
    selected = true;
  }

  @Override
  public void deselect() {
    selected = false;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  @Override
  public void changeType(TypeRelationship type) {
    this.type = type;
  }
  
  public TypeRelationship getType(){
    return type;
  }
  
}
