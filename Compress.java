
/*Author: Neeraja Murali Dharan 
 *File Name: Compress.java
 *Last Modified: 21 October 2015
 *Description: Contains the main method
 *Input: consists of a file 
 *Output: a compressed file with extension .zzz
 */
import java.io.*;

public class Compress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// creates a Dictionary object of specified prime number size
		Dictionary dictionary = new Dictionary(8139);
		try {

			// stores characters corresponding to ASCII values of 0-255 in
			// Dictionary
			for (int i = 0; i < 256; i++) {
				String key = new String();
				key = key + (char) i;
				dictionary.insert(new DictEntry(key, i));
			}
			// Outputs exception message if initializing the dictionary was not
			// successful
		} catch (DictionaryException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to initalize the dictionary");
			return;
		}
		if (args.length>1){
			System.out.println("Too Many Arguments");
			return;
		}
		else{
			// BufferedInputStream is used to read the file
			// BufferedOutputStream is used to write to output file
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			String input_string = new String();
	
			// opens the input file passed in as argument
			try {
				in = new BufferedInputStream(new FileInputStream(args[0]));
	
				// throws exception if file cannot be opened
			} catch (FileNotFoundException e) {
				System.out.println("Error:The input file was not found");
			}
	
			// Creates file into output file with the extension .zzz
			try {
				out = new BufferedOutputStream(new FileOutputStream(args[0] + ".zzz"));
				// throws exception if output file cannot be created
			} catch (FileNotFoundException e) {
				System.out.println("Error: Output file cannot be created");
			}
	
			// Opens input file and reads contents one byte at a time, appending to
			// a string
			try {
				for (int temp = 0; (temp = in.read()) != -1; temp++) {
					input_string = input_string + (char) temp;
				}
				// throws exception if input cannot be read
			} catch (IOException e) {
				System.out.println("Input could not be read from file");
			}
	
			int index = 0;// used to go through the string of characters
			int size = input_string.length();
	
			// checks if there are still characters to be read from string
			while (index < size) {
	
				String p = new String();
				p = p + input_string.charAt(index);// reads first byte from input string and stores it into variable
				String found = new String();
				boolean check = true;
	
				// checks if the longest sequence of strings has been obtained and
				// check is true
				while ((dictionary.find(p) != null) && (check)) {
					found = p;// holds sequence already stored in dictionary
	
					// while the index is not the last character in the string
					if (index < size - 1) {
						p = p + input_string.charAt(++index);// appends character at index to string
					}
	
					// if the index is the element,check is set to false to
					// terminate loop
					else {
						index++;// increments index value to terminate outer loop
						check = false;// check is set to false to terminate inner loop
					}
				}
	
				// writes the string sequence already stored in the dictionary to
				// output file
				try {
					MyOutput write_out = new MyOutput();
					write_out.output(dictionary.find(found).getCode(), out);
					// throws exception if file cannot be written
				} catch (IOException e) {
					System.out.println("The output could not be written to a file");
				}
	
				// if the number of elements is less than 4096 and all input string
				// is not processed
				if ((dictionary.numElements() < 4096) && (index < size)) {
					try {
						// creates a dictionary entry object and stores the longest
						// string not in the dictionary into the dictionary
						DictEntry entry = new DictEntry(p, dictionary.numElements());
						dictionary.insert(entry);
					} catch (DictionaryException e) {
						System.out.println("Element could not be added to the dictionary");
					}
				}
			}
			MyOutput output = new MyOutput();
	
			// closes input and output bufferr
			try {
				output.flush(out);
				in.close();
				out.close();
	
				// throws exception if there in an error in closing the files
			} catch (IOException e) {
				System.out.println("Error in closing file");
			}
		}

	}

}
