/****************
Brandon Cobb
October 14, 2017
Garbage collection for unused nodes
****************/

class StackPlus extends Stack{

	// Attribute
	LL garbage;
	int totalLinksCreated, linksUsed;
	
	// Constructor
	StackPlus(){
		stk = new LL();
		garbage = new LL();
	}

	// Methods from here on down
	// Push a Link(Node) into the stack
	void push(int v){
		if (garbage.head == null){ // If garbage is empty then create a new Link for stk
			stk.insertHead(v);
			totalLinksCreated++;
			linksUsed++;
		}
		else{	// If garbage isnt empty then pop a Link from garbage and use that link for stk
			Link temp = garbage.popLink();
			temp.value = v;
			stk.insertHead(temp);
			linksUsed++;
		}
	}

	// Pop a link from stk and put it into garbage
	int pop(){
		Link poppedHead = stk.popLink();
		int poppedValue = poppedHead.value;
		poppedHead.next = garbage.head;
		garbage.head = poppedHead;
		return poppedValue; 
	}
	
	// call toString in LL, prints the entire stk
	public String toString(){
		if (garbage.head != null){
			System.out.println("Garbage:" + garbage.toString());
		}
		
		return stk.toString();
	}
}