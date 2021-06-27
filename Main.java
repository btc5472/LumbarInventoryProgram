/*********************************************
Brandon Cobb
December 9, 2017

Keep track of wood going in/out of inventory
*********************************************/

import java.io.File;
import java.lang.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// OPEN FILE
		File file = new File("order.txt");				// Create new File obj with the file called order.txt
		
		if(file.exists())								// If order.txt exists then
			System.out.println("Found order.txt \nNow proceeding to read txt and process information");
		else{											// else create a new file called order.txt
			System.out.println("File order.txt doesn't exist or isnt in the correct directory. ");
			
			try{
				file.createNewFile();
				System.out.print("A new empty order.txt file has been created so the user can populate it.\n");
			}
			catch(Exception e){							// Catch Exception if we try to create a new file that already exists
				System.out.println("Cannot create a new order.txt file");
			}
		}
		
		SBT Inventory[][] = new SBT[5][10];				// 2D Array of trees containing all the wood (Inventory)
		StringClass SC = new StringClass();
		String mainStringLine = "";
		
		//Fill the inventory with empty trees
		for(int x = 0; x < 5; x++){
			for (int y = 0; y < 10; y++){	
				Inventory[x][y] = new SBT();		
			}
		}
		
		/******************************************************************************************
		My program doesnt work when there is no ' after a fraction. Example 2x4 8' 2/3(20).
		Also when there is a fraction with dnum == 0 then that will be interpreted as
		6 inches and not 6 feet. Example 6' 6/1'(14) will be 6ft 6in.
		******************************************************************************************/
		
		// READ FILE
		// Store one line into String line
		FileReader fr = new FileReader("order.txt");		// Create a FileReader obj called fr that contains order.txt
		BufferedReader reader = new BufferedReader(fr);		// Create BufferedReader obj with fr that allows the file to be read
			
		mainStringLine = reader.readLine();					// Read line in file and store it into line
		
		if(mainStringLine == null){						// If File is empty then exit
			System.out.println("File contains no information!");
			System.exit(0);
		}
		
		// PROCESS EACH FILE LINE
		while(mainStringLine != null){
			WoodNode[] WoodNodeArray = SC.readString(mainStringLine);	// WoodNodeArray contains all the Length & Quantity info in the current line
																
			int[] crossSection = SC.getCrossSection();					// Separate the crossSection from the rest of the info
	
			SBT tree = Inventory[crossSection[0] - 2][crossSection[1] - 3];		// SBT Tree object will be used to modify the current tree
			
			for(int i = 0; i < WoodNodeArray.length; i++){				// Insert nodes into the tree containing the WoodInfo length/quantity
				tree.insert(WoodNodeArray[i]);
            
				System.out.println("Tree:");
				System.out.println(tree);	
			}
			mainStringLine = reader.readLine();					// Read next line
		}
		
		reader.close();
		
		// Display all Trees with Nodes
		System.out.println("----------------Displaying all trees----------------");
		for(int x = 0; x < 5; x++){
			for (int y = 0; y < 10; y++){
				int v = x + 2;
				int w = y + 3;
				
				if(Inventory[x][y].hasNodes() == true){
					System.out.println("**********Wood with Cross Section " + v + "x" + w + "**********");
					System.out.println(Inventory[x][y]);
				}
			}
		}
		System.out.println("Trees that arent displayed are empty\n");
	}
}

/* Insert & delete test code
		System.out.println("Listing...");
		tree.inorder();
		
		Fraction frac1 = new Fraction(0, 1, 1);
		Fraction frac2 = new Fraction(0, 2, 1);
		Fraction frac3 = new Fraction(0, 3, 1);
		Fraction frac4 = new Fraction(0, 4, 1);
		Fraction frac5 = new Fraction(0, 5, 1);
		Fraction frac6 = new Fraction(0, 6, 1);
		Fraction frac7 = new Fraction(0, 7, 1);
		
		
		WoodInfo WI1 = new WoodInfo(frac1, 1);
		WoodInfo WI2 = new WoodInfo(frac2, 2);
		WoodInfo WI3 = new WoodInfo(frac3, 3);
		WoodInfo WI4 = new WoodInfo(frac4, 4);
		WoodInfo WI5 = new WoodInfo(frac5, 5);
		WoodInfo WI6 = new WoodInfo(frac6, 6);
		WoodInfo WI7 = new WoodInfo(frac7, 7);
		
		System.out.println("Inserting...");
		tree.insert(WI1);
		tree.insert(WI2);
		tree.insert(WI3);
		tree.insert(WI4);
		tree.insert(WI5);
		tree.insert(WI6);
		tree.insert(WI7);
		
		System.out.println("Listing...");
		tree.inorder();
		
		
		System.out.println("Deleting...");
		tree.delete(frac5);
		tree.delete(frac7);
		tree.delete(frac3);
		tree.delete(frac4);
		
		
		System.out.println("Listing...");
		tree.inorder();
*/
