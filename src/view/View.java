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
  
  // factory method 
//  protected ViewCanvas makeCanvas() {
//    return new ViewCanvas();
//  }
  
  protected JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    addFileMenu(menuBar);
    addViewMenu(menuBar);
    addEditMenu(menuBar);
    addOptionMenu(menuBar);
    
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
//    menuFileItem.addActionListener(event -> newFile());

    menuFileItem = new JMenuItem("Open");
    menu.add(menuFileItem);
//    menuFileItem.addActionListener(event -> openFileListener());

    menuFileItem = new JMenuItem("Save");
    menu.add(menuFileItem);
//    menuFileItem.addActionListener(event -> saveFile());

    menuFileItem = new JMenuItem("Save As");
    menu.add(menuFileItem);
//    menuFileItem.addActionListener(event -> saveAsFileListener());

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
    
    menuEditItem = new JMenuItem("Select all");
    menu.add(menuEditItem);
//    menuEditItem.addActionListener(event -> canvas.selectAll());
    
    menuEditItem = new JMenuItem("Clear all");
    menu.add(menuEditItem);
//    menuEditItem.addActionListener(event -> canvas.clearAll());
  }
  
  private void addOptionMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Relation");
    JMenuItem menuRelationship;
    menuBar.add(menu);
     
    menuRelationship = new JMenuItem("INHERITANCE");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.inheritance());
    
    menuRelationship = new JMenuItem("ASSOCIATION");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.association());
    
    menuRelationship = new JMenuItem("AGGREGATION");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.aggregation());    
    
    menuRelationship = new JMenuItem("COMPOSITION");
    menu.add(menuRelationship);
    menuRelationship.addActionListener(event -> controller.composition());    
    
    menuRelationship = new JMenuItem("IMPLEMENTS");
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
  
  private void addHelpMenu(JMenuBar menuBar){
    JMenu menu = new JMenu("Help");
    JMenuItem menuHelpItem;
    menuBar.add(menu);

    menuHelpItem = new JMenuItem("About");
    menu.add(menuHelpItem);
    menuHelpItem.addActionListener(event -> aboutListener());
  }

//  private void newFile() {
//    currentFilename = null;
//    canvas.newFile();
//    setTitle("Scribble Pad");
//  }
//  
//  private void openFileListener() {
//    int retval = chooser.showDialog(null, "Open");
//    if (retval == JFileChooser.APPROVE_OPTION) {
//      File theFile = chooser.getSelectedFile();
//      if (theFile != null) {
//        if (theFile.isFile()) {
//          String filename = chooser.getSelectedFile().getAbsolutePath();
//          openFile(filename);
//        }
//      }
//    }
//  }
//  
//  private void saveFile() {
//    if (currentFilename == null) {
//      currentFilename = "Untitled";
//    }
//    canvas.saveFile(currentFilename);
//    setTitle("Scribble Pad [" + currentFilename + "]");
//  }
//  
//  private void saveAsFileListener() {
//    int retval = chooser.showDialog(null, "Save As");
//    if (retval == JFileChooser.APPROVE_OPTION) {
//      File theFile = chooser.getSelectedFile();
//      if (theFile != null) {
//        if (!theFile.isDirectory()) {
//          String filename = chooser.getSelectedFile().getAbsolutePath();
//          saveFileAs(filename);
//        }
//      }
//    }
//  }
//  
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
//      saveFile();
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
//  
//  protected void openFile(String filename) {
//    currentFilename = filename;
//    canvas.openFile(filename);
//    setTitle("Scribble Pad [" + currentFilename + "]");
//  }
//
//  protected void saveFileAs(String filename) {
//    currentFilename = filename;
//    canvas.saveFile(filename);
//    setTitle("Scribble Pad [" + currentFilename + "]");
//  }
//  
}

