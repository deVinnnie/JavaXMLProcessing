package com.realdolmen.jxp010.ws;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.jxp010.jaxb.domain.Sample;

public class SampleWebServiceClientTest {
	private static SampleWebService sampleWebService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		URL wsdlLocation = new URL("http://localhost:8080/jxp010-getting-started/SampleWebService?wsdl");
		QName serviceName = new QName("http://ws.jxp010.realdolmen.com/", "SampleWebService");
		Service service = Service.create(wsdlLocation, serviceName);
		sampleWebService = service.getPort(SampleWebService.class);
	}

	@Test
	public void testFindAllUsers() {
		List<Sample> result = sampleWebService.listAllSamples();
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
}
