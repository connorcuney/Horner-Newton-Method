public class Polynomial {
	
	Term head;
	double initialPoint;

	public Polynomial() {
		//default constructor
	
	}
	
	public static Polynomial normalizePolynomial(Polynomial list) {
		Term currentTerm = list.head;
		Polynomial newList = new Polynomial();
		newList.setInitialPoint(list.getInitialPoint());
		while(currentTerm != null) {
			insert(newList, currentTerm.getCoefficient(), currentTerm.getExponent());
			if(currentTerm.getNext() != null) {
				if(currentTerm.getNext().getExponent() != currentTerm.getExponent()-1) {
					insert(newList, 0, currentTerm.getExponent()-1);
				}
			}
			currentTerm = currentTerm.getNext();
		}
		
		return newList;
	}
	
	public static void insert(Polynomial list, double coefficient, int exponent){ 
        Term term = new Term(coefficient, exponent); 
        term.next = null; 
  
        if (list.head == null) 
            list.head = term; 
        else { 
            Term last = list.head; 
            while (last.next != null) 
                last = last.next; 
            last.next = term; 
        }     
    } 
	
	public void printLinkedList() {
		 Term currentTerm = this.head;
	     while(currentTerm != null) {
	    	 System.out.print(currentTerm.getCoefficient() + " " + currentTerm.getExponent() + " ");
	    	 currentTerm = currentTerm.getNext();
	     }
	     System.out.print(this.initialPoint);
	}
	
	public void printEquation() {  
        System.out.print("Equation: "); 
        String equation = "";
        Term currentTerm = this.head;
        if(currentTerm != null) {
        	if((int)currentTerm.getExponent() == 0) 
        		equation += currentTerm.getCoefficient();
        	else
        		equation += currentTerm.getCoefficient() + "x^" + (int)currentTerm.getExponent();  
        	
        	currentTerm = currentTerm.next; 
        }
        
        while (currentTerm != null) { 
        	if(currentTerm.getCoefficient() > -1 && (int)currentTerm.getExponent() == 0) 
        		equation += "+" + currentTerm.getCoefficient(); 
        	else if(currentTerm.getCoefficient() < 0 && (int)currentTerm.getExponent() == 0)
        		equation += currentTerm.getCoefficient();
        	else if(currentTerm.getCoefficient() > -1)
        		equation += "+" + currentTerm.getCoefficient() + "x^" + (int)currentTerm.getExponent();
        	else
        		equation += currentTerm.getCoefficient() + "x^" + (int)currentTerm.getExponent();
        	
            currentTerm = currentTerm.next; 
        } 
        
        equation = equation.replace("+", " + ");
        equation = equation.replace("-", " - ");
        equation = equation.replace("x^ - ", "x^-");
        System.out.println(equation); 
        
        System.out.print("Initial Point: " + getInitialPoint());
    }

	public static double[] hornerMethod(Polynomial polynomial, Polynomial deriv, double x) {
		Term currentTerm = polynomial.head;
		double[] value = new double[2];
		value[0] = currentTerm.getCoefficient();
		int exp = currentTerm.getExponent() - 1;

		System.out.print("\n"+ value[0] + "\n");
		
		while(currentTerm.getNext() != null) {
			System.out.print("Inserting " + value[0] + "x^" + exp + "\n\n");
			Polynomial.insert(deriv, value[0], exp--);
			
			currentTerm = currentTerm.getNext();
			System.out.print(value[0] + " * " + x + " = ");
			value[0] = value[0]*x;
			System.out.print(value[0] + "\n"+ value[0] +  " + " +  currentTerm.getCoefficient() + " = ");
			value[0] += currentTerm.getCoefficient();
			System.out.print(value[0] + "\n");
		}
		System.out.print("Final value: " + value[0] + "\n\n");
		deriv.printEquation();
		
		currentTerm = deriv.head;
		value[1] = currentTerm.getCoefficient();
		System.out.print("\n\n");
		
		while(currentTerm.getNext() != null) {
			currentTerm = currentTerm.getNext();
			System.out.print(value[1] + " * " + x + " = ");
			value[1] = value[1]*x;
			System.out.print(value[1] + "\n"+ value[1] +  " + " +  currentTerm.getCoefficient() + " = ");
			value[1] += currentTerm.getCoefficient();
			System.out.print(value[1] + "\n");
		}
		
		System.out.print("Final value: " + value[1] + "\n\n");
		return value;
	}
	
	public static double newtonMethod(double initialPoint, double x, double y) {
		return initialPoint - (x/y);
	}
	
	public static double getError(double n, double m) {
		return Math.abs(n-m);
	}
	
	public double getInitialPoint() {
		return initialPoint;
	}

	public void setInitialPoint(double initialPoint) {
		this.initialPoint = initialPoint;
	}
}
