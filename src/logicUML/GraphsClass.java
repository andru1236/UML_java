package logicUML;

import java.util.ArrayList;
import java.util.HashMap;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;

public class GraphsClass {

  private final HashMap<Class, ArrayList<Conexion>> graph;
          
  public GraphsClass() {
    graph = new HashMap<>();
  }
  
  public boolean isEmpty(){
    return graph.isEmpty();
  }
  
  public void addNode(Class node){
    if (getNode(node) == null) {
      ArrayList<Conexion> conexions = new ArrayList<>();
      graph.put(node, conexions);
    }
  }
  
  public void removeNode(Class node){
    if(!graph.isEmpty()){
      Class removeNode = getNode(node);
      if(removeNode != null){
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
  
  public void addConexion(Class a, Relationship relation, Class b){
    if(!graph.isEmpty()){
      Conexion newConexion = new Conexion(relation, b);
      Conexion conexionExistent = findConexion(newConexion, a);
      if(conexionExistent == null){
        graph.get(a).add(newConexion);
      }
    }
  }
  
  public void removeConexion(Class a, Relationship relation, Class b){
    Conexion conexion = new Conexion(relation, b);
    conexion = findConexion(conexion, a);
    if(conexion != null){
      graph.get(a).remove(conexion);
    }
  }
  
  private Conexion findConexion(Conexion conexion, Class node) {
    ArrayList<Conexion> conexions = graph.get(node);
    if (!conexions.isEmpty()) {
      for (Conexion c : conexions) {
        if (c.isEquals(conexion)) {
          return c;
        }
      }
    }
    return null;
  }
  
  public ArrayList<Conexion> getConexions(Class node){
    if(!graph.isEmpty()){
      return graph.get(node);
    }
    return null;
  }
  
  private boolean rulesOOP(Class a, Relationship relation , Class b){
    if (a.getType() == TypeClass.INTERFACE) {
      return false;
    }
    if (a.getType() == TypeClass.ABTRACT) {
      if (relation.getType() == TypeRelationship.IMPLEMENTS
              && b.getType() == TypeClass.INTERFACE) {
        return true;
      }
      if ((relation.getType() == TypeRelationship.AGGREGATION
              || relation.getType() == TypeRelationship.ASSOCIATION
              || relation.getType() == TypeRelationship.COMPOSITION
              || relation.getType() == TypeRelationship.INHERITANCE)
              && b.getType() == TypeClass.ABTRACT) {
        return true;
      }

    }
    if (a.getType() == TypeClass.CONCRETE_CLASS) {
      if (relation.getType() == TypeRelationship.IMPLEMENTS
              && b.getType() == TypeClass.INTERFACE) {
        return true;
      }
      if ((relation.getType() == TypeRelationship.AGGREGATION
              || relation.getType() == TypeRelationship.ASSOCIATION
              || relation.getType() == TypeRelationship.COMPOSITION
              || relation.getType() == TypeRelationship.INHERITANCE)
              && 
              (b.getType() == TypeClass.ABTRACT 
              || b.getType() == TypeClass.CONCRETE_CLASS)) {
        return true;
      }
    }
    return false;
  }

  
}
