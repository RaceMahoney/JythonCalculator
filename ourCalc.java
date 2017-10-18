import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import org.python.core.*;
import org.python.util.*;

public class ourCalc extends JFrame implements ActionListener {
  int numRows = 6; // add an extra one
  int numButtons = 17;
  int buttonWidth = 65;
  int buttonHeight = 40;
  int maxItemsInRow = 4;
  Dimension buttonDimension = new Dimension(buttonWidth,buttonHeight);
  JPanel[] row = new JPanel[numRows];
  JButton[] button = new JButton[numButtons];
  JTextArea display = new JTextArea(2,20);
  String[] equation = new String[3];
  Font font = new Font("Times new Roman", Font.BOLD, 14);
  String[] buttonString = {"7", "8", "9","+",
                         "4", "5", "6", "-",
                         "1", "2", "3", "*",
                         ".", "0", "=", "/",
                         "C"};
  static PythonInterpreter interpreter = null;
  static PyInstance CALC;
  static PyFunction add;
  static PyFunction sub;
  static PyFunction mult;
  static PyFunction div;
  //static ourCalc ie;

  public static void main(String[] arguments) throws PyException {
      ourCalc c = new ourCalc();
      interpreter = new PythonInterpreter();
      interpreter.exec("from calc import add");
      interpreter.exec("from calc import sub");
      interpreter.exec("from calc import mult");
      interpreter.exec("from calc import div");
      add =(PyFunction)interpreter.get("add",PyFunction.class);
      sub =(PyFunction)interpreter.get("sub",PyFunction.class);
      mult =(PyFunction)interpreter.get("mult",PyFunction.class);
      div =(PyFunction)interpreter.get("div",PyFunction.class);
      if (add == null){
        System.out.println("Null for some reason");
      }
      //System.out.println(( add.__call__(new PyFloat(5), new PyFloat(5))));
  }

  ourCalc(){
    super("Calc");

    setDesign();
    setSize(480,320); //window size I think
    setResizable(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    GridLayout grid = new GridLayout(numRows,maxItemsInRow);
    setLayout(grid);
    FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
    FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
    for (int i = 0; i < numRows; i++){
      row[i] = new JPanel();
    }
    row[0].setLayout(f1);
    for(int i = 1; i < numRows; i++){
      row[i].setLayout(f2);
    }
    for(int i = 0; i < numButtons; i++) {
      button[i] = new JButton();
      button[i].setText(buttonString[i]);
      button[i].setFont(font);
      button[i].addActionListener(this);
    }

    display.setFont(font);
    display.setEditable(false);
    display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    for (int i = 0; i < numButtons; i++){
      button[i].setPreferredSize(buttonDimension);
    }

    row[0].add(display);
    add(row[0]);

    for(int i = 0; i < 4; i++){
      row[1].add(button[i]);
    }
    add(row[1]);

    for(int i = 4; i < 8; i++){
      row[2].add(button[i]);
    }
    add(row[2]);

    for(int i = 8; i < 12; i++){
      row[3].add(button[i]);
    }
    add(row[3]);

    for(int i = 12; i < 16; i++){
      row[4].add(button[i]);
    }
    add(row[4]);

    for(int i = 16; i < numButtons; i++){
      row[5].add(button[i]);
    }
    add(row[5]);


    setVisible(true);
  }

   static void execfile( final String fileName )  {
      interpreter.execfile(fileName);
   }

   static PyInstance createClass( final String className)  {
      return (PyInstance) interpreter.eval(className);
   }

  public final void setDesign() {
    try {
        UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch(Exception e) {
    }
  }
  public void actionPerformed(ActionEvent ae) {
    //display.append("7");
    //if(ae.getSource() == button[0])
    int buttonNum = 0;
    for (int i = 0; i < numButtons; i++){
      if(ae.getSource() == button[i]){
        buttonNum = i;
        break;
      }
    }

    if (buttonNum == 0){
      display.append("7");
    } else if (buttonNum == 1){
      display.append("8");
    } else if (buttonNum == 2){
      display.append("9");
    } else if (buttonNum == 3){
      display.append("+");
    } else if (buttonNum == 4){
      display.append("4");
    } else if (buttonNum == 5){
      display.append("5");
    } else if (buttonNum == 6){
      display.append("6");
    } else if (buttonNum == 7){
      display.append("-");
    } else if (buttonNum == 8){
      display.append("1");
    } else if (buttonNum == 9){
      display.append("2");
    } else if (buttonNum == 10){
      display.append("3");
    } else if (buttonNum == 11){
      display.append("*");
    } else if (buttonNum == 12){
      display.append(".");
    } else if (buttonNum == 13){
      display.append("0");
    } else if (buttonNum == 14){
      getAnswer();
    } else if (buttonNum == 15){
      display.append("/");
    } else if (buttonNum == 16){
      clear();
    }
  }

  public void getAnswer(){
    System.out.println(display.getText());
    String text = display.getText();
    String operator = "";
    /* this part will determine the operator */
    for (int i = 0; i < text.length(); i++){
      if (text.charAt(i) == '+'){
        operator = "[+]";
      } else if (text.charAt(i) == '-'){
        operator = "-";
      } else if (text.charAt(i) == '*'){
        operator = "[*]";
      } else if (text.charAt(i) == '/'){
        operator = "/";
      }
    }
    String[] parts = text.split(operator);
    double answer = 0;
    if (operator == "[+]"){
      answer = plus(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    } else if (operator == "-"){
      answer = minus(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    } else if (operator == "[*]"){
      answer = mult(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    } else if (operator == "/"){
      answer = div(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }

    if (answer == 0){
      answer  = 0;
    }
    System.out.println("adding to GUI: " + answer);
    display.setText(Double.toString(answer));
  }

  public void clear() {
    try {
        display.setText(""); // Sets the display blank
        for(int i = 0; i < 3; i++){
          equation[i] = ""; // Sets the functions back to false
        }
    } catch(NullPointerException e) {
    }
  }


  public double plus(double a, double b){
    System.out.println("Adding: " + a + " and " + b);
    float newA = (float)a;
    float newB = (float)b;
    return add.__call__(new PyFloat(newA), new PyFloat(newB)).asDouble();
  }

  public double minus(double a, double b){
    System.out.println("subtracting: " + a + " from " + b);
    float newA = (float)a;
    float newB = (float)b;
    return sub.__call__(new PyFloat(newA), new PyFloat(newB)).asDouble();
  }

  public double div(double a, double b){
    System.out.println("dividing: " + a + " and " + b);
    float newA = (float)a;
    float newB = (float)b;
    return div.__call__(new PyFloat(newA), new PyFloat(newB)).asDouble();
  }

  public double mult(double a, double b){
    System.out.println("multiplying: " + a + " and " + b);
    float newA = (float)a;
    float newB = (float)b;
    return mult.__call__(new PyFloat(newA), new PyFloat(newB)).asDouble();
  }




}
