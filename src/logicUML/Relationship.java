package logicUML;

import java.awt.Point;
import java.util.Observer;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import logicUML.geometricRepresentation.RelationshipFigure;

public class Relationship extends Component<TypeRelationship> {

  private final Class classA;
  private final Class classB;

  private Relationship(Class classA, TypeRelationship type, Class classB) {
    this.classA = classA;
    this.type = type;
    this.classB = classB;
    this.selected = false;
    initFigure();
  }

  private void initFigure() {
    this.figure = new RelationshipFigure(
            classA.getPosition().x, classA.getPosition().y + 25,
            classB.getPosition().x, classB.getPosition().y - 36);
    this.figure.setSelected(selected);
    this.figure.setType(type);
  }

  @Override
  public void addObserver(GraphsClass observer) {
    this.observer = observer;
  }

  @Override
  public void notifyRemove() {
    if (type == TypeRelationship.INHERITANCE) {
      classA.setInherit(false);
    }
    observer.updateRemoveRelation(this);
  }

  public Class getClassA() {
    return classA;
  }

  public Class getClassB() {
    return classB;
  }

  public void updatePosition() {
    this.figure = new RelationshipFigure(
            classA.getPosition().x, classA.getPosition().y,
            classB.getPosition().x - 40, classB.getPosition().y - 40);
    this.figure.setType(type);
  }

  @Override
  public void changeType(TypeRelationship type) {
    if (rulesOOP(classA, type, classB)) {
      if (classA.isInherit() && this.type == TypeRelationship.INHERITANCE) {
        classA.setInherit(false);
      }
      this.type = type;
      this.figure.setType(type);
    }
  }

  @Override
  public TypeRelationship getType() {
    return type;
  }

  public boolean isEquals(Relationship relation) {
    return relation.getClassA() == classA
            && relation.getClassB() == classB
            && relation.getType() == type;
  }

  public static Relationship makeRelationship(Class a, TypeRelationship relation, Class b) {
    if (rulesOOP(a, relation, b)) {
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
              || relation == TypeRelationship.COMPOSITION
              || relation == TypeRelationship.ASSOCIATION)
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

  public boolean intersects(Point p) {
    return figure.intersects(p);
  }

  @Override
  public void notifyChangeType() {

  }

}
