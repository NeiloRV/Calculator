import java.awt.EventQueue;
/*
 * This program describes calculator, 
 * which can works with complex expression,
 * where several arithmetic operation calculate consequentially   
 */
public class CalcMain {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CalcMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	CalcMain() {
			CalcBuilder calculator = new CalcBuilder(); // create calculator
	}
}
