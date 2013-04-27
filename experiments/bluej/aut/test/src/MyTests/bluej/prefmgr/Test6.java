package MyTests.bluej.prefmgr;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import bluej.Boot;
import bluej.Config;
import bluej.prefmgr.PrefMgr;
import junit.framework.TestCase;

/**
 * Testing the class: bluej.prefmgr.PrefMgr.java
 * @author Nikhat Farha
 *
 */

public class Test6 extends TestCase{
	protected void setUp() {
		String[] args = {""};
		Properties commandLineProps = processCommandLineProperties(args);
		File bluejLibDir = calculateBluejLibDir();
		Config.initialise(bluejLibDir, commandLineProps, false);
	}
	protected void tearDown() {
		
	}
	
	public void test1() {
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject1";
		PrefMgr.setProjectDirectory(projectName);
		PrefMgr.setEditorFontSize(12);
		PrefMgr.setNaviviewExpanded(true);
		PrefMgr.setFlag("test flag", true);
		assertEquals(projectName, PrefMgr.getProjectDirectory());
		assertEquals(12,PrefMgr.getEditorFontSize());
		assertEquals(true,PrefMgr.getNaviviewExpanded());
		assertEquals(true, PrefMgr.getFlag("test flag"));
		
	}
	
	public void test2() {
		assertEquals(1,PrefMgr.getRecentProjects().size());
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject1";
		
		PrefMgr.addRecentProject(projectName);
		assertEquals(2,PrefMgr.getRecentProjects().size());

	}
	
	public void test3() {
		assertEquals(2,PrefMgr.getRecentProjects().size());
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject1";
		
		PrefMgr.addRecentProject(projectName);
		assertEquals(2,PrefMgr.getRecentProjects().size());
		
		assertEquals("SansSerif",PrefMgr.getPopupMenuFont().getName());
		assertEquals(12,PrefMgr.getPopupMenuFont().getSize());
		assertEquals(10,PrefMgr.getScopeHighlightStrength());
		assertEquals("Monospaced",PrefMgr.getStandardEditorFont().getName());
		assertEquals(12,PrefMgr.getStandardEditorFont().getSize());
		
	}
	
	
	public void test4() {
		assertEquals(2,PrefMgr.getRecentProjects().size());
		
		assertEquals("SansSerif",PrefMgr.getStandardFont().getName());
		assertEquals(12,PrefMgr.getStandardFont().getSize());
		assertEquals("SansSerif",PrefMgr.getStandardMenuFont().getName());
		assertEquals(12,PrefMgr.getStandardMenuFont().getSize());
		assertEquals("SansSerif",PrefMgr.getStandoutFont().getName());
		assertEquals(12,PrefMgr.getStandoutFont().getSize());
		
	}
	
	public void test5() {
		assertEquals(1674,PrefMgr.getTargetFont().getNumGlyphs());
		assertEquals(1674,PrefMgr.getStandardFont().getNumGlyphs());
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
	
	 private static Properties processCommandLineProperties(String[] args)
	    {
	        Properties props = new Properties();

	        for(int i = 0; i < args.length; i++) {
	            if (!args[i].startsWith("-"))
	                continue;
	            
	            String definition = args[i].substring(1);
	            int definitionEquals = definition.indexOf('=');
	            
	            if (definitionEquals < 0)
	                continue;
	            
	            String propName = definition.substring(0, definitionEquals); 
	            String propValue = definition.substring(definitionEquals+1);
	            
	            if (!propName.equals("") && !propValue.equals(""))
	                props.put(propName, propValue);
	        }
	        return props;
	    }
}
