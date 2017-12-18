package logicUML;

import logicUML.behavior.EditableRelationship;
import logicUML.behavior.Selectable;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import logicUML.geometricRepresentation.Figure;
import logicUML.geometricRepresentation.RelationshipFigure;

public class Relationship implements Selectable, EditableRelationship {

  private TypeRelationship type;
  private boolean selected;

  private final Class classA;
  private final Class classB;
  
  private GraphsClass observer;
  private Figure RelationFigure;

  private Relationship(Class classA, TypeRelationship type, Class classB) {
    this.classA = classA;
    this.type = type;
    this.classB = classB;
    this.selected = false;
    this.RelationFigure = new RelationshipFigure(
            classA.getPosition().x, classA.getPosition().y, 
            classB.getPosition().x, classB.getPosition().y);
  }
  
  public void addObserver(GraphsClass observer){
    this.observer = observer;
  }
  
  public void remove(){
    observer.updateRemoveRelation(this);
  }

  public Class getClassA() {
    return classA;
  }

  public Class getClassB() {
    return classB;
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
    if(rulesOOP(classA, type, classB)){
      this.type = type;      
    }
  }

  public TypeRelationship getType() {
    return type;
  }
  
  public boolean isEquals(Relationship relation){
    return  relation.getClassA() == classA &&
            relation.getClassB() == classB &&
            relation.getType()   == type;
  }
  
  public static Relationship makeRelationship(Class a, TypeRelationship relation, Class b){
    if(rulesOOP(a, relation, b)){
      return new Relationship(a, relation, b);
    }
    return null;
  }
  
  public static boolean rulesOOP(Class a, TypeRelationship relation, Class b) {
    if (a.getType() == TypeClass.INTERFACE) {
      return false;
    }
    if (a.getType() == TypeClass.ABTRACT) {
      if (relation == TypeRelationship.IMPLEMENTS
              && b.getType() == TypeClass.INTERFACE) {
        return true;
      }
      if ((relation == TypeRelationship.AGGREGATION
              || relation == TypeRelationship.ASSOCIATION
              || relation == TypeRelationship.COMPOSITION
              || relation == TypeRelationship.INHERITANCE)
              && b.getType() == TypeClass.ABTRACT) {
        return true;
      }
      if ((relation == TypeRelationship.AGGREGATION
              || relation == TypeRelationship.ASSOCIATION
              || relation == TypeRelationship.COMPOSITION)
              && b.getType() == TypeClass.CONCRETE_CLASS) {
        return true;
      }

    }
    if (a.getType() == TypeClass.CONCRETE_CLASS) {
      if (relation == TypeRelationship.IMPLEMENTS
              && b.getType() == TypeClass.INTERFACE) {
        return true;
      }
      if ((relation == TypeRelationship.AGGREGATION
              || relation == TypeRelationship.ASSOCIATION
              || relation == TypeRelationship.COMPOSITION
              || relation == TypeRelationship.INHERITANCE)
              && (b.getType() == TypeClass.ABTRACT
              || b.getType() == TypeClass.CONCRETE_CLASS)) {
        return true;
      }
    }
    return false;
  }

}
