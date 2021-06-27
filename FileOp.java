/*********************************************
Brandon Cobb
December 9, 2017
File Operations
*********************************************/

import java.util.Scanner;
import java.io.File;
import java.lang.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileOp {
	
	public static void OpenFile(){
		System.out.println("OpenFile");
		File file = new File("order.txt");		// Create new File obj with the file called order.txt
		
		if(file.exists())								// If order.txt exists then
			System.out.println("File already exists.");	// dont do anything
		else{											// else create a new file called order.txt
			try{
				file.createNewFile();
				System.out.println("try, file created");
			}
			catch(Exception e){							// Catch Exception if we try to create a new file that already exists
				System.out.println("File already exists!");
			}
		}
	}
	
	public String ReadLine(){
		System.out.println("ReadFile");
		
		String line = "";
		
		/*// Store entire file into one long string called text
		try{
			FileReader fr = new FileReader("order.txt");		// Create a FileReader obj called fr that contains order.txt
			BufferedReader reader = new BufferedReader(fr);		// Create BufferedReader obj with fr that allows the file to be read
			
			String text = "";
			String line = reader.readLine();					// Read one line of the file and store it into line
			while(line != null){								// While the file still has stuff in it...
				text += line;									// Store String line into String text
				line = reader.readLine();						// Overwrite String line with the next line in the file
			}
			
			System.out.println(text);
			
			reader.close();
		}
		catch(Exception e2){
			System.out.println("e2");
		}*/
		
		// Store one line into String line
		try{
			FileReader fr = new FileReader("order.txt");		// Create a FileReader obj called fr that contains order.txt
			BufferedReader reader = new BufferedReader(fr);		// Create BufferedReader obj with fr that allows the file to be read	
			
			line = reader.readLine();							// Read line in file and store it into line
		
			reader.close();
		}
		catch(Exception e2){
			System.out.println("e2");
		}
		
		return(line);
	}
}

/* Old Information searcher
char[] lineChars = line.toCharArray();
		
		for(int t = 0; t <= line.length(); t++){
			System.out.println("\nIn for");
			System.out.println("lineChars pos in for:" + lineChars[t]);
			System.out.println("t:" + t);
			//System.out.println("line char length" + line.length() + "\n");
			
			
			Check crossSection
			if(crossSectionRead == false){
				System.out.println("\nWhile crossSectionRead = false");
				while(lineChars[t] != space){
					System.out.println("\nWhile there is still crossSection to read");
					crossSection += lineChars[t];
					t++;					// Move lineChars[t] over
				}
				crossSectionRead = true;
				System.out.println("Final crossSection:" + crossSection + "\n");
			}
			
			
			// Check woodLength and woodQuantity
			if(lineChars[t] == space){			// If space is next
				System.out.println("Check woodLength & woodQuantity");
				System.out.println("lineChars pos in if:" + lineChars[t]);
				while (lineChars[t] == space){	// while there are still spaces, keep moving lineChars[t]
					t++;
					System.out.println("space, therefore t++");
				}
				
				// Check woodLength
				while(lineChars[t] != '('){								// While there are still #'s for woodLength
					System.out.println("\nlineChars:" + lineChars[t]);
				
					while(lineChars[t] >= 48 && lineChars[t] <= 57){	// Read woodLength ft number
						woodLength += lineChars[t];
						System.out.println("woodLength write:" + woodLength);
						t++;
					}
						
					if(lineChars[t] == '\''){							// If there is a ' after the #
						woodLength += "ft";								// insert ft after the first number has been read
						System.out.println("write ft");
						t++;
					}
					
					if(lineChars[t] == space || (lineChars[t] >= 48 && lineChars[t] <= 57)){	// If space or # then its a fraction
						while (lineChars[t] == space){			// while there are still spaces, keep moving lineChars[t]
							t++;
							System.out.println("space, therefore t++");
						}
						
						while(lineChars[t] >= 48 && lineChars[t] <= 57){	// Read Fraction numerator num
							woodLengthFraction += lineChars[t];
							t++;
						}
						
						if(lineChars[t] == '\"'){							// If " is encountered
							if(woodLengthFraction >= 12){					// Check if the inches number is >= 12
																			// If inches >= 12 then send it to fraction class
																			// Fraction class will return a whole fraction as String
							}
							
							// woodLength += WoodLengthFraction;			// Merge strings
							woodLength += "in";								// Write in
							System.out.println("write in");
							t++;
						}
						else if(lineChars[t] == '/'){						// If fraction / is encountered
							woodLengthFraction += lineChars[t];	
							t++;											// Move to dum
							while(lineChars[t] >= 48 && lineChars[t] <= 57){// Read Fraction denomenator dnum
								woodLengthFraction += lineChars[t];
								t++;
							}
																			// Send woodLengthFraction to Fraction class
																			// Fraction will return a whole fraction as String
																			// Merge Strings
																			
						}
						
					}
					else if(lineChars[t] == '(')				// No fraction, read quantity
						break;
						
						
					if(lineChars[t] == '\''){					// Is the wood Length info in feet or inches?
						woodLength += "ft";
						System.out.println("write ft");
						t++;
					}
					else if(lineChars[t] == '\"'){
						woodLength += "in";
						System.out.println("write in");
						t++;
					}
					
					if(lineChars[t] == '/'){
						System.out.println("Fraction Encountered");
						break;
					}
					
					if(lineChars[t] == '('){
						System.out.println("( Encountered");
						break;
					}
					
					System.out.println("Final woodLength:" + woodLength);
				}
				
				// Check woodQuantity
				System.out.println("Checking woodQuantity");
				if(lineChars[t] == '('){		// If lineChars[t] is at the beginning of the quantity of wood info
					t++;
					while(lineChars[t] != ')'){
						woodQuantity += lineChars[t];
						System.out.println(lineChars[t]);
						t++;
					}
				}
				else{
					System.out.println("No ( after woodLength");
				}
					
				if(lineChars[t] == ')'){		// If I reach the end of woodQuantity then move lineChars
					System.out.println(lineChars[t] + ") encountered");
					t++;
				}
				
				System.out.println("woodQuantity:" + woodQuantity);
			}
			
			if(t == line.length()){				// If I reach the end of the line
					System.out.println("end reached" + t);
				}
			else{
				System.out.println("lineChars[t] at end:" + lineChars[t] + "\n");
			}
		}//for
*/
