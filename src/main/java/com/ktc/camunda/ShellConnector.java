package com.ktc.camunda;


import org.camunda.connect.Connectors;
import org.camunda.connect.spi.Connector;
import org.camunda.connect.impl.AbstractConnector;
import org.camunda.connect.spi.ConnectorResponse;

import java.io.Closeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShellConnector extends AbstractConnector<ShellRequest,ShellResponse> {
    public static final String ID = "shell";
    private final static Logger LOGGER = LoggerFactory.getLogger(ShellConnector.class);

  public ShellConnector() {
    super(ID);
  }

  public ShellRequest createRequest() {
    return new ShellRequest(this);
  }

 @Override
  public ConnectorResponse execute(ShellRequest request) {
    ShellResponse response;
    try{
	ShellInvocation invocation=new ShellInvocation(request,request, requestInterceptors);
	response=(ShellResponse)invocation.proceed();
    }catch (Exception e) {
	LOGGER.error("Shell exception: "+ e.getMessage());
	throw new ShellException("Shell Connector execution error ",e);
    }
    return response;
  }
}
