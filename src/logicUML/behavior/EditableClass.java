package logicUML.behavior;

import java.awt.Point;

public interface EditableClass {

  public void changeName(String newName);

  public void changePosition(Point newPosition);
  
  public void changeType(TypeClass type);

}
