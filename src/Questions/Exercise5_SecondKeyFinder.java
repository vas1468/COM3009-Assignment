package Questions;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.xml.bind.DatatypeConverter;

public class Exercise5_SecondKeyFinder {

	public static ArrayList<Character> createArrayList(Character ... elements) {
		  ArrayList<Character> list = new ArrayList<Character>(); 
		  for (Character element : elements) {
		    list.add(element);
		  }
		  return list;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {		
		String checkText = "";
		ArrayList<Character> characters = createArrayList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
				'c', 'd', 'e', 'f'); 

		// Generate all combinations for decryption
				for (Character i : characters) {
		            for (Character j : characters) {
		                for (Character k : characters) {
		                    for (Character l : characters) {
		                    	for (Character m : characters) {
		    	                    for (Character n : characters) {
		    	                    	String secondKey = "68c7401510" + i + j + k + l + m + n;
			
		    	                    	String ciphertext = new String("bc14f8d3095fd80b0990d0ca1de5128928870674f38c99c3");

		    	                    	Exercise5_DESCipher decCiph = new Exercise5_DESCipher(DatatypeConverter.parseHexBinary(secondKey));

		    	                    	byte[] cipherText = decCiph.decrypt(DatatypeConverter.parseHexBinary(ciphertext));
	
		    	                    	String decryptedText = new String(cipherText);
								
		    	                    	for (int x =decryptedText.length()-8; x < decryptedText.length(); x++) {// try to find the padding (3 most common) 
		    	                    		checkText += decryptedText.charAt(x);
														}
		    	                    	if (checkText.matches("")){//check for padding
		    	                    		System.out.println(("Key: " + secondKey));
		    	                    		System.out.println("Decrypted Ciphertext: "	+ new String(cipherText));
		    	                    		
		    	                    	}
		    	                    	checkText = "";
		    	                    }
							}
						}
					}
				}
			}
		}
}
