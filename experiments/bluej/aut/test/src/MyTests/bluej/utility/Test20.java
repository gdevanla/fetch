package MyTests.bluej.utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import MyTests.bluej.pkgmgr.Test15;
import bluej.debugger.gentype.FieldReflective;
import bluej.debugger.gentype.GenTypeClass;
import bluej.debugger.gentype.GenTypeDeclTpar;
import bluej.debugger.gentype.MethodReflective;
import bluej.debugger.gentype.Reflective;
import bluej.utility.JavaReflective;
import junit.framework.TestCase;

/**
 * Testing the bluej.utility.JavaReflective.java class
 * 
 * @author Nikhat Farha
 *
 */


public class Test20 extends TestCase {
	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	
	public void testGetName() {
		JavaReflective reflective = new JavaReflective(Test15.class);
		assertEquals("MyTests.bluej.pkgmgr.Test15",reflective.getName());
		assertEquals("MyTests.bluej.pkgmgr.Test15",reflective.getSimpleName());
	}
	
	public void testGetArrayOf() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		Reflective reflective = javaReflective.getArrayOf();
		assertEquals("[LMyTests.bluej.pkgmgr.Test15;",reflective.getName());
		assertEquals("MyTests.bluej.pkgmgr.Test15[]",reflective.getSimpleName());
	}
	
	public void testGetDeclaredMethods() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		ArrayList<String> methodNames = new ArrayList<String>();
		methodNames.add("setUp");
		methodNames.add("testToString");
		methodNames.add("testLoad");
		methodNames.add("testSave");
		methodNames.add("testExists");
		methodNames.add("testIsPackageFileName");
		methodNames.add("testPackageCreate");
		methodNames.add("testIsOldPackageFileName");
		methodNames.add("tearDown");
		
		Map<String, Set<MethodReflective>> reflectiveMap = javaReflective.getDeclaredMethods();
		assertEquals(methodNames.size(),reflectiveMap.size());
		for (Entry<String, Set<MethodReflective>> entry : reflectiveMap.entrySet()) {
			Set<MethodReflective> set = entry.getValue();
			assertEquals(1, set.size());
			Object[] array = set.toArray();
			assertTrue("Unable to find: "+((MethodReflective)array[0]).getName(),methodNames.contains( ((MethodReflective)array[0]).getName()));
		}
	}
	
	public void testGetDeclaredFields() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		ArrayList<String> fieldNames = new ArrayList<String>();
		fieldNames.add("dir");
		fieldNames.add("pkg");
		fieldNames.add("currentDir");
		fieldNames.add("projectName");
		
		Map<String, FieldReflective> reflectiveMap = javaReflective.getDeclaredFields();
		assertEquals(fieldNames.size(),reflectiveMap.size());
		for (Entry<String, FieldReflective> entry : reflectiveMap.entrySet()) {
			FieldReflective value = entry.getValue();
			assertTrue("Unable to find:",fieldNames.contains(value.getName()));
		}
		FieldReflective value = reflectiveMap.get("projectName");
		assertEquals("bluej.debugger.gentype.GenTypeClass",value.getType().getClass().getName());
	}
	
	public void testIsInterface() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		Reflective reflective = javaReflective.getArrayOf();
		assertFalse(reflective.isInterface());
		assertFalse(reflective.isStatic());
		assertTrue(reflective.isPublic());
	}
	
	public void testGetTypeParams() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		List<GenTypeDeclTpar> paramList = javaReflective.getTypeParams();
		//Returns empty list if the class is not generic
		assertEquals(0,paramList.size());
	}
	
	public void testRelativeClass() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		Reflective reflective = javaReflective.getRelativeClass("bluej.pkgmgr.BlueJPackageFile");
		assertEquals("bluej.pkgmgr.BlueJPackageFile",reflective.getName());
		assertEquals("bluej.pkgmgr.BlueJPackageFile",reflective.getSimpleName());
	}
	
	public void testOuterClass() {
		JavaReflective javaReflective = new JavaReflective(FileReader.class);
		Reflective reflective = javaReflective.getOuterClass();
		//Will return null since there isn't any outer class
		assertNull(reflective);
	}
	
	public void testInners() {
		JavaReflective javaReflective = new JavaReflective(FileReader.class);
		List<Reflective> reflectiveList = javaReflective.getInners();
		//returns empty list since there aren't any inner classes
		assertEquals(0,reflectiveList.size());
	}
	
	public void testGetUnderLyingClass() {
		JavaReflective javaReflective = new JavaReflective(FileReader.class);
		assertEquals("java.io.FileReader",javaReflective.getUnderlyingClass().getName());
	}
	
	public void testIsAssignableFrom() {
		JavaReflective javaReflective = new JavaReflective(FileReader.class);
		Reflective reflective = javaReflective.getRelativeClass("java.io.File.class");
		assertFalse(javaReflective.isAssignableFrom(reflective));
		//It is assignable only from the underlying class of the reflective
		//object
		assertTrue(javaReflective.isAssignableFrom(javaReflective));
	}
	
	public void testSuperTypes() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		//Returns GenType objects
		List<GenTypeClass> list = javaReflective.getSuperTypes();
		assertEquals(1,list.size());
		GenTypeClass genType = list.get(0);
		//Test15.class extends junit.framework.TestCase
		assertEquals("junit.framework.TestCase",genType.getReflective().getName());
	}
	
	public void testSuperTypesr() {
		JavaReflective javaReflective = new JavaReflective(Test15.class);
		//Returns Reflective objects
		List<Reflective> list = javaReflective.getSuperTypesR();
		assertEquals(1,list.size());
		Reflective superReflective = list.get(0);
		//Test15.class extends junit.framework.TestCase
		assertEquals("junit.framework.TestCase",superReflective.getName());
	}
	
	
}
