import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileRenamer {
	//regular expression to match
	private static String expression = "([(\\[)(\\()][(\\w)(\\~)(\\d)(\\ )(\\_)(\\.)(\\,)(\\-)(\\&)(\\!)(\\')]*[(\\])(\\)])";
	
	public static void main(String[] args) {
	    SimpleFileChooser sfc = new SimpleFileChooser();
	    sfc.setVisible(true);
	}
	

	public static void recurseRename(File file){
		File dir = new File(file.toPath().toString());
		//if folder get contents
		if (dir.isDirectory()){
			File[] contents = getContents(dir);
			for (File f : contents){
				recurseRename(f);
			}
		} else {
			//it's a file
			renameFile(file);
		}
		
	}
	
	public static void renameFile(File file){
		String fileName = file.getName();
		fileName = fileName.replaceAll("_", " ").replaceFirst("\\(B-A\\)", "");
		Pattern pattern = Pattern
				.compile(expression);
		Matcher matcher = pattern.matcher(fileName);
		//remove contents surround by brackets 
		String x = (matcher.replaceAll(""));//.replace("_", " "));
		//make sure the start and end of the file do not contain spaces
		while (x.startsWith(" ")) {
			x = x.replaceFirst(" ", "");
		}
		while (x.contains(" .mkv") || x.contains(" .avi") || x.contains(" .mp4")) {
			x = x.replaceFirst(" .mkv", ".mkv");
			x = x.replaceFirst(" .avi", ".avi");
			x = x.replaceFirst(" .mp4", ".mp4");
		}
		//rename the file
		File newFile = new File(file.getParent() + "\\" + x);
		//System.out.println(newFile.toPath());
		file.renameTo(newFile);
	}
		
	
	public static File[] getContents(File file){
		File[] contents = file.listFiles();
		return contents;
		
	}
	
	
			
		
	

}