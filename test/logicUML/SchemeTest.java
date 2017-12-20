package logicUML;

import java.awt.Point;
import logicUML.behavior.TypeClass;
import logicUML.behavior.TypeRelationship;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SchemeTest {
  
  private Scheme scheme;
  
  public void initScheme(){
    scheme = new Scheme();
  }
  
  @Test
  public void addClassTest() {
    initScheme();
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existClasses());
    
    expectedResult = true;
    Class classA = new Class("ClassA", TypeClass.ABTRACT, new Point(50, 50));
    scheme.addClass(classA);
    assertEquals(expectedResult, scheme.existClasses());
  }
  
  @Test
  public void selectedComponentClassTest(){
    initScheme();
    boolean expectedResult = true;
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    
    expectedResult = false;
    int componentSelected = 2;
    Class classA = new Class("ClassA", TypeClass.ABTRACT, new Point(50, 25));
    Class classB = new Class("ClassB", TypeClass.ABTRACT, new Point(50, 80));
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(new Point(50, 25));
    scheme.select(new Point(50, 80));
    
    boolean selected = true;
    assertEquals(selected, classA.isSelected());
    assertEquals(selected, classB.isSelected());
    
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    assertEquals(componentSelected, scheme.getListSelected().size());
  }
  
  
  @Test
  public void deselecteComponentClassTest(){
    initScheme();
    boolean expectedResult = true;
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    
    expectedResult = false;
    int componentSelected = 2;
    
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 80);
    
    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean selected = true;
    
    assertEquals(selected, classA.isSelected());
    assertEquals(selected, classB.isSelected());
    
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    assertEquals(componentSelected, scheme.getListSelected().size());
    
    selected = false;
    expectedResult = true;
    componentSelected = 0;
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    assertEquals(selected, classA.isSelected());
    assertEquals(selected, classB.isSelected());
    
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    assertEquals(componentSelected, scheme.getListSelected().size());
    
  }
  
  @Test
  public void addRelationshipTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 80);

    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);

    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    TypeRelationship typeRelation = TypeRelationship.INHERITANCE;
    scheme.addRelation(typeRelation);
    
    assertEquals(expectedResult, scheme.existRelations());
    assertEquals(expectedResult, scheme.existRelation(classA, typeRelation, classB));
  }
  
  
  
  @Test
  public void moveComponentTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 80);
    
    Point newPointClassA = new Point(80,30);
    Point newPointClassB = new Point(100,30);

    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.move(newPointClassA);
    
    assertEquals(newPointClassA, classA.getPosition());
    
    scheme.select(pointClassB);
    scheme.move(newPointClassB);
    
    assertEquals(newPointClassB, classB.getPosition());
    assertEquals(newPointClassA, classA.getPosition());
  }
  
  @Test
  public void undoTest(){
    initScheme();
    
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 80);

    int numberClasses = 1;
    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    scheme.addClass(classA);

    assertEquals(numberClasses, scheme.getNumberClasses());
    
    numberClasses ++;
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    scheme.addClass(classB);
    
    assertEquals(numberClasses, scheme.getNumberClasses());
    
    numberClasses = 1;
    scheme.undo();
    assertEquals(numberClasses, scheme.getNumberClasses());
    
  }
  
  @Test
  public void redoTest(){
    initScheme();
    
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 80);

    int numberClasses = 1;
    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    scheme.addClass(classA);

    assertEquals(numberClasses, scheme.getNumberClasses());
    
    numberClasses ++;
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    scheme.addClass(classB);
    
    assertEquals(numberClasses, scheme.getNumberClasses());
    
    numberClasses = 1;
    scheme.undo();
    assertEquals(numberClasses, scheme.getNumberClasses());
    
    numberClasses ++;
    scheme.redo();
    assertEquals(numberClasses, scheme.getNumberClasses());
    
  }
  
  @Test
  public void changeRelationTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);

    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    scheme.addRelation(TypeRelationship.INHERITANCE);
    
    assertEquals(expectedResult, scheme.existRelations());
    
    Point pointRelation = new Point(50, 60);
    scheme.select(pointRelation);
    
    scheme.changeRelation(TypeRelationship.COMPOSITION);
    Component relationChange = scheme.getComponent(pointRelation);

    scheme.select(pointRelation);
    
    assertEquals(expectedResult, scheme.existRelations());
    assertEquals(relationChange.getType(), scheme.getListSelected().get(0).getType());
  }
  
  @Test
  public void changeClassTypeTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);

    scheme.addClass(classA);
    scheme.addClass(classB);
    
    TypeClass expectednewType = TypeClass.ABTRACT;
    
    scheme.select(pointClassA);
    scheme.changeClass(TypeClass.ABTRACT);
    
    assertEquals(expectednewType, classA.getType());
    
    expectednewType = TypeClass.CONCRETE_CLASS;
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    scheme.changeClass(TypeClass.CONCRETE_CLASS);
    
    assertEquals(expectednewType, classA.getType());
    assertEquals(expectednewType, classB.getType());    
    
  }
  
  @Test
  public void removeClassTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);

    scheme.addClass(classA);
    scheme.addClass(classB);
    
    expectedResult = false;
    scheme.select(pointClassA);
    
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());

    expectedResult = true;
    scheme.remove();
    scheme.select(pointClassA);

    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
  }
  
  @Test
  public void removeRelationshipTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);

    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    scheme.addRelation(TypeRelationship.INHERITANCE);
    
    assertEquals(expectedResult, scheme.existRelations());
    
    expectedResult = false;
    Point pointRelation = new Point(50, 52);
    scheme.select(pointRelation);
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
    
    expectedResult = true;
    scheme.remove();
    scheme.select(pointRelation);
    assertEquals(expectedResult, scheme.getListSelected().isEmpty());
  }
  
  @Test
  public void changeTwoClassesTypeWithRelationSelected(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);

    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    TypeRelationship typeRelation = TypeRelationship.INHERITANCE;
    Point pointRelation = new Point(50, 60);
    scheme.addRelation(typeRelation);
    
    TypeClass expectednewType = TypeClass.ABTRACT;
    
    scheme.select(pointClassB);
    scheme.select(pointClassA);
    scheme.select(pointRelation);    
    
    scheme.changeClass(expectednewType);

    scheme.select(pointRelation);
    
    assertEquals(expectednewType, classA.getType());
    assertEquals(expectednewType, classB.getType());
    assertEquals(typeRelation, scheme.getListSelected().get(0).getType());    
  }
  
  @Test
  public void changeRelationWithTwoClassesSelectedTest(){
    initScheme();
    
    boolean expectedResult = false;
    assertEquals(expectedResult, scheme.existRelations());

    expectedResult = true;
    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);

    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    TypeRelationship typeRelation = TypeRelationship.INHERITANCE;
    Point pointRelation = new Point(50, 60);
    scheme.addRelation(typeRelation);
    
    TypeRelationship expectedNewType = TypeRelationship.COMPOSITION;
    TypeClass expecteClassType = TypeClass.CONCRETE_CLASS;
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    scheme.select(pointRelation);    
    
    scheme.changeRelation(expectedNewType);
    
    scheme.select(pointRelation);
    
    assertEquals(expecteClassType, classA.getType());
    assertEquals(expecteClassType, classB.getType());
    assertEquals(expectedNewType, scheme.getListSelected().get(0).getType());
  }
  
  @Test
  public void imposibleRelationInterfaceWithConcreteClassCompositionTest(){
    initScheme();

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.INTERFACE, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean existRelations = false;
    scheme.addRelation(TypeRelationship.COMPOSITION);
    Point pointRelation = new Point(50, 60);
    
    scheme.select(pointRelation);
    assertEquals(existRelations, scheme.existRelations());
    
  }
  
  @Test
  public void imposibleRelationInterfaceWithConcreteClassInheritanceTest(){
    initScheme();

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.INTERFACE, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean existRelations = false;
    scheme.addRelation(TypeRelationship.INHERITANCE);
    Point pointRelation = new Point(50, 60);
    
    scheme.select(pointRelation);
    assertEquals(existRelations, scheme.existRelations());
  }
  
  @Test
  public void imposibleRelationInterfaceWithConcreteClassImpementsTest(){
    initScheme();

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.INTERFACE, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean existRelations = false;
    scheme.addRelation(TypeRelationship.IMPLEMENTS);
    Point pointRelation = new Point(50, 60);
    
    scheme.select(pointRelation);
    assertEquals(existRelations, scheme.existRelations());
  }
  
  @Test
  public void imposibleRelationConcreteWithConcreteClassImpementsTest(){
    initScheme();

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.CONCRETE_CLASS, pointClassA);
    Class classB = new Class("ClassB", TypeClass.CONCRETE_CLASS, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean existRelations = false;
    scheme.addRelation(TypeRelationship.IMPLEMENTS);
    Point pointRelation = new Point(50, 60);
    
    scheme.select(pointRelation);
    assertEquals(existRelations, scheme.existRelations());
  }
  
  @Test
  public void imposibleRelationAbstractWithAbsctractClassImpementsTest(){
    initScheme();

    Point pointClassA = new Point(50, 25);
    Point pointClassB = new Point(50, 100);

    Class classA = new Class("ClassA", TypeClass.ABTRACT, pointClassA);
    Class classB = new Class("ClassB", TypeClass.ABTRACT, pointClassB);
    
    scheme.addClass(classA);
    scheme.addClass(classB);
    
    scheme.select(pointClassA);
    scheme.select(pointClassB);
    
    boolean existRelations = false;
    scheme.addRelation(TypeRelationship.IMPLEMENTS);
    Point pointRelation = new Point(50, 60);
    
    scheme.select(pointRelation);
    assertEquals(existRelations, scheme.existRelations());
  }
  
}
