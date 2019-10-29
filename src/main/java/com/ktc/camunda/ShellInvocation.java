package com.ktc.camunda;

import java.util.Arrays;
import java.util.List;
import org.camunda.connect.impl.AbstractRequestInvocation;
import org.camunda.connect.spi.ConnectorRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShellInvocation extends AbstractRequestInvocation<ShellRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShellInvocation.class);
    private ShellRequest request;
  public ShellInvocation(ShellRequest target, ShellRequest request, List<ConnectorRequestInterceptor> requestInterceptors){
    super(target, request, requestInterceptors);
    this.request=request;
  }

  @Override
  public Object invokeTarget() throws Exception {
    LOGGER.debug(" [x] Shell execute '{}'", request.getCmdLine());

      try {
	Process process;
	if (request.getDir()!=null && request.getDir()!=""){
    	    process = Runtime.getRuntime().exec(request.getCmdLine(),null,new File(request.getDir()));
	    }
	else
    	    process = Runtime.getRuntime().exec(request.getCmdLine());

	if (request.getInput()!=null){
    	    process.getOutputStream().write(request.getInput().getBytes());
	    process.getOutputStream().flush();
	    process.getOutputStream().close();
	}

        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
	BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	BufferedReader err_reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	String line;
	while ((line = reader.readLine()) != null) {
	    output.append(line + "\n");
	}
	while ((line = err_reader.readLine()) != null) {
	    error.append(line + "\n");
	}

	int code = process.waitFor();
        LOGGER.debug(" [x] Shell execute '{}'", request.getCmdLine());
        return new ShellResponse(output.toString(),error.toString(),code);

      } catch (Exception ex) {
         ex.printStackTrace();
      }
    return new ShellResponse("","",-1);
  }

}
