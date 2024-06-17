import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator implements ActionListener{
	
	JFrame frame;
	JTextField textField;
	
	JButton[] numberButtons = new JButton[10]; //array to hold 10 buttons for 10 numbers
	JButton[] functionButtons = new JButton[9]; //array of jbuttons to hold our function buttons
	JButton addButton, subButton, mulButton, divButton;		
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	
	Font myFont = new Font("Cabril", Font.BOLD, 30);
	
	double num1 = 0, num2= 0, result = 0;
	char operator;
	
	Calculator(){
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);
		
		//adding text feild after setting the frame, but before we instantiate visibility to true;
		textField = new JTextField();	//creating a textfield object
		textField.setBounds(50, 25, 300, 50);
		textField.setFont(myFont);
		textField.setEditable(false); //disallowing editing texts in the textfield
		
		//Adding Buttons
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		negButton = new JButton("(-)");
		
		//adding our created buttons objects to the function button array
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		//adding action listener
		for(int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);	//this is to remove the outline on the text thats focused
		}
		
		//for loop for our numbered buttons
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		
		//creating Button functionality
		negButton.setBounds(50, 430, 100, 50);
		delButton.setBounds(150, 430, 100, 50);	//set where x=50, y=430	||	make it 145px long, 50px wide
		clrButton.setBounds(250, 430, 100, 50);
		
		//our panel is just a square (I dont think it affects code)
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,4, 10, 10));	//adding grid 4 rows and 4 cols with 10px space between rows and 10px space between cols
		
		//adding buttons to out panel
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(equButton);
		panel.add(divButton);
		
		frame.add(negButton);
		frame.add(panel);
		frame.add(delButton);
		frame.add(clrButton);	
		frame.add(textField);	//adding textField to our Frame
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();	//create calc object
	}
	
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
			
		}
		if(e.getSource() == decButton) {
			textField.setText(textField.getText().concat("."));
		}
		if(e.getSource() == addButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '+';
			textField.setText("");
		}
		if(e.getSource() == subButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '-';
			textField.setText("");
		}
		if(e.getSource() == mulButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '*';
			textField.setText("");
		}
		if(e.getSource() == divButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '/';
			textField.setText("");
		}
		
		if(e.getSource() == equButton) {
			num2 = Double.parseDouble(textField.getText());
			
			//never use double quotation marks, only use it for String text, rather use '' everywhere 
			switch(operator) {
			case'+':
				result = num1 + num2;
				break;
			case'-':
				result = num1 - num2;
				break;
			case'*':
				result = num1 * num2;
				break;
			case'/':
				result = num1 / num2;
				break;
			
			}
			textField.setText(String.valueOf(result));
			num1 = result;
		}
		if(e.getSource() == clrButton) {
			textField.setText("");
		}
		if(e.getSource() == clrButton) {
			String string = textField.getText();
			textField.setText("");
			for(int i=0; i < string.length()-1; i ++) {
				textField.setText(textField.getText() + string.charAt(i));
			}
		}
		if(e.getSource() == negButton) {
			double temp = Double.parseDouble(textField.getText());	//get the current text in the textfield
			temp *= -1;	//multiply that value in the textfield by negative 1 to change it into a negative
			textField.setText(String.valueOf(temp));	//now replace the value that was in the textField with its negative
		}
		

	}

}

