package puzzle;

import java.io.*;
import java.util.*;

public class Search {
	public static void main(String args[]) {
		int i, j, k, l;
		
		String s;						//temp string
		String words_line = null;
		
		int puzzle_line_checker = 0;	// for puzzle array
		int words_checker = 0;			// for words array
		
		char puzzle_map[][];			// puzzle map
		char word[][];					// words list
		
		int word_length[];
		int int_tmp;					//temp int
				
		
		
		// line checker for knowing size of puzzle===================================================================
		try (BufferedReader br = new BufferedReader(new FileReader("src/input.txt")) ){
			while((s = br.readLine()) != null) {
				puzzle_line_checker++;
				
				words_line = s;
			}
			//System.out.println(s );
			
			//except two bottom line
			puzzle_line_checker -= 2;
		
		} catch(IOException exc) {
			System.out.println("I/O Error: " + exc);
		}
		
		//System.out.println("puzzle_line_checker: " + puzzle_line_checker);
		//System.out.println("Words list: " + words_line);		
		

		
		// set Words list===================================================================
		StringTokenizer st = new StringTokenizer(words_line, " ", false);
		words_checker = st.countTokens();
		
		//System.out.println("words_checker: " + words_checker);
		
		word = new char[words_checker][];
		word_length = new int[words_checker];
		
		i = 0;
		while(st.hasMoreTokens() ){
			s = st.nextToken();
			
			word[i] = s.toCharArray();
			
			word_length[i] = s.length();
			
			//System.out.println("s: " + s + " st: " + st + "s.length: " + s.length());
			//System.out.println("word_length: " + word_length[i]);
			
			i++;
		}
		
		
		
		// set puzzle's 2d array by line===================================================================
		puzzle_map = new char[puzzle_line_checker][puzzle_line_checker];
		
		// fill out the puzzle on memory		
		try (BufferedReader br = new BufferedReader(new FileReader("src/input.txt")) ){
			for(i=0; i < puzzle_line_checker; i++){
				s = br.readLine();
				//System.out.println(s);
											
				// mapping the puzzle
				for(j=0; j < puzzle_line_checker; j++ ){
					puzzle_map[i][j]= s.charAt(j+j);
				}
				
			}
						
		} catch(IOException exc) {
			System.out.println("I/O Error: " + exc);
		}
		
			
		
		/*System.out.println("== Check for puzzle_map array");
		for(i=0; i < puzzle_line_checker; i++ ){
			for(j=0; j < puzzle_line_checker; j++ ){
				System.out.print(puzzle_map[i][j] + " ");
			}
			System.out.println("");
		}*/
		
		
		
		
		// ====================== search ===========================================
		
		for(i=0; i < words_checker; i++){
			for(j=0; j < puzzle_line_checker; j++){
				for(k=0; k < puzzle_line_checker; k++){
					
					if(word[i][0] == puzzle_map[j][k]){
						//System.out.println("match word & puzzle: " + word[i][0] + " === i: " + i + ", j: " + j + ", k: " + k);
												
						int_tmp = word_length[i];
						//System.out.println("int_tmp = word_length[i]: " + int_tmp + ", " + word_length[i]);
						
						// 1) row check
						if((k+int_tmp) <= puzzle_line_checker){
							//System.out.println("row check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j][k+l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j][k+l+1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j+1) + "," + (k+l) + ")" );
								//i++;
								}
							}
						}
						
						// 5) reverse-row check
						if((k-int_tmp+1) >= 0){
							//System.out.println("reverse-row check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j][k-l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j][k-l-1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j+1) + "," + (k-l+2) + ")" );
								}
							}
						}
											
						
						// 2) column check
						if((j+int_tmp) <= puzzle_line_checker){
							//System.out.println("column check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j+l][k] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j+l+1][k] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j+l) + "," + (k+1) + ")" );
								}
							}
						}
						
						// 6) reverse-column check
						if((j-int_tmp+1) >= 0){
							//System.out.println("reverse-column check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j-l][k] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j-l-1][k] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j-l+2) + "," + (k+1) + ")" );
								}
							}
						}
						
						
						
						// 3) diagonal(\) check
						if(((j+int_tmp) <= puzzle_line_checker) && ((k+int_tmp) <= puzzle_line_checker) ){
							//System.out.println("diagonal(left-up to right-bottom) check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j+l][k+l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j+l+1][k+l+1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j+l) + "," + (k+l) + ")" );
								}
							}
						}
						
						// 7) reverse-diagonal(\) check
						if(((j-int_tmp+1) >= 0) && ((k-int_tmp+1) >= 0) ){
							//System.out.println("reverse-diagonal(left-up to right-bottom) check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j-l][k-l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j-l-1][k-l-1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j-l+2) + "," + (k-l+2) + ")" );
								}
							}
						}
						
						
						// 4) diagonal(/) check
						if(((j+int_tmp) <= puzzle_line_checker) && ((k-int_tmp+1) >= 0) ){
							//System.out.println("diagonal(/) check");
							for(l=1; l < int_tmp; l++){
								if(puzzle_map[j+l][k-l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j+l+1][k-l-1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j+l) + "," + (k-l+2) + ")" );
								}
							}
						}
			
						// 8) reverse-diagonal(/) check
						if(((j-int_tmp+1) >= 0) && ((k+int_tmp) <= puzzle_line_checker) ){
							//System.out.println("reverse-diagonal(/) check");
							for(l=1; l <= int_tmp; l++){
								if(puzzle_map[j-l][k+l] != word[i][l]){
									//System.out.println("row fail: " + l);
									break;
								}
							
							//l++;
							//System.out.println("== for after int l: " + l);
							if(puzzle_map[j-l-1][k+l+1] == word[i][l+1]){
								for(l=0; l < int_tmp; l++){
									System.out.print(word[i][l]);
								}
								//System.out.println("== i, j, k, l: " + i + ", " + j + ", " + ", " + k + ", " + l);
								System.out.println(": (" + (j+1) + "," + (k+1) + ") - (" + (j-l+2) + "," + (k+l) + ")" );
								}
							}
						}
						
						
						
					}
					
					
				}
			}
			
					
		}
		
		
		
		
		
	}
}


