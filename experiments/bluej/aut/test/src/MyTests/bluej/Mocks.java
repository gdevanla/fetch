package MyTests.bluej;

import static org.easymock.EasyMock.expect;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Properties;

import junit.framework.TestCase;
import org.easymock.classextension.EasyMock;
import org.easymock.classextension.IMocksControl;

import bluej.Boot;
import bluej.Config;

/**
 * Any class that requires the Config class properties should be tested by
 * extending this Test Class. The Config class properties are only initialized 
 * when the main method is called from the Boot.java
 * For unit testing, we need to use Config properties without calling the main 
 * method. This class will provide the reflection of the Config class.
 * Optionally, the "args" field may be set before calling initMocks() to
 * set different command line arguments. Otherwise, it will be empty.
 * 
 * @author Nikhat Farha
 *
 */

public class Mocks extends TestCase{
	protected static IMocksControl control;
	protected static Boot boot;
	protected static String[] args = {""};
	
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args1) {
		args = args1;
	}

	protected static void initMocks() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		if (control == null)
			control = EasyMock.createNiceControl();
		else
			control.reset();
		createMocks();
		createFactoryExpectations();
		initialiseConfigObject();
	}
	
	private static void initialiseConfigObject() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		
				assertNotNull(boot);
				forceSetInt(boot, "commandLineProps", getCommandLineProperties());
				
				Properties commandLineProps = getCommandLineProperties();
				File bluejLibDir = Boot.getBluejLibDir();
				Config.initialise(bluejLibDir, commandLineProps, false);
		       
				assertEquals("equal to", Config.getString("debugger.assert.equalTo"));
				
			/*	// Step 4. Verify the mocks were hit as you expected
						control.verify(); // Don't forget this! */
	}
	
	private static void forceSetInt(Object o, String fieldName, Properties properties) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
	    Class<?> clazz = Boot.class;
	    Field field = clazz.getDeclaredField(fieldName);
	    field.setAccessible(true);
	    field.set(o, properties);
	}
	

	private static void createMocks() {
		boot = control.createMock(Boot.class);
	}
	private static void createFactoryExpectations() {
		expect(boot.getCommandLineProperties()).andReturn(getCommandLineProperties()).anyTimes();
	}

	
	public static Properties getCommandLineProperties() {
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
