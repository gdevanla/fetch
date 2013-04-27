package MyTests.bluej.editor.moe;


import java.awt.Button;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.TextField;

import bluej.editor.moe.Info;
import bluej.editor.moe.ParserMessageHandler;
import MyTests.bluej.Mocks;

/**
 * Testing the class bluej.editor.moe.ParserMessageHandler.java and
 * bluej.editor.moe.Info.java
 * 
 * @author Nikhat Farha
 *
 */


public class Test18 extends Mocks { 
	
	
	protected void setUp() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		super.initMocks();
		
	}
	protected void tearDown() {
		
	}
	public void testGetMessageForCode() {
		String code = "BJ000";
		String msg ="Method declaration should be followed by method body (or \"throws\", or \";\")"; 
		assertEquals(msg,ParserMessageHandler.getMessageForCode(code));
		code = "BJ001";
		msg ="\"(\" expected; \"if\"/\"while\"/\"switch\" must be followed by \"(\" -\n" + 
				"did you forget to put \"(\" / \")\" around the condition?  "; 
		assertEquals(msg,ParserMessageHandler.getMessageForCode(code));
		code = "BJ002";
		msg ="\"(\" expected; You must have a \"(\" / \")\" pair around the condition\n" + "" +
				"(and \"{\" / \"}\" around the loop body)."; 
		assertEquals(msg,ParserMessageHandler.getMessageForCode(code));
		code = "BJ003";
		msg ="\";\" expected here."; 
		assertEquals(msg,ParserMessageHandler.getMessageForCode(code));
	}
	
	public void test() {
		Info info = new Info();
		info.message("Hello");
		
		Button button = new Button ("This is the label of the button.");
		button.setName("Test Button");
		button.setLocation(new Point(1,2));
		
		TextArea txtArea = new TextArea();
		int style = Font.BOLD | Font.ITALIC;
		Font font = new Font ("Garamond", style , 11);
		txtArea.setFont(font);
		txtArea.setLocation(4,10);
		txtArea.setName("Test Text Area");
		
		TextField txtfield = new TextField(20);
		txtfield.setName("Test Text Field");
		txtfield.setLocation(12,15);
		txtfield.setFont(font);
		info.add(button);
		info.add(txtArea);
		info.add(txtfield);
		
		assertEquals(5,info.getComponentCount());
		assertEquals("Test Button",info.getComponents()[2].getName());
		assertEquals("This is the label of the button.",((Button) info.getComponents()[2]).getLabel());
		assertEquals(0.5f,((Button) info.getComponents()[2]).getAlignmentX());
		assertEquals(0.5f,((Button) info.getComponents()[2]).getAlignmentY());
		assertEquals(1,((Button) info.getComponents()[2]).getX());
		assertEquals(2,((Button) info.getComponents()[2]).getY());
		
		assertEquals("Test Text Area",info.getComponents()[3].getName());
		assertEquals(0.5f,((TextArea) info.getComponents()[3]).getAlignmentX());
		assertEquals(0.5f,((TextArea) info.getComponents()[3]).getAlignmentY());
		assertEquals(4,((TextArea) info.getComponents()[3]).getX());
		assertEquals(10,((TextArea) info.getComponents()[3]).getY());
		assertEquals("Garamond Italic",((TextArea) info.getComponents()[3]).getFont().getFontName());
		assertEquals(Font.BOLD | Font.ITALIC,((TextArea) info.getComponents()[3]).getFont().getStyle());
		assertEquals(11,((TextArea) info.getComponents()[3]).getFont().getSize());
		
		assertEquals("Test Text Field",info.getComponents()[4].getName());
		assertEquals(0.5f,((TextField) info.getComponents()[4]).getAlignmentX());
		assertEquals(0.5f,((TextField) info.getComponents()[4]).getAlignmentY());
		assertEquals(12,((TextField) info.getComponents()[4]).getX());
		assertEquals(15,((TextField) info.getComponents()[4]).getY());
		assertEquals("Garamond Italic",((TextField) info.getComponents()[4]).getFont().getFontName());
		assertEquals(Font.BOLD | Font.ITALIC,((TextField) info.getComponents()[4]).getFont().getStyle());
		assertEquals(11,((TextField) info.getComponents()[4]).getFont().getSize());
		
	}
	
	public void testSetFont() {
		Info info = new Info();
		info.message("Hello");
		
		//These are the defaults
		assertEquals("Tahoma",info.getFont().getFontName());
		assertEquals(Font.PLAIN ,info.getFont().getStyle());
		assertEquals(11,info.getFont().getSize());
		
		//Overwriting the defaults
		int style = Font.BOLD | Font.ITALIC;
		Font font = new Font ("Garamond", style , 16);
		info.setFont(font);
		
		assertEquals("Garamond Italic",info.getFont().getFontName());
		assertEquals(Font.BOLD | Font.ITALIC,info.getFont().getStyle());
		assertEquals(16,info.getFont().getSize());
		
	}
}

