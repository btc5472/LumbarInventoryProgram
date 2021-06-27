/*********************************************
Brandon Cobb
December 9, 2017

This class reads the information in the given
string and puts it into an array containing
all of the information that has been read
*********************************************/
public class StringClass {

	String crossSection = "";
	String lengthFeetStr = "";
	String numStr = "";
	String dnumStr = "";
	int lengthFeetInt = 0;
	int quantity = 0;
	
	// Read one line of file, return the info from current line as ints or Strings
	public WoodNode[] readString(String line){
			
		//Split the string of the current line into cross Section info as [0] & length/quantity info as []>0
		line = line.trim();
		String[] lineInfoArray = StringSplit(line);								// This now holds the all the info in one line as substrings
		
		crossSection = lineInfoArray[0];										// Put cross section info into its String
		
		WoodNode[] WoodNodeArray = new WoodNode[lineInfoArray.length - 1];
		
		//Put each Length/Quantity info into an int
		for(int v = 1; v < lineInfoArray.length; v++){							// Read each length and quantity String and
			
			// Clear the values
			int num = 0;
			int dnum = 1;
			int lengthFeetInt = 0;
			int quantity = 0;
			String crossSection = "";
			String lengthFeetStr = "";
			String numStr = "";
			String dnumStr = "";
																				
			String[] lengthQuantityStrArray = lineInfoArray[v].split("\\(");	// lengthQuantityStrArray[0] = length & [1] = quantity
			
			// Read length info
			char[] lengthChars = lengthQuantityStrArray[0].toCharArray();		// Convert length info into char array
			
			int c = 0;			// Initialize the element index for lengthChars
			
			for(c = 0; c < lengthChars.length && Character.isDigit(lengthChars[c]); c++){
				lengthFeetStr += lengthChars[c];										// As long as there are still # then put them into lengthFeetStr						         	
			}

			lengthFeetInt = (Integer.parseInt(lengthFeetStr)); 					// Transform lengthQuantityStrArray[0] into length int
			
			
			for(; c != lengthChars.length && !Character.isDigit(lengthChars[c]); c++){	// Keep moving until it reaches a number or end of lengthChars
			}
			
			if(c != lengthChars.length && Character.isDigit(lengthChars[c])){	// If its == digit 
				while(Character.isDigit(lengthChars[c])){						// then record fraction numerator into numStr
					numStr += lengthChars[c];
					c++;
				}
				
				if(lengthChars[c] == '/'){													// If there is a fraction
					c++;																	// then pass up the /
					
					while(Character.isDigit(lengthChars[c]) && c != lengthChars.length){	// and record the fraction denomenator into dnumStr
						dnumStr += lengthChars[c];
						c++;
					}
				}
			}
			
			// convert dnumStr, numStr, and the quantity info into ints
			if(numStr != "")
				num = Integer.parseInt(numStr);
			if(dnumStr != "")
				dnum = Integer.parseInt(dnumStr);
			
			// Convert quantity String to int
			quantity = Integer.parseInt(lengthQuantityStrArray[1]);
			
			// Process wood length info(turn it into a Fraction object
			Fraction frac = new Fraction(lengthFeetInt, num, dnum);
		
			// Put the fraction obj length, and quantity into a WoodNode obj
			WoodNode WN = new WoodNode(frac, quantity);
			
			// Store the WoodNode objects into an array
			WoodNodeArray[v-1] = WN;
		}//for
		return WoodNodeArray;
	}//method

	// Obtain the cross Section from the crossSection String and return it as an array of ints with 2 elements
	public int[] getCrossSection(){
		char[] crossSectionChars = crossSection.toCharArray();
		int[] crossSectionArray = new int[2];
	
		if(crossSection == ""){
			System.out.println("crossSection is empty, cant continue program");
			System.exit(0);
		}else{
			crossSectionArray[0] = Character.getNumericValue(crossSectionChars[0]);
			crossSectionArray[1] = Character.getNumericValue(crossSectionChars[2]);
		}
		
		return crossSectionArray;
	}

	// Split String into String[]
	// Frist element contains the Cross Section info
	// The rest of the elements contain Length/Quantity info
	private String[] StringSplit(String line){
		String[] strArray = line.split(" ",2);						// Separate cross section from rest of string
		String[] lengthQuantityStr = strArray[1].split("\\)");		// Divide up the wood length and quantity strings
		String[] strArray2 = new String[lengthQuantityStr.length + 1];// This is the returned String with
																	  //cross section & wood length/quantity info
		for(int z = 0; z < lengthQuantityStr.length; z++){			// Trim all the wood lengthQuantityStr
			lengthQuantityStr[z] = lengthQuantityStr[z].trim();
		}
	
		strArray2[0] = strArray[0];									// Put all substrings into strArray2
		for(int x = 1; x < strArray2.length; x++){			
			strArray2[x] = lengthQuantityStr[x-1];
		}

		return (strArray2);
	}
}
