package logicUML.behavior;

import java.util.Observer;
import logicUML.GraphsClass;

public interface Subject<T> {
  
  public void addObserver(GraphsClass observer);
  
  public void notifyRemove();
  
  public void notifyChangeType();
  
}
