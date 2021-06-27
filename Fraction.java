/***************************************
Brandon Cobb
December 9, 2017

This class takes feet and inches (they
can be numbers or fractions), then
simplifies & processes the fraction to
make sure its valid. 
***************************************/

class Fraction {
       	
	int num;
	int dnum;
	int feet;
	int inches;
	int totalInches;
	

	// If a fraction numerator & denomenator are passed 
	Fraction(int f, int n, int d){
		num = n; dnum = d;
		setFeetInches(f, n, d);
	}
	
	// If fraction has already been processed then just set feet and inches
	Fraction(int f, int i){
		feet = f;
		inches = i;
		num = inches;
		dnum = 1;
      totalInches = f * 12 + inches;
	}
	
	
	
	
	public void setFeetInches(int f, int n, int d){
		int gcd = CobbGcd.findGcd (n,d);
		int twelveInches = 12;
		
		if (d < 0 || n < 0){	// divide by negative?		
			System.out.println("Cant have a negative number");
		}
		
		/*// Simplify the fraction and prevent n/0
		if(n != 0 && d != 1){
			n = n/gcd;
			d = d/gcd;
			num = n/gcd;
			dnum = d/gcd;
		}*/
		
		if(d == 0){
			System.out.println("Cannot Divide by zero in class Fraction");
			System.exit(0);
		}
	
		if(n < d){									// If given a proper fraction, then set inches and feet
			inches = (n * twelveInches) / d;
			feet = f;
			totalInches = feet * twelveInches + inches;
		}else if(n > d){							// If given an improper fraction, then add to feet and then set inches
			if(d == 1){
				inches = n;
				feet = f + inches/twelveInches;			// Add to feet if n/d >= 1
				inches = inches%twelveInches;			// Set n to be a rational fraction
			}else{
				inches = (num * 12) / dnum;
				feet = f + inches/twelveInches;			// Add to feet if n/d >= 1
				inches = inches%twelveInches;			// Set n to be a rational fraction
			}
      
			totalInches = feet * twelveInches + inches;
		}else if(n == d){
         inches = 1; // Because a/a = 1
         feet = f;
         totalInches = feet * 12 + inches;
      }
	}
	
	public boolean x_GreaterThan(Fraction r){
		if((totalInches) > (r.totalInches)){
			return true;
		}
		else /*if(a * d > b * c)*/{
			return false;
		}
	}
	
	public boolean x_LessThan(Fraction r){
		if(totalInches < r.totalInches){
			return true;
		}
		else /*if(a * d > b * c)*/{
			return false;
		}
	}
   	
	public String toString(){
		return "Feet:" + feet + "  Inches:" + inches + "totalInches" + totalInches;
	}
}

// May not need num and dnum as globals
// May not need to simplify fraction in setFeetInches