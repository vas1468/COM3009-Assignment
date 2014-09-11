/**
 * 
 */
package Questions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

/**
 * @author vk00050
 * 
 */
public class Exercise6_PictureDecryption {
	public static void main(String[] args) throws IOException {

		byte[] keybytes;
		byte[] iv;

		keybytes = DatatypeConverter.parseHexBinary("1234567890ABCDEF");
		System.out.println("Picture Decryption\n Key: 1234567890ABCDEF");

		iv = DatatypeConverter.parseHexBinary("0000000000000000");
		System.out.println("IV: 0000000000000000");

		// To read the picture hex file line by line
		FileReader flread = new FileReader("files/pyramid.txt");
		BufferedReader bufread = new BufferedReader(flread);
		String plaintext = "";
		try {
			String ln;
			// read line by line
			while ((ln = bufread.readLine()) != null) {
				plaintext += ln;
			}
			flread.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// creating a des/cbc object
		Exercise6_DESCBC desCbc = new Exercise6_DESCBC();
		// set the keybytes and the iv
		desCbc.setDESCipher(keybytes, iv);

		//byte[] firstEncrypt = desCbc.encrypt(plaintext.getBytes());
		//byte[] firstDecrypt = desCbc.decrypt(firstEncrypt);

		byte[] secondDecrypt = desCbc.decrypt(desCbc.decrypt(desCbc.encrypt(plaintext.getBytes())));

		System.out.println(DatatypeConverter.printHexBinary(secondDecrypt));

	}
}
