package logicUML;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;

public class GraphsClass {

  private HashMap<Class, ArrayList<Relationship>> graph;
          
  public GraphsClass() {
    graph = new HashMap<>();
  }
  
  public boolean isEmpty(){
    return graph.isEmpty();
  }
  
  public void addNode(Class node){
    if (getNode(node) == null) {
      ArrayList<Relationship> conexions = new ArrayList<>();
      graph.put(node, conexions);
      node.addObserver(this);
    }
  }
  
  public void removeNode(Class node){
    if(!graph.isEmpty()){
      Class removeNode = getNode(node);
      if(removeNode != null){
        node.notifyRemove();
        graph.remove(node);
      }
    }
  }
  
  public Class getNode(Class node){
    if(!graph.isEmpty()){
      for(Class c : graph.keySet()){
        if(c.getName().equals(node.getName())){
          return c;
        }
      }
    }
    return null;
  }
  
  public void addConexion(Class a, TypeRelationship relation, Class b){
    if (!graph.isEmpty()) {
      Relationship conexion = Relationship.makeRelationship(a, relation, b);
      if(conexion != null){
        conexion.addObserver(this);
        Relationship conexionExistent = findConexion(conexion, a);
        if (conexionExistent == null){
          if(conexion.getType() == TypeRelationship.INHERITANCE){
            if(!a.isInherit()){
              graph.get(a).add(conexion);
              a.addRelation(conexion);
              b.addRelation(conexion);
            }
          }else{
            graph.get(a).add(conexion);
            a.addRelation(conexion);            
            b.addRelation(conexion);
          }
        }
      }
    }
  }
  
  public void removeConexion(Class a, TypeRelationship relation, Class b){
    Relationship conexion = Relationship.makeRelationship(a, relation, b);
    conexion = findConexion(conexion, a);
    if(conexion != null){
      graph.get(a).remove(conexion);
      conexion.notifyRemove();
    }
  }
  
  public void removeConexion(Relationship relation){
    Relationship relationFond = findConexion(
            relation.getClassA(), 
            relation.getType(),
            relation.getClassB());
    relationFond.notifyRemove();
  }
  
  public void changeConexion(Relationship relation, TypeRelationship type){
    relation.changeType(type);
  }
  
  public void changeNodeType(Class node, TypeClass type){
    getNode(node).changeType(type);
  }
  
  private Relationship findConexion(Relationship conexion, Class node) {
    ArrayList<Relationship> conexions = graph.get(node);
    if (!conexions.isEmpty()) {
      for (Relationship c : conexions) {
        if (c.isEquals(conexion)) {
          return c;
        }
      }
    }
    return null;
  }
  
  public Relationship findConexion(Class a, TypeRelationship relation, Class b){
    Relationship relationShip = Relationship.makeRelationship(a, relation, b);
    return findConexion(relationShip, a);
  }
  
  public ArrayList<Relationship> getConexions(Class node){
    if(!graph.isEmpty()){
      return graph.get(node);
    }
    return null;
  }
   
  public void updateChangeNode(Relationship relation){
    Class classA = relation.getClassA();
    TypeRelationship type = relation.getType();
    Class classB = relation.getClassB();
    
    ArrayList<Relationship> conexionA = getConexions(classA);
    ArrayList<Relationship> conexionB = getConexions(classA);
    
    if(!Relationship.rulesOOP(classA, type, classB)){
      conexionA.remove(relation);      
      conexionB.remove(relation);
    }
  }
    
  public void updateRemoveRelation(Relationship relation){
    Class classA = relation.getClassA();
    Class classB = relation.getClassB();
    ArrayList<Relationship> conexion = getConexions(classA);
    if(!conexion.isEmpty()){
      conexion.remove(relation);
    }
    classA.removeRelation(relation);
    classB.removeRelation(relation);
    
  }

  public GraphsClass clonGraph(){
    GraphsClass clon = new GraphsClass();
    if(!graph.isEmpty()){
      for (Class c : graph.keySet()) {
        clon.addNode(c.clonClass());
      }
      for (Class c : graph.keySet()) {
        ArrayList<Relationship> conexions = getConexions(c);
        for(Relationship relation : conexions){
          Relationship clonRelationship = Relationship.makeRelationship(
                  clon.getNode(relation.getClassA().getName()), 
                  relation.getType(), 
                  clon.getNode(relation.getClassB().getName()));
        }
      }
    }
    else {
      clon.graph = new HashMap<>();
    }
    return clon;
  }
  
  private Class getNode(String node){
    if(!graph.isEmpty()){
      for(Class c : graph.keySet()){
        if(c.getName().equals(node)){
          return c;
        }
      }
    }
    return null;
  }

  public void show(){
    graph.forEach((k,v) -> System.out.println("Key  " + k.getName()));
  }
  
  public Component select(Point p){
    if(!graph.isEmpty()){
      for(Class c: graph.keySet()){
        if(c.intersects(p)){
          return c;
        }
      }
      for(Class c : graph.keySet()){
        ArrayList<Relationship> conexions = getConexions(c);
        for(Relationship conexion : conexions){
          if(conexion.intersects(p)){
            return conexion;
          }
        }
      }
    }
    return null;
  }
  
  public void move(Class a, Point p){
    a.changePosition(p);
  }
  
  public boolean conexionsIsEmpty(){
    for (Class c : graph.keySet()) {
      if(!getConexions(c).isEmpty()){
        return true;
      }
    }
    return false;
  }
  
  public int getNumberNodes(){
    return graph.size();
  }
  
  public void draw(Graphics g) {
    if (!graph.isEmpty()) {
      for (Class c : graph.keySet()) {
        c.draw(g);
      }
    }
  }

}