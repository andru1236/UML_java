package logicUML;

import java.awt.Point;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GraphsClassTest {

  GraphsClass testCase;
  
  TypeRelationship imp;
  TypeRelationship inh;
  TypeRelationship aso;
  TypeRelationship agr;
  TypeRelationship com;
  
  Class i;
  Class a;
  Class c;

  public void resetRelations() {
    imp = TypeRelationship.IMPLEMENTS;
    inh = TypeRelationship.INHERITANCE;
    aso = TypeRelationship.ASSOCIATION;
    agr = TypeRelationship.AGGREGATION;
    com = TypeRelationship.COMPOSITION;
  }

  public void resetGraph() {

    testCase = new GraphsClass();

    i = new Class("Intercace", TypeClass.INTERFACE, new Point(0, 0));
    a = new Class("Abstract", TypeClass.ABTRACT, new Point(50, 25));
    c = new Class("ConcretClass", TypeClass.CONCRETE_CLASS, new Point(250, 25));

    testCase.addNode(i);
    testCase.addNode(a);
    testCase.addNode(c);
  }

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
  public void changeTypeNode(){
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, imp, i);
    TypeClass expectedResult = TypeClass.INTERFACE;
    int conexionNumber = 1;
    assertEquals(expectedResult, i.getType());
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    
    testCase.changeNodeType(i, TypeClass.ABTRACT);
    expectedResult = TypeClass.ABTRACT;
    conexionNumber = 0;
    assertEquals(expectedResult, i.getType());
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    conexionNumber = 1;
    assertEquals(expectedResult, a.getType());
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    testCase.changeNodeType(c, TypeClass.CONCRETE_CLASS);
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    conexionNumber = 1;
    assertEquals(expectedResult, a.getType());
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    testCase.addConexion(a, imp, i);
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    testCase.changeNodeType(a, TypeClass.INTERFACE);
    conexionNumber = 0;
    assertEquals(conexionNumber, testCase.getConexions(c).size());    
    assertEquals(conexionNumber, testCase.getConexions(a).size());    
  }

  @Test
  public void removeNodeTest() {
    testCase = new GraphsClass();
    Class abs = new Class("nodeClass", TypeClass.ABTRACT, new Point(0, 0));
    boolean expectResult = true;
    assertEquals(testCase.isEmpty(), expectResult);

    testCase.addNode(abs);
    expectResult = false;
    assertEquals(testCase.isEmpty(), expectResult);

    testCase.removeNode(abs);
    expectResult = true;
    assertEquals(testCase.isEmpty(), expectResult);
    
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    testCase.addConexion(a, imp, i);
    int conexionNumber = 1;
    
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    testCase.removeNode(a);
    conexionNumber = 0;
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    
  }

  @Test
  public void addconexionTest() {

    resetGraph();
    resetRelations();

    boolean expectedResult = true;
    testCase.addConexion(i, imp, a);
    testCase.addConexion(i, inh, a);
    testCase.addConexion(i, aso, c);
    testCase.addConexion(i, agr, c);
    testCase.addConexion(i, com, c);
    testCase.addConexion(i, imp, i);

    assertEquals(expectedResult, testCase.getConexions(i).isEmpty());

    resetGraph();
    resetRelations();

    int conexNumber = 1;
    testCase.addConexion(a, imp, i);
    assertEquals(!expectedResult, testCase.getConexions(a).isEmpty());
    assertEquals(conexNumber, testCase.getConexions(a).size());
    Class a2 = new Class("Abstract2", TypeClass.ABTRACT, new Point(100, 160));
    testCase.addConexion(a, inh, a2);
    conexNumber++;
    assertEquals(conexNumber, testCase.getConexions(a).size());
    testCase.addConexion(a, aso, c);
    conexNumber++;
    assertEquals(conexNumber, testCase.getConexions(a).size());

    resetGraph();
    resetRelations();
    testCase.addConexion(a, inh, c);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());
    a2 = new Class("Abstract2", TypeClass.ABTRACT, new Point(100, 160));
    testCase.addConexion(a, imp, a2);
    assertEquals(expectedResult, testCase.getConexions(a).isEmpty());

    resetGraph();
    resetRelations();

    testCase.addConexion(c, inh, a);
    assertEquals(!expectedResult, testCase.getConexions(c).isEmpty());
    testCase.addConexion(c, com, a);
    conexNumber = 2;
    assertEquals(conexNumber, testCase.getConexions(c).size());
    conexNumber++;
    testCase.addConexion(c, imp, i);
    assertEquals(conexNumber, testCase.getConexions(c).size());

  }

  @Test
  public void removeConexionTest() {
    resetGraph();
    resetRelations();
    boolean expectResul = false;

    testCase.addConexion(c, inh, a);
    assertEquals(testCase.getConexions(c).isEmpty(), expectResul);
    testCase.removeConexion(c, inh, a);
    assertEquals(testCase.getConexions(c).isEmpty(), !expectResul);

    int countConex = 1;
    testCase.addConexion(c, inh, a);
    assertEquals(testCase.getConexions(c).isEmpty(), expectResul);
    assertEquals(countConex, testCase.getConexions(c).size());

    testCase.addConexion(c, com, a);
    countConex++;
    assertEquals(countConex, testCase.getConexions(c).size());

    testCase.addConexion(c, imp, i);
    countConex++;
    assertEquals(countConex, testCase.getConexions(c).size());

    testCase.removeConexion(c, inh, a);
    countConex--;
    assertEquals(countConex, testCase.getConexions(c).size());
    testCase.removeConexion(c, com, a);
    countConex--;
    assertEquals(countConex, testCase.getConexions(c).size());
    testCase.removeConexion(c, imp, i);
    countConex--;
    assertEquals(countConex, testCase.getConexions(c).size());
  }
  
  @Test
  public void removeConexionRelationshipTest(){
    resetGraph();
    resetRelations();
    testCase.addConexion(c, inh, a);
    testCase.addConexion(a, imp, i);
    
    int conexionNumber = 1;
    
    Relationship conexInh = Relationship.makeRelationship(c, inh, a);
    Relationship conexImp = Relationship.makeRelationship(a, imp, i);
    assertEquals(conexionNumber, testCase.getConexions(c).size());
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    
    testCase.removeConexion(conexInh);
    assertEquals(conexionNumber, testCase.getConexions(a).size());
    conexionNumber = 0;
    assertEquals(conexionNumber, testCase.getConexions(c).size());
  }
  
  @Test
  public void changeConexionTest(){
    resetGraph();
    resetRelations();
    
    int conexionNumber = 1;
    testCase.addConexion(c, inh, a);
    assertEquals(testCase.getConexions(c).size(), conexionNumber);
    
    Relationship relation = testCase.findConexion(c, inh, a);
    
    TypeRelationship expectResult = TypeRelationship.INHERITANCE;    
    testCase.changeConexion(relation, imp);
    assertEquals(expectResult, relation.getType());
    
    expectResult = TypeRelationship.COMPOSITION;
    testCase.changeConexion(relation, com);
    assertEquals(expectResult, relation.getType());
  }

  @Test
  public void getNodeTest() {
    testCase = new GraphsClass();
    Class a = new Class("nodeClassA", TypeClass.ABTRACT, new Point(0, 0));
    Class b = new Class("nodeClassB", TypeClass.CONCRETE_CLASS, new Point(50, 50));

    boolean expectedResult = true;

    testCase.addNode(a);
    testCase.addNode(b);
    assertEquals(a, testCase.getNode(a));
    assertEquals(b, testCase.getNode(b));
  }
  
  @Test 
  public void clonTest(){
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    testCase.addConexion(a, imp, i);
    
    GraphsClass clon = testCase.clonGraph();
    testCase.show();
    System.out.println("");
    clon.show();
    System.out.println("");
    
    testCase.changeNodeType(a, TypeClass.CONCRETE_CLASS);
    a.changeName("CONCRETA");
    
    testCase.show();
    System.out.println("");
    clon.show();
  }
  
  @Test
  public void selectedTest(){
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    testCase.addConexion(a, imp, i);
    
    Component selectedClass = testCase.select(new Point(225, 25));
    Class expectedResult = c;
    
    assertEquals(expectedResult, selectedClass);
    
    Component selecteRelation = testCase.select(new Point(180, 25));
    Relationship expentedRelationship = testCase.findConexion(c, inh, a);
    
    assertEquals(expentedRelationship, selecteRelation);
  }
  
  @Test
  public void moveNodeTest(){
    resetGraph();
    resetRelations();
    
    testCase.addConexion(c, inh, a);
    testCase.addConexion(a, imp, i);
    
    Point expectedPoint = new Point(100, 25);
    testCase.move(c, expectedPoint);
    
    assertEquals(testCase.getNode(c).getPosition(), expectedPoint);
  }

}