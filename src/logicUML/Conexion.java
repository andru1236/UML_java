package logicUML;


public class Conexion {
  
  private Relationship relation;
  private Class classB;
  
  public Conexion(Relationship relation, Class classB){
    this.relation = relation;
    this.classB = classB;

  }
  
  public Relationship getRelation() {
    return relation;
  }

  public void setRelation(Relationship relation) {
    this.relation = relation;
  }

  public Class getClassB() {
    return classB;
  }

  public void setClassB(Class classB) {
    this.classB = classB;
  }
  
  public boolean isEquals(Conexion conex){
//    return relation.getType() == conex.getRelation().getType() && 
//            classB.getName() == conex.getClassB().getName() &&
//            relation.equals(conex.getRelation())&& 
//            classB.equals(conex.getClassB());
return relation == conex.getRelation();
  }
  
}
