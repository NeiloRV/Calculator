
/*
 * Error class search inside and estimate equation 
 * on error presence. Next it takes away error cause, 
 * and gives appropriate message in error text field 
 */
public class Error {
	CalcBuilder calc;

	public Error(CalcBuilder inCalculator) {
		calc = inCalculator;
	}

	// this method set error message in error text field
	public void errorDisplay(String errText) {
		calc.errorField.setText(errText);
	}

	// this method search: was any action entered before "equals" was asked?
	public void errorEquationActionAbsence() {
		if (calc.previousEntAction == "=") {
			calc.equation = calc.equation.substring(0, calc.equation.length() - 1);
			calc.currentValue = calc.currentValue.substring(0, calc.currentValue.length() - 1);
			calc.calcOperation.displayEquation(calc.equation);
			errorDisplay("Put arifmetic operation before ");
		}
	}

	//this method answered: was any variable entered?
	public boolean errFirstData() {
		if (calc.equation == "") {
			errorDisplay("Enter first variable");
			return false;
		} else {
			return true;
		}
	}

	// this method answered: was several actions inserted consequently 
	public boolean errDoubleAction() {
		if (calc.equation.charAt(calc.equation.length() - 1) == '*' || calc.equation.charAt(calc.equation.length() - 1) == '/'
				|| calc.equation.charAt(calc.equation.length() - 1) == '+' || calc.equation.charAt(calc.equation.length() - 1) == '-') {
			errorDisplay("Can not change arifmetic operation");
			return false;
		} else {
			return true;
		}
	}
}