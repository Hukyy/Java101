package polynomial;

public class Main {

	public static void main(String[] args) {
		Polynomial p = new Polynomial("3x^6 + 7x^4 + 3");
		System.out.println(p.evaluate(2));
		Polynomial new_p = p.derivative();
		System.out.println(p);
		System.out.println("Derivative: " + new_p);

	}

}
