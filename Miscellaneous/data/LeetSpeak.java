import java.util.Scanner;

public class LeetSpeak {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please enter a sentence in English > ");
		String input = in.nextLine();
		String output = "";
		char nextChar = 0;
		
		// go through input and replace common characters
		
		for (int i = 0; i < input.length(); i++) {
			
			nextChar = input.charAt(i);
			
			switch (nextChar) {
				
				case 'o': case 'O': output += '0'; break;
				case 'i': case 'I': output += '1'; break;
				case 'z': case 'Z': output += '2'; break;
				case 'e': case 'E': output += '3'; break;
				case 'a': case 'A': output += '4'; break;
				case 's': case 'S': output += '5'; break;
				case 'g': case 'G': output += '6'; break;
				case 't': case 'T': output += '7'; break;
				case 'b': case 'B': output += '8'; break;
				case 'p': case 'P': output += '9'; break;
				default: output += nextChar;
			}
		}
		
		System.out.println("\n\nThe 1337 way to write your sentence is \"" +
							output + "\"\n\n");
	}	
}
