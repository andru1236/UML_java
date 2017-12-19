package logicUML;

import java.awt.Point;
import logicUML.behavior.TypeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import logicUML.behavior.TypeRelationship;

public class RelationshipTest {

  Relationship testCase;

  Class i;
  Class a;
  Class c;

  public void initClasses() {
    i = new Class("Intercace", TypeClass.INTERFACE, new Point(50, 50));
    a = new Class("Abstract", TypeClass.ABTRACT, new Point(0, 0));
    c = new Class("ConcretClass", TypeClass.CONCRETE_CLASS, new Point(150, 50));
  }

  @Test
  public void selectedTest() {
    initClasses();
    testCase = Relationship.makeRelationship(c, TypeRelationship.INHERITANCE, a);
    boolean expectedResult = false;
    assertEquals(expectedResult, testCase.isSelected());

    testCase.select();
    assertNotEquals(expectedResult, testCase.isSelected());

    expectedResult = true;
    assertEquals(expectedResult, testCase.isSelected());

    testCase = Relationship.makeRelationship(i, TypeRelationship.AGGREGATION, c);
    Relationship expectedInstanceRelations = null;
    assertEquals(expectedInstanceRelations, testCase);
  }

  @Test
  public void deselectTest() {
    initClasses();
    testCase = Relationship.makeRelationship(c, TypeRelationship.IMPLEMENTS, i);
    boolean expectedResult = false;
    assertEquals(expectedResult, testCase.isSelected());
    testCase.select();
    testCase.select();
    assertEquals(expectedResult, testCase.isSelected());
  }

  @Test
  public void changeTypeTest() {
    initClasses();
    testCase = Relationship.makeRelationship(c, TypeRelationship.INHERITANCE, a);
    TypeRelationship expectedResult = TypeRelationship.INHERITANCE;
    assertEquals(expectedResult, testCase.getType());

    expectedResult = TypeRelationship.AGGREGATION;
    testCase.changeType(TypeRelationship.AGGREGATION);
    assertEquals(expectedResult, testCase.getType());

    expectedResult = TypeRelationship.ASSOCIATION;
    testCase.changeType(TypeRelationship.ASSOCIATION);
    assertEquals(expectedResult, testCase.getType());

    expectedResult = TypeRelationship.IMPLEMENTS;
    testCase.changeType(TypeRelationship.IMPLEMENTS);
    assertNotEquals(expectedResult, testCase.getType());

    expectedResult = TypeRelationship.INHERITANCE;
    testCase.changeType(TypeRelationship.INHERITANCE);
    assertEquals(expectedResult, testCase.getType());

    expectedResult = TypeRelationship.COMPOSITION;
    testCase.changeType(TypeRelationship.COMPOSITION);
    assertEquals(expectedResult, testCase.getType());

  }

}
