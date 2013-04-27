package edu.ncsu.csc.itrust.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.HospitalBean;

public class HospitalBeanTest extends TestCase {

	public void testHospitalBean() throws Exception {
		HospitalBean h = new HospitalBean();
		h.setHospitalID("id");
		h.setHospitalName("name");
		assertEquals("id", h.getHospitalID());
		assertEquals("name", h.getHospitalName());
		assertEquals(42, h.hashCode());
	}
	
	public void testFullConstructor() throws Exception {
		HospitalBean h = new HospitalBean("id","name","address","city","ST","12345-6789",0,0);
		assertEquals("id", h.getHospitalID());
		assertEquals("name", h.getHospitalName());
		assertEquals("address", h.getHospitalAddress());
		assertEquals("city", h.getHospitalCity());
		assertEquals("ST", h.getHospitalState());
		assertEquals("12345-6789", h.getHospitalZip());
		assertEquals(0.0, h.getHospitalLat());
		assertEquals(0.0, h.getHospitalLng());
		assertEquals(42, h.hashCode());
	}
}
