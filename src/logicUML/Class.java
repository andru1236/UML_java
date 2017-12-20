package logicUML;

import java.awt.Point;
import java.util.ArrayList;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import logicUML.geometricRepresentation.ClassFigure;

public class Class extends Component<TypeClass> {

  private String name;
  private Point position;
  private ArrayList<Relationship> relations;
  private boolean inherit;

  public Class(String name, TypeClass type, Point position) {
    this.name = name;
    this.type = type;
    this.position = position;
    this.selected = false;
    this.inherit = false;
    this.relations = new ArrayList<>();
    initFigure();
  }

  private void initFigure(){
    this.figure = new ClassFigure(position.x - 50, position.y - 25,
            position.x + 50, position.y + 25);
    this.figure.setSelected(selected) ;
    this.figure.setName(name);
    this.figure.setPositionName(position);
  }
  
  @Override
  public void addObserver(GraphsClass observer) {
    this.observer = observer;
  }

  public void addRelation(Relationship relation) {
    if(relation.getType() == TypeRelationship.INHERITANCE){
      if(!inherit){
        relations.add(relation);      
      }
      inherit = true;
    }
    else{
      relations.add(relation);
    }
  }

  public void changeName(String newName) {
    this.name = newName;
    this.figure.setName(name);
  }

  public void changePosition(Point newPosition) {
    this.position = newPosition;
    this.figure.setPositionName(position);
  }

  @Override
  public void changeType(TypeClass type) {
    this.type = type;
    this.figure.setType(this.type);
    notifyChangeType();
  }

  @Override
  public TypeClass getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public Point getPosition() {
    return position;
  }
  
  public boolean isInherit() {
    return inherit;
  }
 
  public void setInherit(boolean inherit) {
    this.inherit = inherit;
  }

  @Override
  public void notifyChangeType() {
    for (Relationship relation : relations) {
      observer.updateChangeNode(relation);
    }
  }

  @Override
  public void notifyRemove() {
    for (Relationship relation : relations) {
      Class classA = relation.getClassA();
      if(classA != this){
        observer.getNode(classA).relations.remove(relation);  
        observer.getConexions(classA).remove(relation);
      }
    }
  }

  public void remove() {
    observer.removeNode(this);
  }

  public void removeRelation(Relationship relation) {
    if (!relations.isEmpty()) {
      relations.remove(relation);
    }
  }

  public Class clonClass() {
    Point positionClon = new Point(position.x, position.y);
    Class clon = new Class(name, type, positionClon);
    clon.selected = selected;
    clon.figure = figure.clonFigure();
    return clon;
  }

  public boolean intersects(Point p) {
    return figure.intersects(p);
  }

  public String generateCode() {
    String code = "";
    switch (type){
      case INTERFACE:
        code = "public interface ";
        break;
      case ABTRACT:
        code = "public abstract class ";
        break;
      case CONCRETE_CLASS:
        code = "public class ";
        break;
    }
    
    code += name;
    
    if(inherit){
      code += " extends ";
      Class classAbstract = getRelationInHeritance().getClassB();
      code += classAbstract.getName();
    }
    
    if(classImplements()){
      code += " implements ";
      ArrayList<Relationship> relationsImplements = getRelationsImplements();
      if(relationsImplements.size() == 1){
        code += relationsImplements.get(0).getClassB().getName();
      }
      else{
        for(Relationship relation : relationsImplements){
          code += relation.getClassB().getName() + ", "; 
        }
        code = code.substring(0, code.length()-2);
      }
    }
    
    code += "{}";
    
    return code;
  }
  
  private Relationship getRelationInHeritance(){
    if(!relations.isEmpty()){
      for(Relationship relation : relations){
        if(relation.getType() == TypeRelationship.INHERITANCE){
          return relation;
        }
      }
    }
    return null;
  }
  
  private ArrayList<Relationship> getRelationsImplements(){
    ArrayList<Relationship> relationsImplements = new ArrayList<>();
    if(!relations.isEmpty()){
      for(Relationship relation : relations){
        if(relation.getType() == TypeRelationship.IMPLEMENTS){
          relationsImplements.add(relation);
        }
      }
    }
    return relationsImplements;
  }
  
  private boolean classImplements(){
    if(!relations.isEmpty()){
      for(Relationship relation : relations){
        if(relation.getType() == TypeRelationship.IMPLEMENTS){
          return true;
        }
      }
    }
    return false;
  }

}
