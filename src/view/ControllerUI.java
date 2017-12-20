package view;

import view.tools.Tool;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import logicUML.Scheme;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;

public class ControllerUI implements MouseListener, MouseMotionListener{
  
  private Tool tool;
  private Scheme scheme;
  private DesingUML view;
  
  public ControllerUI(){
    scheme = new Scheme();
    view = new DesingUML("UML Diagram", this);
    
    view.addScheme(scheme);
    view.addController(this);
  }
  
  public void undo(){
    scheme.undo();
    view.draw();
  }

  public void redo(){
    scheme.redo();
    view.draw();
  }
  
  public void inheritance(){
    scheme.addRelation(TypeRelationship.INHERITANCE);
    view.draw();
  }
  
  public void association(){
    scheme.addRelation(TypeRelationship.ASSOCIATION);
    view.draw();
  }
  
  public void aggregation(){
    scheme.addRelation(TypeRelationship.AGGREGATION);    
    view.draw();
  }
  
  public void composition(){
    scheme.addRelation(TypeRelationship.COMPOSITION);    
    view.draw();
  }
  
  public void implementsRelation(){
    scheme.addRelation(TypeRelationship.IMPLEMENTS);    
    view.draw();
  }
  
  public void changeInterface(){
    scheme.changeClass(TypeClass.INTERFACE);
    view.draw();
  }
  
  public void changeAbstract(){
    scheme.changeClass(TypeClass.ABTRACT);    
    view.draw();    
  }
  
  public void changeConcrete(){
    scheme.changeClass(TypeClass.CONCRETE_CLASS);    
    view.draw();    
  }
  
  public void changeInheritance(){
    scheme.changeRelation(TypeRelationship.INHERITANCE);
    view.draw();
  }
  
  public void changeAssociation(){
    scheme.changeRelation(TypeRelationship.ASSOCIATION);    
    view.draw();
  }
  
  public void changeAggregation(){
    scheme.changeRelation(TypeRelationship.AGGREGATION);    
    view.draw();
  }
  
  public void changeComposition(){
    scheme.changeRelation(TypeRelationship.COMPOSITION);    
    view.draw();
  }
  
  public void changeImplementsRelation(){
    scheme.changeRelation(TypeRelationship.IMPLEMENTS);    
    view.draw();
  }
  
  public void changeNameClass(){
    String nameClass = scheme.firstClassSelected();
    String text = JOptionPane.showInputDialog("Enter Text", nameClass);
    if(text != ""){
      scheme.changeName(text);
    }
    view.draw();
  }
  
  public void clearAll(){
    scheme.clearAll();
    view.draw();
  }
  
  public void newScheme(){
    scheme = new Scheme();
    view.addScheme(scheme);
    view.addController(this);
    view.draw();
  }
  
  public void remove(){
    scheme.remove();
    view.draw();
  }
  
  public void exportToFiles(){
    scheme.exportFilesJava();
  }
  
  public void openFile(String filename) {
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      scheme = (Scheme) in.readObject();
      in.close();
      view.addScheme(scheme);
      view.draw();
    } catch (IOException e1) {
      System.out.println("Unable to open file: " + filename);
    } catch (ClassNotFoundException e2) {
      System.out.println(e2);
    }
  }

  public void saveFile(String filename) {
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(scheme);
      out.close();
      System.out.println("Save drawing to " + filename);
    } catch (IOException e) {
      System.out.println("Unable to write file: " + filename);
    }
  }
  
  @Override
  public void mousePressed(MouseEvent me) {
    Point p = me.getPoint();
    tool.startAction(p, scheme);
  }
  
  @Override
  public void mouseReleased(MouseEvent me) {
    view.draw();
  }
  
  public void setTool(Tool tool){
    this.tool = tool;
  }

  public Tool getTool(){
    return tool;
  }
  
  @Override
  public void mouseDragged(MouseEvent me) {
    
  }

  @Override
  public void mouseEntered(MouseEvent me) {
  }

  @Override
  public void mouseExited(MouseEvent me) {
  }

  @Override
  public void mouseMoved(MouseEvent me) {
  }
  
  @Override
  public void mouseClicked(MouseEvent me) {
  }
  
}
