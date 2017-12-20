package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public abstract class View extends JFrame {

  protected JMenuBar menuBar;
  protected ViewCanvas canvas;
  
  protected JFileChooser chooser;
  protected String currentFilename;
  protected ActionListener exitAction;

  protected static int width = 600;
  protected static int height = 400;
  
  protected ControllerUI controller;

  public View(String title, ControllerUI controller) {
    super(title);

    currentFilename = null;
    chooser = new JFileChooser(".");
    
    canvas = new ViewCanvas(controller);
    getContentPane().setLayout(new BorderLayout());
    
    menuBar = createMenuBar();
    
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exitListener();
      }
    });

  }
  
  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    addFileMenu(menuBar);
    addEditMenu(menuBar);
    addViewMenu(menuBar);
    addOptionMenu(menuBar);
    addChangeMenu(menuBar);
    
    menuBar.add(Box.createHorizontalGlue());
    addHelpMenu(menuBar);
    return menuBar;
  }
  
  private void addFileMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("File");
    JMenuItem menuFileItem;
    menuBar.add(menu);
    
    menuFileItem = new JMenuItem("New");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> newFile());

    menuFileItem = new JMenuItem("Open");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> openFileListener());

    menuFileItem = new JMenuItem("Save");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> saveFile());

    menuFileItem = new JMenuItem("Save As");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> saveAsFileListener());
    
    menuFileItem = new JMenuItem("Export");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> controller.exportToFiles());

    menu.add(new JSeparator());

    menuFileItem = new JMenuItem("Exit");
    menu.add(menuFileItem);
    menuFileItem.addActionListener(event -> exitListener());
  }
  
  private void addEditMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Edit");
    JMenuItem menuEditItem;
    menuBar.add(menu);
    
    menuEditItem = new JMenuItem("Undo");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> controller.undo());
    
    menuEditItem = new JMenuItem("Redo");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> controller.redo());
    
    menuEditItem = new JMenuItem("Group Shapes");
    menu.add(menuEditItem);
//    menuEditItem.addActionListener(event -> canvas.groupShapes());
    
    menuEditItem = new JMenuItem("Ungroup Shapes");
    menu.add(menuEditItem);
//    menuEditItem.addActionListener(event -> canvas.ungroupShapes());
    
    menuEditItem = new JMenuItem("Clear all");
    menu.add(menuEditItem);
    menuEditItem.addActionListener(event -> controller.clearAll());
  }
  
  private void addOptionMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Relation");
    JMenuItem menuRelationship;
    menuBar.add(menu);
     
    menuRelationship = new JMenuItem("inheritance");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.inheritance());
    
    menuRelationship = new JMenuItem("association");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.association());
    
    menuRelationship = new JMenuItem("aggregation");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.aggregation());    
    
    menuRelationship = new JMenuItem("composition");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.composition());    
    
    menuRelationship = new JMenuItem("implements");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.implementsRelation());    
    
  }
  
  private void addViewMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("View");
    JMenuItem menuOptionItem;
    menuBar.add(menu);
    
    menuOptionItem = new JMenuItem("Hide Tools");
    menu.add(menuOptionItem);
    menuOptionItem.addActionListener(event -> hideTools());
    
    menuOptionItem = new JMenuItem("Show Tools");
    menu.add(menuOptionItem);
    menuOptionItem.addActionListener(event -> showTools());
  }
  
  private void addChangeMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Change");
    JMenuItem menuChange;
    JMenu multiMenu;
    menuBar.add(menu);
    
    multiMenu = new JMenu("Classes");
    
    menuChange = new JMenuItem("Interface");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeInterface());
    
    menuChange = new JMenuItem("Abstract");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeAbstract());
    
    menuChange = new JMenuItem("Concrete Class");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeConcrete());    
    
    menu.add(multiMenu);
    
    multiMenu = new JMenu("Relations");
    
    menuChange = new JMenuItem("inheritance");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeInheritance());  
    
    menuChange = new JMenuItem("association");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeAssociation());  
    
    menuChange = new JMenuItem("aggregation");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeAggregation());  
    
    menuChange = new JMenuItem("composition");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeComposition());  
    
    menuChange = new JMenuItem("implements");
    multiMenu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeImplementsRelation());  
    
    menu.add(multiMenu);
    
    menuChange = new JMenuItem("Remove");
    menu.add(menuChange);
    menuChange.addActionListener(event -> controller.remove());
    
    menuChange = new JMenuItem("Name Class");
    menu.add(menuChange);
    menuChange.addActionListener(event -> controller.changeNameClass());
    
  }
  
  private void addHelpMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Help");
    JMenuItem menuHelpItem;
    menuBar.add(menu);

    menuHelpItem = new JMenuItem("About");
    menu.add(menuHelpItem);
    menuHelpItem.addActionListener(event -> aboutListener());
  }

  private void newFile() {
    currentFilename = null;
    controller.newScheme();
    setTitle("UML Pad");
  }
  
  private void openFileListener() {
    int retval = chooser.showDialog(null, "Open");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (theFile.isFile()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          openFile(filename);
        }
      }
    }
  }
  
  private void saveFile() {
    if (currentFilename == null) {
      currentFilename = "Untitled";
    }
    controller.saveFile(currentFilename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }
  
  private void saveAsFileListener() {
    int retval = chooser.showDialog(null, "Save As");
    if (retval == JFileChooser.APPROVE_OPTION) {
      File theFile = chooser.getSelectedFile();
      if (theFile != null) {
        if (!theFile.isDirectory()) {
          String filename = chooser.getSelectedFile().getAbsolutePath();
          saveFileAs(filename);
        }
      }
    }
  }
  
  protected void hideTools(){
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(menuBar, BorderLayout.NORTH);
    getContentPane().add(canvas, BorderLayout.CENTER);
  }

  protected void showTools(){
  }
   
  private void exitListener() {
    int result = JOptionPane.showConfirmDialog(null,
            "Do you want to exit Scribble Pad?",
            "Exit Scribble Pad?",
            JOptionPane.YES_NO_OPTION);
    if (result == JOptionPane.YES_OPTION) {
      saveFile();
      System.exit(0);
    } else {
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
  }

  private void aboutListener() {
    JOptionPane.showMessageDialog(null,
            "UML Diagram version 1.0\nCopyright (c) Andres Gutierrez 2017", "About",
            JOptionPane.INFORMATION_MESSAGE);
  }
  
  protected void openFile(String filename) {
    currentFilename = filename;
    controller.openFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }

  protected void saveFileAs(String filename) {
    currentFilename = filename;
    controller.saveFile(filename);
    setTitle("Scribble Pad [" + currentFilename + "]");
  }
  
}

