package Questions;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.xml.bind.DatatypeConverter;
import Questions.Exercise5_DESCipher;

public class Exercise5_FirstKeyAndPlainFinder {
	public static ArrayList<Character> createArrayList(Character ... elements) {
		  ArrayList<Character> list = new ArrayList<Character>(); 
		  for (Character element : elements) {
		    list.add(element);
		  }
		  return list;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String checkPlain = "";
		
		ArrayList<Character> characters = createArrayList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
				'c', 'd', 'e', 'f'); 

		// Compute all combinations.
		for (Character i : characters) {
            for (Character j : characters) {
                for (Character k : characters) {
                    for (Character l : characters) {
                    	for (Character m : characters) {
    	                    for (Character n : characters) {

								String firstKey = "618c914361"+ i +j + k + l + m +n;
										
								String secondKey = "68c7401510064208";

								String ciphertext = new String(
										"bc14f8d3095fd80b0990d0ca1de5128928870674f38c99c3");

								Exercise5_DESCipher decryptWithFirstKey = new Exercise5_DESCipher(
										DatatypeConverter
												.parseHexBinary(firstKey));
								Exercise5_DESCipher decryptWithSecondKey = new Exercise5_DESCipher(
										DatatypeConverter
												.parseHexBinary(secondKey));

								byte[] cipherText = decryptWithSecondKey.decrypt(DatatypeConverter
										.parseHexBinary(ciphertext));

								byte[] plainText = decryptWithFirstKey.decrypt(cipherText);
								checkPlain = "";
								
								String finalPlain = new String(plainText);
								for (int x = 0; x < 6; x++) {//check the decryption
									checkPlain += finalPlain.charAt(x);
								}
								if (checkPlain.matches("[a-z]*")) {
									System.out.println(("First Key: "+ firstKey));
									System.out.println(("Second Key: "+ secondKey));
									System.out.println("Decrypted Ciphertext (ascii): "+ new String(plainText));
								}
							}
						}
					}
				}
			}
		}
	}
}
