package Main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Main{
	@SuppressWarnings("unused")
	public static void main(String[] args){
		Game g=new Game();
		File file = new File("./");
		String[] directories = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		System.out.println(Arrays.toString(directories));
	}
}