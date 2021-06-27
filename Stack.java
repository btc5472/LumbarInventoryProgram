/****************
Brandon Cobb
October 14, 2017
Comp 182
****************/

class Stack{

	// Attribute
	LL stk;

	// Constructor
	Stack(){
		stk = new LL();
	}

	// Methods from this point on
	// call toString in LL
	public String toString(){
		return stk.toString();
	}

	// insert a Link between the current head and the rest of the Links in the LL stk object
	void push(int v){
		stk.insertHead(v);
	}

	// Remove the most recently added Link in the LL stk object, return int
	int pop(){
		return stk.pop();
	}

	// Remove the most recently added Link in the LL stk object, return Link
	Link popLink(){
		return stk.popLink();
	}
}