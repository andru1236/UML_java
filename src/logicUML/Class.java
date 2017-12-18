package logicUML;

import java.awt.Point;
import java.util.ArrayList;
import logicUML.behavior.Selectable;
import logicUML.behavior.EditableClass;
import logicUML.behavior.TypeClass;
import logicUML.geometricRepresentation.ClassFigure;
import logicUML.geometricRepresentation.Figure;

public  class Class implements Selectable, EditableClass{
  
  private String name;
  private Point position;
  private TypeClass type;
  private boolean selected;
  private GraphsClass observer;
  private ArrayList<Relationship> relations;
  private Figure classFigure;
  
  public Class(String name, TypeClass type, Point position){
    this.name = name;
    this.type = type;
    this.position = position;
    this.selected = false;
    this.relations = new ArrayList<>();
    this.classFigure = new ClassFigure(position.x - 50, position.y - 25, 
                                  position.x + 50, position.y - 25);
  }
  
  public void addObserver(GraphsClass observer){
    this.observer = observer;
  }
  
  public void addRelation(Relationship relation){
    relations.add(relation);
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
    notifyChangeType();
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
  
  public void notifyChangeType(){
    for(Relationship relation : relations){
      observer.updateChangeNode(relation);
    }
  }
  
  public void notifyRemoveNode(){
    for(Relationship relation : relations){
      observer.updateRemoveRelation(relation);
    }
  }
  
  public void remove(){
    observer.removeNode(this);
  }
  
  public void removeRelation(Relationship relation){
    if(!relations.isEmpty()){
      relations.remove(relation);
    }
  }
  
  public Class clonClass(){
    Point positionClon = new Point(position.x, position.y);
    Class clon = new Class(name, type, positionClon);
    clon.selected = selected;
    return clon;
  }
  
}
