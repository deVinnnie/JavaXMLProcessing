package com.realdolmen.jxp010.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.jxp010.jaxb.domain.Sample;

public class SampleRestClientTest {
	private static SampleRestService client;

	@BeforeClass
	public static void setupBeforeClass() {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
		client = ProxyFactory.create(SampleRestService.class,
				"http://localhost:8080/jxp010-getting-started/rest");
	}

	@Test
	public void testFindAllUsers() {
		List<Sample> result = client.listAllSamples();
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
}
