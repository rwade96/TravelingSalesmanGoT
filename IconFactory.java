import java.awt.Image;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.ImageIcon;

public class IconFactory {
    
    public Image getImage(String fileName) {
    	File f = new File("./src/"+fileName.trim());
    	ImageIcon icon = new ImageIcon(f.toString());
		return icon.getImage();
    }
    
    public String fileFinder(String endsWith, int index) {
    	//Creates and applies text filter ".jpg" to find the first file ending in ".jpg"
    	File f = new File("./src/"); // current directory
    	FilenameFilter textFilter = new FilenameFilter() {
    		public boolean accept(File dir, String name) {
    			String lowercaseName = name.toLowerCase();
    			if (lowercaseName.endsWith(endsWith))
    				return true;
    			else
    				return false;
    		}
    	};
    	File[] files = f.listFiles(textFilter);
    	System.out.println(files[index].toString());
    	return files[index].toString();
    }
}
