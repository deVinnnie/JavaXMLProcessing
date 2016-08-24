package com.realdolmen.jxp010.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.realdolmen.jxp010.jaxb.domain.Sample;

@WebService(serviceName="SampleWebService")
public class SampleWebServiceEndPoint implements SampleWebService {
	@WebMethod
	@WebResult(name = "samples")
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
