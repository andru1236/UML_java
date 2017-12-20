package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EventListener;
import javax.swing.JPanel;
import logicUML.Scheme;

public class ViewCanvas extends JPanel{

  private Color curColor;
  private EventListener listener;
  private Scheme scheme;
  
  public ViewCanvas(ControllerUI controller) {
    addMouseListener(controller);
    addMouseMotionListener(controller);
  }
  
  @Override
  public void paint(Graphics g){
    if(scheme != null){
      scheme.draw(g); 
      scheme.show();
    }
  }
  
  public void addScheme(Scheme scheme){
    this.scheme = scheme;
  }
 
}