package com.ktc.camunda;

import org.camunda.connect.spi.ConnectorProvider;

public class ShellConnectorProvider implements ConnectorProvider{
  public String getConnectorId() {
    return ShellConnector.ID;
  }

  public ShellConnector createConnectorInstance() {
    return new ShellConnector();
  }

}
