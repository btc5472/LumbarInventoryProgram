private Node delete(Fraction x, Node r){
		if(r == null)										// If tree doesnt exist return null
			return null;
			
		if(x.compareLess(r.data)){							// If the node to delete is on the left then set new r to r.left, restart method
			r.right = delete(x, r.right);	
		}else if(x.compareGreater(r.data)){					// If the node to delete is on the right then set new r to r.right, restart method
			r.left = delete(x, r.left);
		}else{												// If node to delete is found (both children !> !< node to delete)
			if(r.left == null || r.right == null){
				Node temp = null;
				if(r.left == null) temp = r.right;
				else temp = r.left;							// Which child non empty?
				
				if(temp == null){							// No child case
					temp = r; r = null;
				}else{
					r = temp; 								// Copy the contents of the non-empty child
				}	
			}else{
				//Node with 2 children get the inorder successor (smallest in the right subtree)
				Node temp = minValueNode(r.right);
				
				// copy the inorder successors data to this node
				r.data = temp.length;
				
				// delete the inorder successor
				r.right = delete(temp.length, r.right);
				
			}
		}
		
		//if the tree had only one node then return
		if(r == null)
			return r;
		
		//REBALANCE
		//Update Height
		r.height = max(height(r.left), height(r.right)) + 1;
		//rebalance
		int balance = getBalance(r);
		
		if(balance > 2){
			if (getBalance(r.left) >= 0)
				return rotateWithLeftChild(r);
			if(getBalance(r.left) < 0)
				return doubleWithLeftChild(r);
		}
		
		if (balance < -3){
			if(getBalance(r.right) == 0)
				return rotateWithRightChild(r);
			if(getBalance(r.right) > 0)
				return doubleWithRightChild(r);
		}
		
		return r;
	}