package logicUML;

import logicUML.behavior.Selectable;
import logicUML.behavior.Subject;
import logicUML.geometricRepresentation.Figure;


public abstract class Component<T> implements Selectable, Subject<T>{
  
  protected boolean selected;
  protected GraphsClass observer;
  protected Figure figure;
  protected T type;
  
  @Override
  public void select() {
    selected = true;
  }

  @Override
  public void deselect() {
    selected = false;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }
  
  public abstract void changeType(T type);
  public abstract T getType();
  
}
