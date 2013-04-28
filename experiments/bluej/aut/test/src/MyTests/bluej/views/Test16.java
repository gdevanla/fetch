package MyTests.bluej.views;

import MyTests.bluej.pkgmgr.Test15;
import bluej.views.ConstructorView;
import bluej.views.FieldView;
import bluej.views.MethodView;
import bluej.views.View;
import junit.framework.TestCase;

/**
 * Testing bluej.views.ConstructorView.java,
 * import bluej.views.FieldView.java and 
 * import bluej.views.MethodView.java
 * 
 * @author Nikhat Farha
 *
 */



public class Test16 extends TestCase {
	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	
	public void testGetAllFields() {
		View view = View.getView(Test15.class);
		FieldView[] fieldViews = view.getAllFields();
		assertEquals(5 ,fieldViews.length);
		assertEquals("junit.framework.TestCase",fieldViews[0].getClassName());
		assertEquals("fName",fieldViews[0].getName());
		assertEquals("projectName",fieldViews[1].getName());
		assertEquals("MyTests.bluej.pkgmgr.Test15",fieldViews[1].getClassName());
		assertEquals("currentDir",fieldViews[2].getName());
		assertEquals("pkg",fieldViews[3].getName());
		assertEquals("dir",fieldViews[4].getName());
		assertEquals("MyTests.bluej.pkgmgr.Test15",fieldViews[4].getDeclaringView().getQualifiedName());
	}	
	
	public void testGetAllMethods() {
		View view = View.getView(Test15.class);
		MethodView[] methodViews = view.getAllMethods();
		assertEquals(70 ,methodViews.length);
		boolean test1=false;
		boolean test2 = false;
		for(int i=0; i< methodViews.length ; i++) {
			if("setUp".equals(methodViews[i].getName())) {
				assertEquals(0,methodViews[i].getParameterCount());
				assertEquals("void",methodViews[i].getReturnType().getQualifiedName());
				test1=true;
			}
			if("junit.framework.TestCase".equals(methodViews[i].getClassName()))
				test2= true;
		}
		assertTrue(test1);
	}
	
	public void testGetPackageName() {
		View view = View.getView(Test15.class);
		assertEquals("MyTests.bluej.pkgmgr",view.getPackageName());
		assertEquals("Test15",view.getBaseName());
		assertEquals("Test15",view.getTypeName());
		assertEquals(4,view.getDeclaredFields().length);
		FieldView[] fieldViews = view.getDeclaredFields();
		assertEquals("dir",fieldViews[0].getName());
		assertEquals("pkg",fieldViews[1].getName());
		assertEquals("currentDir",fieldViews[2].getName());
		assertEquals("projectName",fieldViews[3].getName());
	}
	
	public void testGeneric() {
		View view = View.getView(Test15.class);
		assertFalse(view.isInterface());
		assertFalse(view.isGeneric());
		assertEquals("junit.framework.TestCase",view.getSuper().getQualifiedName());
		assertEquals(9,view.getDeclaredMethods().length);
		MethodView[] methodViews = view.getDeclaredMethods();
		assertEquals(0,methodViews[8].getParameterCount());
		assertEquals("void",methodViews[8].getReturnType().getQualifiedName());
	}
	
	public void testGetClassLoader() {
		View.removeAll(Test15.class.getClassLoader());
		View view = View.getView(Test15.class);
		assertEquals(9,view.getDeclaredMethods().length);
		
	}
	
	public void testGetConstructors() {
		View view = View.getView(FieldView.class);
		ConstructorView[] constructorViews = view.getConstructors();
		assertEquals(1,constructorViews.length);
		assertEquals("bluej.views.FieldView(View, Field)",constructorViews[0].getShortDesc());
		assertEquals("bluej.views.FieldView(View, Field)",constructorViews[0].getLongDesc());
		assertEquals(2,constructorViews[0].getParameterCount());
	}
}
