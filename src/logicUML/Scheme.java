package logicUML;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;

public class Scheme implements Serializable{
  
  private GraphsClass graph;
  private ArrayList<GraphsClass> undoList;
  private ArrayList<GraphsClass> redoList;
  private ArrayList<Component> listSelected;
  public static final int CLASS_A = 0;
  public static final int CLASS_B = 1;

  public Scheme() {
    graph = new GraphsClass();
    undoList = new ArrayList<>();
    undoList.add(graph.clonGraph());
    redoList = new ArrayList<>();
    listSelected = new ArrayList<>();
  }
  
  public void addClass(Class newClass){
    undoList.add(graph.clonGraph());
    graph.addNode(newClass);
  }
  
  public boolean existClasses(){
    return !graph.isEmpty();
  }
  
  public void select(Point p){
    Component componentSelect = graph.select(p);
    if(componentSelect != null){
      undoList.add(graph.clonGraph());
      if(componentSelect.isSelected()){
        listSelected.remove(componentSelect);
      } else{
        listSelected.add(componentSelect);
      }
      componentSelect.select();
    }
  }
  
  public ArrayList<Component> getListSelected(){
    return listSelected;
  }
  
  public boolean existRelations(){
    return graph.conexionsIsEmpty();
  }
  
  public boolean existRelation(Class a, TypeRelationship type, Class b){
    Relationship relationFond = graph.findConexion(a, type, b);
    if(relationFond != null) {
      return true;
    }
    return false;
  }
  
  public void addRelation(TypeRelationship type){
    if(listSelected.size() == 2) {
      Component classA = listSelected.get(CLASS_A);
      Component classB = listSelected.get(CLASS_B);      
      if(classA instanceof Class && classB instanceof Class){
        undoList.add(graph.clonGraph());
        graph.addConexion((Class) classA, type, (Class) classB);
      }
    }
    resetListSelected();
  }
  
  public void move(Point newPosition){
    if(!listSelected.isEmpty()){
      undoList.add(graph.clonGraph());
      for(Component selected : listSelected){
        if(selected instanceof Class){
          graph.move((Class) selected, newPosition);
        }
      }
    }
    resetListSelected();
  }
  
  public void resetListSelected(){
    for(Component selected :listSelected){
      selected.select();
    }
    listSelected = new ArrayList<>();
  }
  
  public int getNumberClasses(){
    return graph.getNumberNodes();
  }
  
  public void undo(){
    if(undoList.size() > 0){
      redoList.add(graph.clonGraph());      
      graph = undoList.remove(undoList.size() - 1);     
    }
  }
  
  public void redo(){
    if(redoList.size() > 0){
      undoList.add(graph.clonGraph());
      graph = redoList.remove(redoList.size() - 1);
    }
  }
  
  public void changeRelation(TypeRelationship type){
    if(!listSelected.isEmpty()){
      undoList.add(graph.clonGraph());
      for(Component selected: listSelected){
        if(selected instanceof Relationship){
          graph.changeConexion((Relationship) selected, type);
        }
      }
    }
    resetListSelected();
  }
  
  public void changeClass(TypeClass type){
    if(!listSelected.isEmpty()){
      undoList.add(graph.clonGraph());
      for(Component selected: listSelected){
        if(selected instanceof Class){
          graph.changeNodeType((Class) selected, type);
        }
      }
    }
    resetListSelected();
  }
  
  public void changeName(String name){
    if(!listSelected.isEmpty()){
      Class classFond = graph.getNode(name);
      if(classFond == null){
        undoList.add(graph.clonGraph());
        for (Component selected : listSelected) {
          if (selected instanceof Class) {
            graph.getNode((Class) selected).changeName(name);
            break;
          }
        }
      }
    }
    resetListSelected();
  }
  
  public void remove(){
    if(!listSelected.isEmpty()){
      undoList.add(graph.clonGraph());
      for(Component selected: listSelected){
        if(selected instanceof Relationship){
          graph.removeConexion((Relationship) selected);
        }
      }
      for(Component selected: listSelected){
        if(selected instanceof Class){
          graph.removeNode((Class) selected);
        }
      }
    }
    resetListSelected();
  }
  
  public void clearAll(){
    undoList.add(graph.clonGraph());
    graph = new GraphsClass();
  }
  
  public Component getComponent(Point p){
    return graph.select(p);
  }
  
  public String firstClassSelected(){
    if(!listSelected.isEmpty()){
      for (Component component : listSelected) {
        if(component instanceof Class){
          return ((Class) component).getName();
        }
      }
    }
    return "";
  }
  
  public void draw(Graphics g){
    graph.draw(g);
  }
  
  public void show(){
    graph.show();
  }
  
  public void exportFilesJava(){
    graph.exportFiles();
  }
  
}
