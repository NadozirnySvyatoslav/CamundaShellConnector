package com.ktc.camunda;

import org.camunda.connect.impl.AbstractConnectorRequest;
import org.camunda.connect.spi.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShellRequest extends AbstractConnectorRequest<ShellResponse> {
   private final static Logger LOGGER = LoggerFactory.getLogger(ShellRequest.class);

 public ShellRequest(Connector<?> connector) {
    super(connector);
  }

 public String getCmdLine() {
    return getRequestParameter("cmd");
  }
 public String getDir() {
    return getRequestParameter("dir");
  }
 public String getInput() {
    return getRequestParameter("input");
  }

  @Override
  protected boolean isRequestValid() {

    if(getCmdLine() == null ) {
      LOGGER.error("parameter 'cmd' is empty {}", this);
        return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "ShellRequest [cmd=" + getRequestParameter("cmd")+ "]";
  }
}

