package logicUML;

import logicUML.behavior.Selectable;
import logicUML.behavior.Subject;
import logicUML.geometricRepresentation.Figure;

public abstract class Component<T> implements Selectable, Subject {

  protected boolean selected;
  protected GraphsClass observer;
  protected Figure figure;
  protected T type;

  @Override
  public void select() {
    if(selected){
      selected = false;
    }
    else{
      selected = true;      
    }
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  public abstract void changeType(T type);

  public abstract T getType();

}
