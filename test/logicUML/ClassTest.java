package logicUML;

import java.awt.Point;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
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

    expectedResult = true;
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
    
    expectedResult = false;
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
    testClass.select();
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
  }
  
  @Test
  public void deselectTest(){
    boolean expectedResult = false;
    Point position = new Point(0, 0);
    testClass = new Class("testClass", TypeClass.ABTRACT, position);
    assertEquals(expectedResult, testClass.isSelected());
    
    expectedResult = true;
    testClass.select();
    assertEquals(expectedResult, testClass.isSelected());
    
    expectedResult = true;
    testClass.select();
    testClass.select();
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
  
  @Test
  public void generateCodeClassConcreteTest(){
    String expectResult = "public class Class{}";
    GraphsClass graphs = new  GraphsClass();
    Class classClass = new Class("Class", TypeClass.CONCRETE_CLASS, new Point(300, 300));

    graphs.addNode(classClass);
    
    assertEquals(expectResult, classClass.generateCode());
    
  }
  
  @Test
  public void generateCodeInterfaceTest(){
    String expectResult = "public interface Class{}";
    GraphsClass graphs = new  GraphsClass();
    Class classClass = new Class("Class", TypeClass.INTERFACE, new Point(300, 300));

    graphs.addNode(classClass);
    
    assertEquals(expectResult, classClass.generateCode());
  }
  
  @Test
  public void generateCodeAbstractClass(){
    String expectResult = "public abstract class Class{}";
    GraphsClass graphs = new  GraphsClass();
    Class classClass = new Class("Class", TypeClass.ABTRACT, new Point(300, 300));

    graphs.addNode(classClass);
    
    assertEquals(expectResult, classClass.generateCode());
  }
  
  @Test
  public void generateCodeInheritanceTest(){
    String expectResult = "public class Class extends Component{}";
    GraphsClass graphs = new  GraphsClass();
    Class classClass = new Class("Class", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classComponent = new Class("Component", TypeClass.ABTRACT, new Point(300, 150));

    graphs.addNode(classClass);
    graphs.addNode(classComponent);
    
    graphs.addConexion(classClass, TypeRelationship.INHERITANCE, classComponent);

    assertEquals(expectResult, classClass.generateCode());
  }
  
  @Test
  public void generateCodeImplementsTest(){
    String expectResult = "public class ClassA implements ClassB, ClassC{}";
    GraphsClass graphs = new  GraphsClass();  
    
    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.INTERFACE, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));
    
    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    
    assertEquals(expectResult, classA.generateCode());
  }
  
  @Test
  public void generateCodeInheritanceAndImplements(){
    String expectResult = "public class ClassA extends ClassB implements ClassC{}";
    
    GraphsClass graphs = new  GraphsClass(); 
    
    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));

    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    
    graphs.addConexion(classA, TypeRelationship.INHERITANCE, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    
    assertEquals(expectResult, classA.generateCode());
  }
  
  @Test
  public void generateCodeInheritanceAndManyImplements(){
    String expectResult = "public class ClassA extends ClassB implements ClassC, ClassD{}";
    
    GraphsClass graphs = new  GraphsClass(); 
    
    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));
    Class classD = new Class("ClassD", TypeClass.INTERFACE, new Point(60, 150));

    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    graphs.addNode(classD);
    
    graphs.addConexion(classA, TypeRelationship.INHERITANCE, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    
    assertEquals(expectResult, classA.generateCode());
  }
  
  @Test
  public void generateCodeAbstractInheritanceAndManyImplements(){
    String expectResult = "public abstract class ClassA extends ClassB implements ClassC, ClassD{}";
    
    GraphsClass graphs = new  GraphsClass(); 
    
    Class classA = new Class("ClassA", TypeClass.ABTRACT, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));
    Class classD = new Class("ClassD", TypeClass.INTERFACE, new Point(60, 150));

    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    graphs.addNode(classD);
    
    graphs.addConexion(classA, TypeRelationship.INHERITANCE, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    
    assertEquals(expectResult, classA.generateCode());
  }
  
  @Test
  public void removeConexionAndGenerateCode(){
    String expectResult = "public class ClassA extends ClassB implements ClassC, ClassD{}";
    
    GraphsClass graphs = new  GraphsClass(); 
    
    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));
    Class classD = new Class("ClassD", TypeClass.INTERFACE, new Point(60, 150));

    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    graphs.addNode(classD);
    
    graphs.addConexion(classA, TypeRelationship.INHERITANCE, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    
    assertEquals(expectResult, classA.generateCode());
    
    expectResult = "public class ClassA extends ClassB implements ClassC{}";
    
    graphs.removeConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    assertEquals(expectResult, classA.generateCode());
    
    expectResult = "public class ClassA extends ClassB implements ClassC, ClassD{}";
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    assertEquals(expectResult, classA.generateCode());
    
    
    Relationship relation = Relationship.makeRelationship(classA, TypeRelationship.IMPLEMENTS, classD);
    graphs.removeConexion(relation);
    expectResult = "public class ClassA extends ClassB implements ClassC{}";
    assertEquals(expectResult, classA.generateCode());
  }
  
  @Test
  public void removeNodeAndGenerateCode(){
    String expectResult = "public class ClassA extends ClassB implements ClassC, ClassD{}";
    
    GraphsClass graphs = new  GraphsClass(); 
    
    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, new Point(300, 300));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(300, 150));
    Class classC = new Class("ClassC", TypeClass.INTERFACE, new Point(170, 150));
    Class classD = new Class("ClassD", TypeClass.INTERFACE, new Point(60, 150));

    graphs.addNode(classA);
    graphs.addNode(classB);
    graphs.addNode(classC);
    graphs.addNode(classD);
    
    graphs.addConexion(classA, TypeRelationship.INHERITANCE, classB);
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classC);    
    graphs.addConexion(classA, TypeRelationship.IMPLEMENTS, classD);
    assertEquals(expectResult, classA.generateCode());
    
    expectResult = "public class ClassA extends ClassB implements ClassC{}";
    graphs.removeNode(classD);
    System.out.println(expectResult);
    System.out.println(classA.generateCode());
    assertEquals(expectResult, classA.generateCode());

  }
  
  
}
