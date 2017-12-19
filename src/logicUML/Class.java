package logicUML;

import java.awt.Point;
import java.util.ArrayList;
import logicUML.behavior.TypeClass;
import logicUML.geometricRepresentation.ClassFigure;

public  class Class extends Component<TypeClass>{
  
  private String name;
  private Point position;
  private ArrayList<Relationship> relations;
  
  public Class(String name, TypeClass type, Point position){
    this.name = name;
    this.type = type;
    this.position = position;
    this.selected = false;
    this.relations = new ArrayList<>();
    this.figure = new ClassFigure(position.x - 50, position.y - 25, 
                                  position.x + 50, position.y + 25);
  }
  
  @Override
  public void addObserver(GraphsClass observer){
    this.observer = observer;
  }
  
  public void addRelation(Relationship relation){
    relations.add(relation);
  }

  public void changeName(String newName) {
    this.name = newName;
  }

  public void changePosition(Point newPosition) {
    this.position = newPosition;
  }

  @Override
  public void changeType(TypeClass type) {
    this.type = type;
    notifyChangeType();
  }

  @Override
  public TypeClass getType() {
    return type;
  }
  
  public String getName(){
    return name;
  }
  
  public Point getPosition(){
    return position;
  }
  
  @Override
  public void notifyChangeType(){
    for(Relationship relation : relations){
      observer.updateChangeNode(relation);
    }
  }
  
  @Override
  public void notifyRemove(){
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
  
  public boolean intersects(Point p){
    return figure.intersects(p);
  }
  
}
