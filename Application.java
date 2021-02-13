package cunecd01_cs384_hw2;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Insert a polynomial and an initial point: ");
		Polynomial polynomial = new Polynomial();
		polynomial = getInput(polynomial);
		polynomial.printEquation();
		
		System.out.print("\n\nHow many iterations: ");
		int iters = sc.nextInt();
		sc.close();
		
		Polynomial deriv = new Polynomial();
		double x0 = polynomial.getInitialPoint();
		for(int i = 0; i < iters; i++) {
			System.out.println("\n===========Iteration " + (i+1) + "============");
			deriv = new Polynomial();
			deriv.setInitialPoint(x0);
			double[] polys = new double[2];
			polys = Polynomial.hornerMethod(polynomial, deriv, x0);
			double x1 = Polynomial.newtonMethod(x0, polys[0], polys[1]);
			System.out.println("Newtons Method x" + (1+i) + " = " + x1);
			System.out.println("Error |" + x1 + " - " + x0 + "| = " + Polynomial.getError(x1, x0));
			x0 = x1; 
		}	
	}

	public static Polynomial getInput(Polynomial polynomial) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] strArr = input.split("\\s+");
		
		if(strArr.length < 0) {
			System.out.print("Incorrect input.");
			System.out.println("Example input: 2 4 -3 2 3 1 -4 0 -2  would be  2x^4 -3x^2 + 3x -4  x0 = -2");
			System.exit(0);
		}
		
		if(strArr.length % 2 == 0) {
			System.out.print("Incorrect input.");
			System.out.println("Example input: 2 4 -3 2 3 1 -4 0 -2  would be  2x^4 -3x^2 + 3x -4  x0 = -2");
			System.exit(0);
		}
		
		polynomial.setInitialPoint(Double.parseDouble(strArr[strArr.length-1]));
		for(int i = 0; i < strArr.length-1; i+=2) {
			try {
				Polynomial.insert(polynomial, Double.parseDouble(strArr[i]), (int)Integer.parseInt(strArr[i+1]));
			}catch(NumberFormatException e){
				System.out.print("Incorrect input.");
				System.out.println("Example input: 2 4 -3 2 3 1 -4 0 -2  would be  2x^4 -3x^2 + 3x -4  x0 = -2");
				System.exit(0);
		    }
		}
		
		return Polynomial.normalizePolynomial(polynomial);
		
	}
	
	
}
