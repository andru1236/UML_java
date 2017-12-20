package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import logicUML.Scheme;
import view.tools.CreateAbstract;
import view.tools.CreateClass;
import view.tools.CreateInterface;
import view.tools.Move;
import view.tools.Select;
import view.tools.Tool;

public class DesingUML extends View {

  private ToolKit toolkit;
  private JComponent toolbar;

  public DesingUML(String title, ControllerUI controller) {
    super(title, controller);
    init();
    initTools();

    toolbar = createToolBar();
    getContentPane().add(toolbar, BorderLayout.WEST);
    JMenu menu = createToolMenu();
    menuBar.add(menu, 2);

    revalidate();
  }

  private void init() {
    setSize(width, height);
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width / 2 - width / 2,
            screenSize.height / 2 - height / 2);
    setVisible(true);
  }

  public void addController(ControllerUI controller) {
    this.controller = controller;
    controller.setTool(toolkit.getTool(0));
  }

  public void addScheme(Scheme scheme) {
    canvas.addScheme(scheme);
  }

  public void draw() {
    canvas.repaint();
    revalidate();
  }

  private void initTools() {
    toolkit = new ToolKit();
    toolkit.addTool(new CreateClass());
    toolkit.addTool(new CreateAbstract());
    toolkit.addTool(new CreateInterface());
    toolkit.addTool(new Select());
    toolkit.addTool(new Move());
  }

  private JComponent createToolBar() {
    JPanel toolbar = new JPanel(new GridLayout(0, 1));
    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JButton button = new JButton(tool.getName());
        button.addActionListener(toolListener -> selectedTool(toolListener));
        toolbar.add(button);
      }
    }
    return toolbar;
  }

  private void selectedTool(ActionEvent event) {
    Object source = event.getSource();
    if (source instanceof AbstractButton) {
      AbstractButton button = (AbstractButton) source;
      Tool tool = toolkit.setSelectedTool(button.getText());
      controller.setTool(tool);
    }
  }

  protected JMenu createToolMenu() {
    JMenu menu = new JMenu("Tools");
    int n = toolkit.getToolCount();
    for (int i = 0; i < n; i++) {
      Tool tool = toolkit.getTool(i);
      if (tool != null) {
        JMenuItem menuitem = new JMenuItem(tool.getName());
        menuitem.addActionListener(toolListener -> selectedTool(toolListener));
        menu.add(menuitem);
      }
    }
    return menu;
  }

  @Override
  protected void hideTools() {
    BorderLayout layout = (BorderLayout) this.getContentPane().getLayout();
    getContentPane().remove(layout.getLayoutComponent(BorderLayout.WEST));
    revalidate();
  }

  @Override
  protected void showTools() {
    getContentPane().add(toolbar, BorderLayout.WEST);
    revalidate();
  }

}
