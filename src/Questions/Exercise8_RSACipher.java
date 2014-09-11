package Questions;

/**
 * @author vk00050
 * 
 * Because the ciphertexts reordered after the encryption as I know, I run my code several times and each 
 * time changing either the key pair or the ciphertext until all of them decrypt.
 */
import java.math.BigInteger;
import java.security.*;
import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;
import java.security.spec.*;

public class Exercise8_RSACipher {

	PrivateKey privekey;
	KeySpec RSAPrivateKeySpec;
	Cipher priveCipher;

	//Modulus of keypair 4
	private BigInteger mod = new BigInteger(
			"978698097892583443479422472153239370213333505645278946567808608449809261272986156217527555852" +
			"6016575963543252846488631194383549452872551405348698472392607");
	//Private Exponent of keypair 4
	private BigInteger privexp = new BigInteger(
			"92454915450246019148350587719119280176507454823999976900010068751600075033447685178176397416510" +
			"09219721564087777248059960700479142079969720818605008210601");
	BigInteger pubexp = new BigInteger("65537");

	//constructor
	public Exercise8_RSACipher() throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException {
		
		RSAPrivateKeySpec = new RSAPrivateKeySpec(mod, privexp);
		privekey = KeyFactory.getInstance("RSA").generatePrivate(RSAPrivateKeySpec);

		priveCipher = Cipher.getInstance("RSA");
		priveCipher.init(Cipher.DECRYPT_MODE, privekey);
	}

	public BigInteger getMod() {
		return this.mod;
	}

	public BigInteger getPriveExp() {
		return this.privexp;
	}

	// Decryption Method
	public byte[] rsaDecrypt(byte[] data) throws IllegalBlockSizeException,
			BadPaddingException {
		return priveCipher.doFinal(data);
	}

	// Main method
	public static void main(String[] args) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		// create new class object
		Exercise8_RSACipher rsac = new Exercise8_RSACipher();
		// print the key pair
		System.out.println("Exponent = " + rsac.getPriveExp().toString());
		System.out.println("Modulus = " + rsac.getMod().toString());

		//Ciphertext 2
		String myCiphertext = new String(
				"2002A2C01A097F8DD07A9A67BB1E98F0205A2FC365920AD8C79F05B5E49882D83DFEF6626032722719E81" +
				"939D8983281DBA1B2579F58F828115CBABC44CF48FB");
		// print out the ciphertext
		System.out.println("\nChiphertext: " + myCiphertext);
		//Decryption performed
		byte[] cipherbytes = DatatypeConverter.parseHexBinary(myCiphertext);
		byte[] plainbytes = rsac.rsaDecrypt(cipherbytes);
		//Print out the decrypted message
		System.out.println("\nDecrypted Plaintext: " + new String(plainbytes));

	}
}