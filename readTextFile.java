import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class readTextFile {
	static public String getContents(File aFile){
		StringBuffer contents = new StringBuffer();
		try{
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try{
				String line=null;
				while((line=input.readLine())!=null){
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
				}
			}
			finally{
				input.close();
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return contents.toString();
	}
}
