package edu.ncsu.csc.itrust;

import java.lang.reflect.Modifier;
import java.util.regex.Pattern;
import junit.framework.Test;
import junitx.util.DirectorySuiteBuilder;
import junitx.util.SimpleTestFilter;

public class AllTests {
	
	//only run the tests for which we know the trace matrix.
	static Pattern p = Pattern.compile(".*Test.*Test");
	
	@SuppressWarnings("rawtypes")
	public static Test suite() throws Exception {
		DBBuilder.rebuildAll();
		DirectorySuiteBuilder builder = new DirectorySuiteBuilder();
		builder.setFilter(new SimpleTestFilter() {
			@Override
			public boolean include(Class clazz) {
				// Ignore the HTTP tests in this suite
				System.out.println("Test being returned=" + clazz.getName());
				return !clazz.getPackage().getName().contains("http")
						&& !Modifier.isAbstract(clazz.getModifiers())
					    && p.matcher(clazz.getName()).matches();
			}
		});
		return builder.suite("build/classes/");
	}

}
