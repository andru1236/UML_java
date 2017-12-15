
package logicUML;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import logicUML.behavior.TypeRelationship;

public class RelationshipTest {
  
  Relationship testCase;
  
  @Test
  public void selectedTest() {
    testCase = new Relationship(TypeRelationship.ASSOCIATION);
    boolean expectedResult = false;
    assertEquals(expectedResult, testCase.isSelected());
    testCase.select();
    assertNotEquals(expectedResult, testCase.isSelected());
    expectedResult = true;
    assertEquals(expectedResult, testCase.isSelected());
  }
  
  @Test
  public void deselectTest(){
    testCase = new Relationship(TypeRelationship.COMPOSITION);
    boolean expectedResult = false;
    assertEquals(expectedResult, testCase.isSelected());
    testCase.select();
    testCase.deselect();
    assertEquals(expectedResult, testCase.isSelected());
  }
  
  @Test
  public void changeTypeTest(){
    testCase = new  Relationship(TypeRelationship.COMPOSITION);
    TypeRelationship expectedResult =  TypeRelationship.COMPOSITION;
    assertEquals(expectedResult, testCase.getType());
    
    expectedResult = TypeRelationship.AGGREGATION;
    testCase.changeType(TypeRelationship.AGGREGATION);
    assertEquals(expectedResult, testCase.getType());
    
    expectedResult = TypeRelationship.ASSOCIATION;
    testCase.changeType(TypeRelationship.ASSOCIATION);
    assertEquals(expectedResult, testCase.getType());
    
    expectedResult = TypeRelationship.IMPLEMENTS;
    testCase.changeType(TypeRelationship.IMPLEMENTS);
    assertEquals(expectedResult, testCase.getType());
    
    expectedResult = TypeRelationship.INHERITANCE;
    testCase.changeType(TypeRelationship.INHERITANCE);
    assertEquals(expectedResult, testCase.getType());
    
    expectedResult = TypeRelationship.COMPOSITION;
    testCase.changeType(TypeRelationship.COMPOSITION);
    assertEquals(expectedResult, testCase.getType());

  }
  
}
