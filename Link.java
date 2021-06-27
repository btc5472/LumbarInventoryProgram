/****************
Brandon Cobb
October 14, 2017
Comp 182
****************/

class Link{

	// Attributes
	int value;
	Link next;

	//Cconstuctor
	Link (int v){
		value = v;
		next = null;
	}
	
	// Put Node list into a string and display it
	public String toString(){
		Link t = this;
		String s = "";

		while(t != null){
			s = s + " " + t.value;
			t = t.next;
		}
		return s.trim();
	}
}