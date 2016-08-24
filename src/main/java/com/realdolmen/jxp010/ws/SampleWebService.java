package com.realdolmen.jxp010.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.realdolmen.jxp010.jaxb.domain.Sample;

@WebService
public interface SampleWebService {
	@WebMethod
	public List<Sample> listAllSamples();
}
