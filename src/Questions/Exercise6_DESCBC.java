package Questions;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Exercise6_DESCBC {

	SecretKey deskey;
	Cipher desEnCipher;  // DES CBC encryption cipher with key, PKCS5 padding
	Cipher desDeCipher;  // DES CBC decryption cipher with key, No padding
	
	public Exercise6_DESCBC(){};
	
	public void setDESCipher(byte[] keybytes, byte[] iv){
		deskey = new SecretKeySpec(keybytes, "DES");
		try{
			desEnCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			desDeCipher = Cipher.getInstance("DES/CBC/NoPadding");
			desEnCipher.init(Cipher.ENCRYPT_MODE,deskey,new IvParameterSpec(iv));
			desDeCipher.init(Cipher.DECRYPT_MODE,deskey,new IvParameterSpec(iv));	

		}
		catch (InvalidKeyException e){
		}
		catch (NoSuchAlgorithmException e){
		}
		catch (NoSuchPaddingException e){
		} 
		catch (InvalidAlgorithmParameterException e) {
		} 
	 }
		
	public void setDESCipher(byte[] keybytes){ 
		deskey = new SecretKeySpec(keybytes, "DES");
		try{
			desEnCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			desDeCipher = Cipher.getInstance("DES/CBC/NoPadding");
			desEnCipher.init(Cipher.ENCRYPT_MODE,deskey);
			desDeCipher.init(Cipher.DECRYPT_MODE,deskey,new IvParameterSpec(desDeCipher.getIV()));	
		}
		catch (InvalidKeyException e){
		}
		catch (NoSuchAlgorithmException e){
		}
		catch (NoSuchPaddingException e){
		} 
		catch (InvalidAlgorithmParameterException e) {
		}
}

	public byte[] encrypt(byte[] databytes){
		byte[] enc = null;
		try{
		enc = desEnCipher.doFinal(databytes);
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
		}
		return enc;
	}

	public byte[] decrypt(byte[] databytes){
		byte[] dec = null;
		try{
		dec = desDeCipher.doFinal(databytes);
		}
		catch (IllegalBlockSizeException e){
		}
		catch (BadPaddingException e){
		}
		return dec;
	}
	
	public byte[] getIV(){
		return desEnCipher.getIV();
	}
	
}
