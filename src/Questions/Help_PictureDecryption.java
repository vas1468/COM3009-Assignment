package Questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Help_PictureDecryption {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileReader fr = new FileReader("files/pyramid.txt");
		BufferedReader br = new BufferedReader(fr);
		String picture = "";
		
		try{
		String line;
		while ((line = br.readLine()) != null) {		
			picture += line;
		}
		
		fr.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
		String r = picture.replace(" ", "");
		System.out.println(r);
	}
	
}
