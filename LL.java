/****************
Brandon Cobb
October 14, 2017
Comp 182
****************/

class LL{

	// Attributes
	Link head;

	// Constuctor
	LL(){
		head = null;
	}

	// Methods from this point on
	// Create new link obj then call the other insertHead method
	public void insertHead(int v){
		Link temp = new Link(v);
		insertHead(temp);
	}

	// Make the new link next = the last link created, then set head = new link
	public void insertHead(Link temp){
		temp.next = head;
		head = temp;
	}
	
	// pop the most recently added Link from a Linked List and return popped head link
	Link popLink(){
		Link poppedHead = head;
		head = head.next;
		return poppedHead;
	}
	
	// pop the most recently added Link from a Linked List and return value in the popped head link
	public int pop(){
		int poppedValue = head.value;
		head = head.next;
		return poppedValue;
	}
	
	public String toString(){
		Link t = this.head;
		String s = "";

		while(t != null){
			s = s + " " + t.value;
			t = t.next;
		}
		return s.trim();
	}
}