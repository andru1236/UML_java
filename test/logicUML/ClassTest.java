package logicUML;

import java.awt.Point;
import logicUML.behavior.TypeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ClassTest {

  Class testClass;

  @Test
  public void changeTypeTest(){
    TypeClass expectedResult = TypeClass.ABTRACT;
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    assertEquals(expectedResult, testClass.getType());
    
    testClass.changeType(TypeClass.INTERFACE);
    assertNotEquals(expectedResult, testClass.getType());
    expectedResult = TypeClass.INTERFACE;
    assertEquals(expectedResult, testClass.getType());
    
    testClass.changeType(TypeClass.CONCRETE_CLASS);
    expectedResult = TypeClass.CONCRETE_CLASS;
    assertEquals(expectedResult, expectedResult);
    
    testClass.changeType(TypeClass.ABTRACT);
    expectedResult = TypeClass.ABTRACT;
    assertEquals(expectedResult, expectedResult);

  }
  
  @Test
  public void changeNameTest() {
    String expectedResult = "testClass";
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    
    assertEquals(testClass.getName() , expectedResult);
    
    testClass.changeName("newName");
    assertNotEquals(expectedResult, testClass.getName());
    
    expectedResult = "newName";
    assertEquals(expectedResult, testClass.getName());
  }
  
  @Test
  public void selectedTest(){
    boolean expectedResult = false;
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    
    assertEquals(expectedResult, testClass.isSelected());
    testClass.select();
    assertNotEquals(expectedResult, testClass.isSelected());
    
    expectedResult = true;
    assertEquals(expectedResult, testClass.isSelected());
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
    testClass.deselect();
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
  }
  
  @Test
  public void deselectTest(){
    boolean expectedResult = false;
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    assertEquals(expectedResult, testClass.isSelected());
    testClass.deselect();
    assertEquals(expectedResult, testClass.isSelected());
    
    testClass.select();
    testClass.deselect();
    assertEquals(expectedResult, testClass.isSelected());
  }
  
  @Test
  public void changePositionTest(){
    Point expectedResult = new Point(5, 5);
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    assertNotEquals(expectedResult, testClass.getPosition());
    testClass.changePosition(expectedResult);
    assertEquals(expectedResult, testClass.getPosition());
  }
  
  
}
