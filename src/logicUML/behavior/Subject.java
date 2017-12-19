package logicUML.behavior;

import logicUML.GraphsClass;

public interface Subject {
  
  public void addObserver(GraphsClass observer);
  
  public void notifyRemove();
  
  public void notifyChangeType();
  
}
