package cunecd01_cs384_hw2;

public class Term {
	double coefficient;
	int exponent;
	Term next; 
	
	public Term(double coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
		this.next = null;
	}

	public double getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	public int getExponent() {
		return exponent;
	}
	
	public void setExponent(int exponent) {
		this.exponent = exponent;
	} 
	
	public Term getNext() {
		return next;
	}

	public void setNext(Term next) {
		this.next = next;
	}
	
}
