package logicUML;

import java.awt.Point;
import java.util.ArrayList;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GraphsClassTest {
  
  GraphsClass testCase;
  
  @Test
  public void addNodeTest() {
    testCase = new GraphsClass();
    Class a = new Class("nodeClass", TypeClass.ABTRACT, new Point(0, 0));
    boolean expectResult = true;
    assertEquals(testCase.isEmpty(), expectResult);
    
    testCase.addNode(a);
    expectResult = false;
    assertEquals(testCase.isEmpty(), expectResult);    
  }
  
  @Test
  public void removeNodeTest(){
    testCase = new GraphsClass();
    Class a = new Class("nodeClass", TypeClass.ABTRACT, new Point(0, 0));
    boolean expectResult = true;
    assertEquals(testCase.isEmpty(), expectResult);
    
    testCase.addNode(a);
    expectResult = false;
    assertEquals(testCase.isEmpty(), expectResult);    
    
    testCase.removeNode(a);
    expectResult = true;
    assertEquals(testCase.isEmpty(), expectResult);    
    
  }
  
  @Test
  public void addconexionTest(){
    testCase = new GraphsClass();
    Class a = new Class("nodeClassA", TypeClass.ABTRACT, new Point(0, 0));
    Class b = new Class("nodeClassB", TypeClass.CONCRETE_CLASS, new Point(50, 50));
    Class c = new Class("nodeClassC", TypeClass.CONCRETE_CLASS, new Point(50, 50));
    
    Relationship relationOne = new Relationship(TypeRelationship.INHERITANCE);
    Relationship relationTwo = new Relationship(TypeRelationship.ASSOCIATION);
    Relationship relationThree = new Relationship(TypeRelationship.ASSOCIATION);
    
    boolean expectedResult = true;
    
    testCase.addNode(a);
    testCase.addNode(b);
    
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    
    expectedResult = false;
    int conexionNumber = 1;
    testCase.addConexion(a, relationOne, b);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    
    conexionNumber = 2;
    testCase.addConexion(a, relationTwo, c);
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    
    testCase.addConexion(a, relationOne, a);
    testCase.addConexion(a, relationOne, b);
    testCase.addConexion(a, relationTwo, b);
    
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    
    conexionNumber = 3;
    testCase.addConexion(a, relationThree, b);
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    
  }
  
  @Test
  public void removeConexionTest(){
    testCase = new GraphsClass();
    Class a = new Class("nodeClassA", TypeClass.ABTRACT, new Point(0, 0));
    Class b = new Class("nodeClassB", TypeClass.CONCRETE_CLASS, new Point(50, 50));
    Relationship relation = new Relationship(TypeRelationship.INHERITANCE);
    boolean expectedResult = true;
    
    testCase.addNode(a);
    testCase.addNode(b);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    
    expectedResult = false;
    testCase.addConexion(a, relation, b);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    
    expectedResult = true;
    testCase.removeConexion(a, relation, b);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    
    expectedResult = false;
    testCase.addConexion(a, relation, a);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    
    testCase.addConexion(a, relation, b);
    int conexionNumber = 1;
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    assertEquals(conexionNumber, testCase.getConexions(a).size());
  }
  
  @Test
  public void getNodeTest(){
    testCase = new GraphsClass();
    Class a = new Class("nodeClassA", TypeClass.ABTRACT, new Point(0, 0));
    Class b = new Class("nodeClassB", TypeClass.CONCRETE_CLASS, new Point(50, 50));
    Relationship relation = new Relationship(TypeRelationship.INHERITANCE);
    
    boolean expectedResult = true;
    
    testCase.addNode(a);
    testCase.addNode(b);
    assertEquals(a, testCase.getNode(a));
    assertEquals(b, testCase.getNode(b));
  }
  
}
