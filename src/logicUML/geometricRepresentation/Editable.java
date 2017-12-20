package logicUML.geometricRepresentation;

import java.awt.Point;

public interface Editable<T> {
  
  public void setName(String name);
  
  public void setPositionName(Point p);
  
  public void setSelected(boolean selected);
  
  public void setType(T type);
}
