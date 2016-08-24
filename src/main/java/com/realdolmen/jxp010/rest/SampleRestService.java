package com.realdolmen.jxp010.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.realdolmen.jxp010.jaxb.domain.Sample;

@Path("/samples")
public interface SampleRestService {
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public List<Sample> listAllSamples();
}
