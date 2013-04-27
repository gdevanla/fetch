package MyTests.bluej;



import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import bluej.Boot;
import bluej.Config;

import junit.framework.TestCase;

public class TestMocks  extends TestCase {
	
	protected static IMocksControl control;
	protected static Config config;
	
	protected static void initMocks() {
		if (control == null)
			control = EasyMock.createNiceControl();
		else
			control.reset();
		createMocks();
		createFactoryExpectations();
	}
	
	private static void createMocks() {
		config = control.createMock(Config.class);
		
	}
	
	private static void createFactoryExpectations() {
		
	        final String[] args = {""};
	        Properties commandLineProps = new Properties();
	        File bluejLibDir = calculateBluejLibDir();

	        Config.initialise(bluejLibDir, commandLineProps, false);
	        
	}
	
	private static File calculateBluejLibDir()
    {
        File bluejDir = null;
        String bootFullName = Boot.class.getResource("Boot.class").toString();

        try {
            if (! bootFullName.startsWith("jar:")) {
                // Boot.class is not in a jar-file. Find a lib directory somewhere
                // above us to use
                File startingDir = (new File(new URI(bootFullName)).getParentFile());
                while((startingDir != null) &&
                        !(new File(startingDir.getParentFile(), "lib").isDirectory())) {
                    startingDir = startingDir.getParentFile();
                }
                
                if (startingDir == null) {
                    bluejDir = null;
                }
                else {
                    bluejDir = new File(startingDir.getParentFile(), "lib");
                }
            }
            else {
                // The class is in a jar file, '!' separates the jar file name
                // from the class name. Cut off the class name and the "jar:" prefix.
                int classIndex = bootFullName.indexOf("!");
                String bootName = bootFullName.substring(4, classIndex);
                
                File finalFile = new File(new URI(bootName));
                bluejDir = finalFile.getParentFile();
            }   
        } 
        catch (URISyntaxException use) { }
        
        return bluejDir;
    }
}
