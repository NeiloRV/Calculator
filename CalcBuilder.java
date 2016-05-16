import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalcBuilder {
	JFrame frame;				// main frame
	JTextField equationField; 	// text field, where calculated expression will be shown  
	JTextField resultField; 	// text field with current result
	JTextField errorField;		// text field with error information 

	int counter = 0; 	// counter of operation number
	int buttonWidth = 50, buttonHeight = buttonWidth; // button geometry
	double result;   				// result of calculation, 
	double currentValueDoubleForm;  // previous result variable
	boolean pointPresent = false, pointAbsenceCurValue = false, pointNotEntered = true; // variables which indicate was point entered or not 
	String equation = ""; // string format of calculated expression
	String previousEntAction = "", localCurrentAction, currentValue = ""; // several variables, which keep last, current action 
	String currentValueIntPart = ""; //current int value of user-entered number 

	Error calcError = new Error(this); // create operation block
	Operation calcOperation = new Operation(this, calcError); // create error analyzing block

	public CalcBuilder() {
		initialize(); // initialize calculator GUI
	}
	
	void initialize() {

		// initialize GUI frame
		frame = new JFrame();
		frame.setBounds(100, 100, 330, 410);
		frame.setTitle("Calculator Neilo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		// next several initialization lines describe user number buttons
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("1");
			}
		});
		button.setBounds(10, 206, buttonWidth, buttonHeight);
		frame.getContentPane().add(button);

		JButton button_0 = new JButton("0");
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("0");
			}
		});
		button_0.setBounds(10, 261, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_0);

		JButton button_00 = new JButton("00");
		button_00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("0");
				calcOperation.insert("0");
			}
		});
		button_00.setBounds(65, 261, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_00);

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("2");
			}
		});
		button_2.setBounds(65, 206, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("3");
			}
		});
		button_3.setBounds(120, 206, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("4");
			}
		});
		button_4.setBounds(10, 150, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("5");
			}
		});
		button_5.setBounds(65, 150, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_5);

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("6");
			}
		});
		button_6.setBounds(120, 150, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_6);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("7");
			}
		});
		button_7.setBounds(10, 94, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_7);

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("8");
			}
		});
		button_8.setBounds(65, 94, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_8);

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcOperation.insert("9");
			}
		});
		button_9.setBounds(120, 94, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_9);

		
		// Next lines initialize operation, which user can utilize
		JButton button_eqv = new JButton("=");  				// button "equal"
		button_eqv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localCurrentAction = "=";
				if (previousEntAction == "" || previousEntAction == "=") {
					calcError.errorDisplay("Enter arifmetic operation before");
				} else {
					equation = equation + "=";
					calcOperation.displayEquation(equation);
					calcOperation.action(currentValue, localCurrentAction);
					currentValue = "";
				}
			}
		});
		button_eqv.setBounds(254, 261, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_eqv);

		
		JButton button_1 = new JButton("*");					// multiplication button
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localCurrentAction = "*";
				if (calcError.errFirstData() && calcError.errDoubleAction()) {
					if (previousEntAction == "=") {
						equation = equation.substring(0, equation.length() - 1);
						equation = "(" + equation + ")*";
						calcOperation.displayEquation(equation);
						previousEntAction = "*";
					} else {
						calcOperation.action(currentValue, localCurrentAction);
						if (calcError.errDoubleAction()) {
							equation = "(" + equation + ")*";
							calcOperation.displayEquation(equation);
							currentValue = "";
						} else {
							calcError.errorDisplay("Don't devide on 0! Can't change arifmetic operation");
						}
					}
				}
			}
		});
		button_1.setBounds(199, 94, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_1);

		JButton button_10 = new JButton("/");					// division button
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localCurrentAction = "/";
				if (calcError.errFirstData() && calcError.errDoubleAction()) {
					if (previousEntAction == "=") {
						equation = equation.substring(0, equation.length() - 1);
						equation = "(" + equation + ")/";
						calcOperation.displayEquation(equation);
						previousEntAction = "/";
					} else {
						calcOperation.action(currentValue, localCurrentAction);
						if (calcError.errDoubleAction()) {
							equation = "(" + equation + ")/";
							calcOperation.displayEquation(equation);
							currentValue = "";
						} else {
							calcError.errorDisplay("Don't devide on 0! Can't change arifmetic operation");
						}
					}
				}
			}
		});
		button_10.setBounds(199, 150, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_10);

		JButton button_11 = new JButton("-");						// button minus
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localCurrentAction = "-";
				if (calcError.errFirstData() && calcError.errDoubleAction()) {
					if (previousEntAction == "=") {
						equation = equation.substring(0, equation.length() - 1);
						equation = equation + "-";
						calcOperation.displayEquation(equation);
						previousEntAction = "-";
					} else {
						calcOperation.action(currentValue, localCurrentAction);
						if (calcError.errDoubleAction()) {
							equation = equation + "-";
							calcOperation.displayEquation(equation);
							currentValue = "";
						} else {
							calcError.errorDisplay("Don't devide on 0! Can't change arifmetic operation");
						}
					}
				}
			}
		});
		button_11.setBounds(199, 206, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_11);

		JButton button_12 = new JButton("+"); 						// button plus
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localCurrentAction = "+";
				if (calcError.errFirstData() && calcError.errDoubleAction()) {
					if (previousEntAction == "=") {
						equation = equation.substring(0, equation.length() - 1);
						equation = equation + "+";
						calcOperation.displayEquation(equation);
						previousEntAction = "+";
					} else {
						calcOperation.action(currentValue, localCurrentAction);
						if (calcError.errDoubleAction()) {
							equation = equation + "+";
							calcOperation.displayEquation(equation);
							currentValue = "";
						} else {
							calcError.errorDisplay("Don't devide on 0! Can't change arifmetic operation");
						}
					}
				}
			}
		});
		button_12.setBounds(199, 261, buttonWidth, buttonHeight);
		frame.getContentPane().add(button_12);

		JButton btnC = new JButton("C"); 						// button clear
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				equation = "";
				currentValue = "";
				calcOperation.displayEquation(equation);
				calcOperation.displayResult(0);
				counter = 0;
				result = 0.0;
				previousEntAction = "";
			}
		});
		btnC.setBounds(254, 206, buttonWidth, buttonHeight);
		frame.getContentPane().add(btnC);

		
		// initializing text field for equation expression
		equationField = new JTextField();
		equationField.setBackground(Color.WHITE);
		equationField.setBounds(79, 10, 225, 33);
		equationField.setText("");
		frame.getContentPane().add(equationField);
		
		// initializing text field for current result
		resultField = new JTextField();
		resultField.setColumns(10);
		resultField.setBounds(79, 49, 225, 33);
		resultField.setText("");
		frame.getContentPane().add(resultField);

		JButton button_point = new JButton("."); 				// button "point"
		button_point.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char point = '.';
				if (currentValue.length() == 0) {
					calcOperation.insert("0.");
				}

				// this block looking for "." (to prevent insert point second time in number)
				for (int i = 0; i < currentValue.length(); i++) {
					if (currentValue.charAt(i) == point) {
						break;
					} else {
						if (i == currentValue.length() - 1) {
							pointAbsenceCurValue = true;
						}
					}
				}
				if (pointAbsenceCurValue && pointNotEntered) {
					currentValueIntPart = currentValue;
					calcOperation.insert(".");
					currentValue = "";
					pointPresent = true;
					pointNotEntered = false;
				}
			}
		});
		button_point.setBounds(120, 261, 50, 50);
		frame.getContentPane().add(button_point);

		JLabel lblNewLabel = new JLabel("Equation");				// equation text field name
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(14, 19, 55, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblResult = new JLabel("Result"); 					// result text field name
		lblResult.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblResult.setBounds(14, 58, 55, 14);
		frame.getContentPane().add(lblResult);

		errorField = new JTextField();							// error text field 
		errorField.setBackground(SystemColor.controlHighlight);
		errorField.setColumns(10);
		errorField.setBounds(10, 322, 294, 33);
		frame.getContentPane().add(errorField);

	}
}
