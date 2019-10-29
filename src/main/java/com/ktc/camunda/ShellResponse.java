package com.ktc.camunda;

import java.util.Map;
import org.camunda.connect.spi.CloseableConnectorResponse;

import org.camunda.connect.impl.AbstractConnectorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShellResponse extends AbstractConnectorResponse {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShellResponse.class);
  String output;
  String error;
  int code;

  public ShellResponse(String output, String error, int code){
    this.output=output;
    this.error=error;
    this.code=code;
  }

  @Override
  protected void collectResponseParameters(Map<String, Object> responseParameters) {
    responseParameters.put("output", this.output);
    responseParameters.put("error", this.error);
    responseParameters.put("code", this.code);
  }
  @Override
  public String toString() {
    return "ShellResponse [code="+this.code+" output=" + this.output + "]";
  }

}
