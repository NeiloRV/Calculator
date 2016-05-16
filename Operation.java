
/*
 * Class Operation analyze consequence of operation, 
 * update equation and result lines  
 */
public class Operation {
	CalcBuilder calc;
	Error calcError;

	public Operation(CalcBuilder inCalculator, Error inCalcError) {
		calc = inCalculator;
		calcError = inCalcError;
	}

	// method insert put new variable or action in equation line  
	public void insert(String equationLocal) {
		calc.equation = calc.equation + equationLocal;
		calc.currentValue = calc.currentValue + equationLocal;
		displayEquation(calc.equation);
		calcError.errorEquationActionAbsence();
	}

	// method displayEquation set equation expression in equation and error (if it`s OK) text field
	public void displayEquation(String res) {
		calc.equationField.setText(res);
		calcError.errorDisplay("All is correct");
	}

	// method displayResult set new result in result text field
	public void displayResult(double resultDoubleLocal) {
		calc.resultField.setText("" + resultDoubleLocal);
	}

	// method action analyze which action was user insert, and makes appropriate calculation 
	public void action(String currentValueLocal, String lastEntAction) {
		if (calc.currentValueIntPart == "") {calc.currentValueIntPart = "0";}
		calc.currentValueDoubleForm = Double.parseDouble(currentValueLocal);
		calc.pointAbsenceCurValue = false;
		calc.pointNotEntered = true;

		if (calc.pointPresent) {
			calc.currentValueDoubleForm = Double.parseDouble(calc.currentValueIntPart)
					+ (calc.currentValueDoubleForm / Math.pow(10, currentValueLocal.length()));
			calc.pointPresent = !calc.pointPresent;
		}

		if (lastEntAction == "=") {
			switch (calc.previousEntAction) {
			case "+":
				calc.result = calc.result + calc.currentValueDoubleForm;
				displayResult(calc.result);
				calc.previousEntAction = lastEntAction;
				break;
			case "-":
				calc.result = calc.result - calc.currentValueDoubleForm;
				displayResult(calc.result);
				calc.previousEntAction = lastEntAction;
				break;
			case "*":
				calc.result = calc.result * calc.currentValueDoubleForm;
				displayResult(calc.result);
				calc.previousEntAction = lastEntAction;
				break;
			case "/": {
				if (calc.currentValueDoubleForm == 0.0) {
					calc.equation = calc.equation.substring(0, calc.equation.lastIndexOf("/") + 1);
					displayEquation(calc.equation);
					calc.currentValue = "";
					calc.currentValueIntPart = "";
					displayResult(calc.result);
					calcError.errorDisplay("Do not divide on 0! Enter another data.");
					break;
				} else {
					calc.result = calc.result / calc.currentValueDoubleForm;
					displayResult(calc.result);
					calc.previousEntAction = lastEntAction;
					break;
				}
			}
			}

		} else {
			if (calc.counter != 0) {
				switch (calc.previousEntAction) {
				case "+":
					calc.result = calc.result + calc.currentValueDoubleForm;
					displayResult(calc.result);
					calc.previousEntAction = lastEntAction;
					break;
				case "-":
					calc.result = calc.result - calc.currentValueDoubleForm;
					displayResult(calc.result);
					calc.previousEntAction = lastEntAction;
					break;
				case "*":
					calc.result = calc.result * calc.currentValueDoubleForm;
					displayResult(calc.result);
					calc.previousEntAction = lastEntAction;
					break;
				case "/": {
					if (calc.currentValueDoubleForm == 0.0) {
						calc.equation = calc.equation.substring(1, calc.equation.lastIndexOf("/") + 1);
						displayEquation(calc.equation);
						calc.currentValue = "";
						calc.currentValueIntPart = "";
						displayResult(calc.result);
						calcError.errorDisplay("Do not divide on 0! Enter another data.");
						break;
					} else {
						calc.result = calc.result / calc.currentValueDoubleForm;
						displayResult(calc.result);
						calc.previousEntAction = lastEntAction;
						break;
					}
				}
				}
			} else {
				calc.result = calc.currentValueDoubleForm;
				calc.previousEntAction = lastEntAction;
				calc.counter++;
			}
		}
	}
}
