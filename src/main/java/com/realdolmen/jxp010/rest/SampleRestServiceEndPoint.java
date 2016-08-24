package com.realdolmen.jxp010.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.realdolmen.jxp010.jaxb.domain.Sample;

@Path("/samples")
@RequestScoped
public class SampleRestServiceEndPoint implements SampleRestService {

	@GET
	@Produces({MediaType.APPLICATION_XML})
	public List<Sample> listAllSamples() {
		final List<Sample> samples = new ArrayList<>();
		samples.add(createSample("sample1"));
		samples.add(createSample("sample2"));
		samples.add(createSample("sample3"));
		return samples;
	}

	private Sample createSample(String sampleName) {
		Sample sample = new Sample();
		sample.setSample(sampleName);
		return sample;
	}
}
