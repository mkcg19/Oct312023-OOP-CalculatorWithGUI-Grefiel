package OOPActivities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatorWithGUI extends JFrame {
	private JTextField display;
	private double num1, num2;
	private String operator;
	private boolean decimalPoint = false;
	 
	 public calculatorWithGUI() {
	     setTitle("Calculator");
	     setSize(300, 400);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLayout(new BorderLayout());

	     display = new JTextField();
	     display.setEditable(false);
	     display.setFont(new Font("Arial", Font.PLAIN, 24));
	     add(display, BorderLayout.NORTH);
	     
	     JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

	     String[] buttonLabels = {
	         "C", "+/-", "%", "/",
	         "7", "8", "9", "*",
	         "4", "5", "6", "-",
	         "1", "2", "3", "+",
	         "0", ".", "=", "←"
	     };
	     
	     for (String label : buttonLabels) {
	          JButton button = new JButton(label);
	          button.addActionListener(new ButtonListener());
	          button.setFont(new Font("Arial", Font.PLAIN, 20));
	          buttonPanel.add(button);
	     }

	     add(buttonPanel, BorderLayout.CENTER);
		 
	}// end of calculatorWithGUI method
	 
	private class ButtonListener implements ActionListener {
	     public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();

	         if ("0123456789".contains(command) || (command.equals(".") && !decimalPoint)) {
	        	 if (command.equals(".")) {
	                    decimalPoint = true;
	                }
	             display.setText(display.getText() + command);
	         }else if ("+-*/".contains(command)) {
	             operator = command;
	             num1 = Double.parseDouble(display.getText());
	             display.setText("");
	             decimalPoint = false;
	         }else if("%".equals(command)) {
	        	 double currentNumber = Double.parseDouble(display.getText());
	        	 currentNumber *= 0.01; 
	             display.setText(String.valueOf(currentNumber));
	         }else if ("=".equals(command)) {
	             num2 = Double.parseDouble(display.getText());
	             double result = calculate(num1, num2, operator);
	             if (Double.isNaN(result)) { 
	                 display.setText("Error");
	             }else {
	                 display.setText(String.valueOf(result));
	             }
	             decimalPoint = false;
	         }else if ("C".equals(command)) {
	             display.setText("");
	             decimalPoint = false;
	         }else if("+/-".equals(command)) {
	        	 double currentNumber = Double.parseDouble(display.getText());
	        	 currentNumber *= -1;
	             display.setText(String.valueOf(currentNumber));
	         }else if("←".equals(command)) {
	        	 String currentInput = display.getText();
	        	 if(currentInput.length() > 0) {
	        	    display.setText(currentInput.substring(0, currentInput.length() - 1));
	        	 }
	         }
	     }
	}
	
	private double calculate(double num1, double num2, String operator) {
		double result = 0;
		switch(operator) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			if(num2 != 0) {
			   result = num1 / num2;
			}else {
			    return Double.NaN;
			}
			break;
		default:
			return Double.NaN;
		}
		return result;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
	        calculatorWithGUI calculator = new calculatorWithGUI();
	        calculator.setVisible(true);
	    });
	}

}
