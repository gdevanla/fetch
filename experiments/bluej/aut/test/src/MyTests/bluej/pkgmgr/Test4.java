package MyTests.bluej.pkgmgr;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import junit.framework.TestCase;


import bluej.Boot;
import bluej.Config;
import bluej.pkgmgr.Project;

/**
 * Testing the bluej.pkgmgr.Project.java class.
 * 
 * @author Nikhat Farha
 *
 */

public class Test4 extends TestCase{
	

	protected void setUp() {
		String[] args = {""};
		Properties commandLineProps = processCommandLineProperties(args);
		File bluejLibDir = calculateBluejLibDir();
		Config.initialise(bluejLibDir, commandLineProps, false);
	}
	protected void tearDown() {
		//nothing to do
	}
	
	
	public void testCreateProject() {
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject";
		
		String bluejFileName = projectName + File.separator + "package.bluej";
		File bluejFile = new File(bluejFileName);
		
		Project.createNewProject(projectName, true);
		File projectFile = new File(projectName);
		assertTrue(projectFile.exists());
		assertTrue(bluejFile.exists());
		
	}
	
	
	public void testIsProject() {
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject";
		String bluejFileName = projectName + File.separator + "package.bluej";
		Project.createNewProject(projectName, true);
		assertTrue(Project.isProject(bluejFileName));
	}
	
	public void testOpenProjectCount() {
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject";
	//	String bluejFileName = projectName + File.separator + "package.bluej";
	
		Project.createNewProject(projectName, true);
		
		assertEquals(0, Project.getOpenProjectCount());
	}
	
	
	public void testIsME() {
		String currentDir =  System.getProperty("user.dir");
		String projectName = currentDir + File.separator + "newProject";
		
		String bluejFileName = projectName + File.separator + "package.bluej";
		Project.createNewProject(projectName, true);
		
		assertTrue(Project.isProject(bluejFileName));
	
	}
	
	public void testGetProjects() {
		String outputDir = "C:\\Documents and Settings";
		String projectName = outputDir + File.separator + "newProject";
		Project.createNewProject(projectName, true);
		assertEquals(0,	Project.getProjects().size());
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
