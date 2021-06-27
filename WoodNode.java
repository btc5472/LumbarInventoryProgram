/**********************************
Brandon Cobb
December 9, 2017

WoodNode is a node that gets placed
in an SBT. It contains all of the
Wood information along with the
normal Node attributes
**********************************/

class WoodNode{
	
	// Attributes
	WoodNode left, right;
	
	// Info
	int quantity = 0, height = 0;
	Fraction length;
	
	public WoodNode(WoodNode WN){
		left =  null; right = null;
		quantity = WN.quantity; height = 0;
		length = new Fraction(WN.length.feet, WN.length.num, WN.length.dnum);
	}	
	
	public WoodNode(Fraction frac, int q){
		left = null; right = null;
		length = new Fraction(frac.feet, frac.inches);
		quantity = q;
		height = 0;
	}

	public String toString(){
		return "Feet:" + length.feet + "  Inches:" + length.inches + "  quantity:" + quantity + "  Height:" + height; 
	}
}