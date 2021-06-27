/***************************************
Brandon Cobb
December 9, 2017

Self Balanced Tree
Each node will be a different length
of wood and its corresponding quantity
***************************************/

class SBT{
	
	// Attribute
	private WoodNode root;
	
	public SBT(){
		root = null;
	}

	
	
	// Insert a Node(WoodNode) into a tree
	public void insert(WoodNode x){
		root = insert(x, root);
	}
	
	// Insert a Node(WoodNode) into a tree
	private WoodNode insert(WoodNode x, WoodNode t){
		if(t == null){
			t = new WoodNode(x);
		}else if(x.length.totalInches < t.length.totalInches){
			t.left = insert(x, t.left);
			if(height(t.left) - height(t.right) == 2)
				if(x.length.totalInches < t.left.length.totalInches) t = rotateWithLeftChild(t);
				else t = doubleWithLeftChild(t);
		}else if(x.length.totalInches > t.length.totalInches){
			t.right = insert(x, t.right);
			if(height(t.right) - height(t.left) == 2)
				if(x.length.totalInches > t.right.length.totalInches) t = rotateWithRightChild(t);
				else t = doubleWithRightChild(t);
		}else{
			t.quantity = t.quantity + x.quantity;
		}		
		
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}
	
	// Delete a node from a tree
	public void delete(Fraction x) {
		root = delete(x, root);
	}
	
	// Delete a node from a tree
	private WoodNode delete(Fraction x, WoodNode r){
		if(r == null)									// If tree doesnt exist return null
			return null;
		
		if(x.x_GreaterThan(r.length)){					// If node to delete(x) is to the left of root(r) then r = r.left, call method again
			r.right = delete(x, r.right);	
		}else if(x.x_LessThan(r.length)){				// If node to delete(x) is to the right of root(r) then r = r.right, call method again
			r.left = delete(x, r.left);
		}else{											// If node to delete(x) is found
			if(r.left == null || r.right == null){		// If node only has one child
				WoodNode temp = null;
				if(temp == r.left)						// If left child is empty
					temp = r.right;						// then temp = right child
				else
					temp = r.left;						
				
				if(temp == null){						// If right child is empty
					temp = r;
					r = null;
				}else{									// Else copy the temp node into node r
					r = temp; 							
				}	
			}else{										// If node has 2 children
				WoodNode temp = minValueNode(r.right);	// Find the node with the smallest length within r's right subtree
				r.length = temp.length;					// Copy the minValueNode's length into r's length
				r.right = delete(temp.length, r.right);	// Delete the minValueNode
			}
		}
		
		if(r == null)									// If tree has only one node then return r
			return r;
		
		// Rebalance operation if needed
		r.height = max(height(r.left), height(r.right)) + 1;	// Update height of r
		int balance = getBalance(r);							// Set balance equal to the difference in # of right children & left children
																
		if(balance > 1){										// If the difference in children is greater than 1, then rebalance the tree
			if (getBalance(r.left) >= 0)
				return rotateWithLeftChild(r);
			if(getBalance(r.left) < 0)
				return doubleWithLeftChild(r);
		}
		
		if(balance < -3){
			if(getBalance(r.right) == 0)
				return rotateWithRightChild(r);
			if(getBalance(r.right) > 0)
				return doubleWithRightChild(r);
		}
		
		return r;
	}
	
	// Find the node with the smallest length
	private WoodNode minValueNode(WoodNode m){
		if(m == null)                     		// If there is no right child of the node to delete then return
			return m;
      
		while(m.left != null){					// While there is still a smaller node(a left child exists)
			m = m.left;							// Set m = its left child
		}
      
		return m;
	}
	
	// Find the difference in heights between the given nodes right and left children
	private int getBalance(WoodNode r){
      int leftHeight;
      int rightHeight;
      
		if(r.left == null)						// If there is no left child then its height = 0
			leftHeight = 0;
		else									// If there is a left child then get its height
			leftHeight = height(r.left) + 1;	// +1 because the height of the current node doesnt count itself, but we need it to
         
		if(r.right == null)						// If there is no right child then its height = 0
			rightHeight = 0;
		else									// If there is a right child then get its height
			rightHeight = height(r.right) + 1;	// +1 because the height of the current node doesnt count itself, but we need it to
     
		int balance = leftHeight - rightHeight;	// balance = the difference in heights of the 2 children
		
		return balance;
	}
		
	// Find the balance of the current node
	public int height(WoodNode root){
		 return root == null ? - 1 : root.height;
	}
	
	// Find the max # of children this node has
	private int max(int lhs, int rhs){
		return lhs > rhs ? lhs : rhs;
	}

	// BALANCING OPERATIONS
	private WoodNode rotateWithRightChild(WoodNode k1){
		System.out.println("\nRotate " + k1 + " With Right Child");
		WoodNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;		// set height of k1 depending on the children it currently has
		k2.height = max(height(k2.left), height(k1.right)) + 1;		// set height of k2 depending on the children it currently has
		return k2;
	}
	
	private WoodNode rotateWithLeftChild(WoodNode k2){
		System.out.println("\nRotate " + k2 + " With Left Child");
		WoodNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}
	
	private WoodNode doubleWithRightChild(WoodNode k1){
		System.out.println("\nRotate " + k1 + " With Right Child");
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}
	
	private WoodNode doubleWithLeftChild(WoodNode k3){
		System.out.println("\nRotate " + k3 + " With Left Child");
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	
	public boolean hasNodes(){
		if(root == null)
			return false;
		else
			return true;
	}

	// Display the tree in order (left child, root, then right child)
	public void inOrder(){
		inorder(root);
	}
	
	// Display the tree in order (left child, root, then right child)
	private void inorder(WoodNode r){
		if(r != null){
			inorder(r.left);
			System.out.println(r.length.feet + "ft " + r.length.inches + "in" + "(" + r.quantity + ")" + "|" + r.height + " ");
			inorder(r.right);
		}
	}

	// Display the tree in order (left child, root, then right child)
	public String toString(){
		inorder(root);
		return "";
	}
}