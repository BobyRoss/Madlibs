import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**creates and views Madlibs
 * 
 * @author s-stojima
 *
 */
public class Main {
	

	public static void main(String[] args) throws FileNotFoundException {
		intro();
		Scanner console = new Scanner(System.in);
		//sentinel loop
		String command = "x";
		
		while(!command.equals("Q")) {
			System.out.print("(C)reate Mad Lib, (V)iew Mad Lib, (Q)uit");
			command = console.nextLine().substring(0, 1).toUpperCase();
			if(command.equals("C")) {
				create();
				Scanner inputFile = getInputFile(console);
				PrintStream outputFile = getOutputFile(console);
				createMadLib(console, inputFile, outputFile);
				
				inputFile.close();
			}else if(command.equals("V")) {
				Scanner inputFile = getInputFile(console);
				System.out.println();
				view(inputFile);
				inputFile.close();
				
			}else {
				
			}
		}
		console.close();

	}
	
	//plays a game of madlibs
	public static void createMadLib(Scanner console, Scanner input, PrintStream output) {
		while(input.hasNextLine()) {
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			while(lineScan.hasNext()){
				String token = lineScan.next();
				if(token.startsWith("<") && token.endsWith(">")) {
					token = token.substring(1, token.length()-1);
					System.out.print("Please type a " + token + " : ");
					token = console.nextLine();
				}
				output.print(token + " ");
			}
			output.println();
			lineScan.close();
		}
	}
	
	
	//Takes a scanner to the console, then gets a file name from the user and returns the file
	public static Scanner getInputFile(Scanner console) throws FileNotFoundException {
		boolean isValid = false;
		Scanner inputFile = null;
		
		System.out.println("Input file name: ");
		
		while(!isValid) {
			String fileName = console.nextLine();
			if(fileName.length() > 0){
				File f = new File("files/" + fileName);
				if(f.exists()) {
					inputFile = new Scanner(f);
					isValid = true;
				}else {
					System.out.println("File not found error. Please try again ");
				}
			}else {
				System.out.print("Blank file not allowed. Please try again: ");
			}
			
		}
		return inputFile;
		
	}
	
	//Prints the intro line
	public static void intro() {
		System.out.println("Welcome to the game of Mad Libs.");
		System.out.println("I will ask you to provide various words");
		System.out.println("and phrases to fill a story.");
		System.out.println("The results will be written to an output file");
		System.out.println("");
	}
	
	public static void create() { 
		System.out.println("sucess");
	}
	
	
	//rakes in a console, prompts the user for a file name and returns a printStream
	public static PrintStream getOutputFile(Scanner console) {
		System.out.print("Output file name: ");
		boolean validFile = false;
		PrintStream output = null;
		
		while (!validFile) {
			String fileName = console.nextLine();
			if(fileName.length() <1) {
				System.out.print("Blank files not allowed. Please try again: ");
			}else {
				try {
					File f = new File("files/" + fileName);
					output = new PrintStream(f);
					validFile = true;
				}catch(FileNotFoundException e){
					System.out.print("Unable to write to file. Please try again: ");
				}
			}
		}
		return output;
	}
	
	
	public static void view(Scanner input) {
		//read each line
		while(input.hasNextLine()) {
			//makeascanner for each line and read each word
			String line = input.nextLine();
			Scanner lineScan=new Scanner(line);
			while(lineScan.hasNext()) {
				String token = lineScan.next();
				System.out.print(token+" ");
			}
			System.out.println();
			lineScan.close();
		}
		
	}

}
