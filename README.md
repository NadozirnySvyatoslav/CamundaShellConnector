# Camunda Shell Connector

Execute commands in local filesystems

## Implementing connector to the server Camunda

1. Put  shell-connector-1.0.jar  to /var/lib/tomcat9/lib
2. Restart tomcat server
3. Check for logs in /var/lib/tomcat9/logs/catalina.*
```
29-Oct-2019 16:36:13.643 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo ENGINE-12003 Plugin 'ConnectProcessEnginePlugin' activated on process engine 'default'
29-Oct-2019 16:36:13.648 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo CNCT-01004 Discovered provider for connector id 'rabbitmq' and class 'com.ktc.rabbitmq.RMQConnector': 'com.ktc.rabbitmq.RMQConnectorProvider'
29-Oct-2019 16:36:13.650 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo CNCT-01004 Discovered provider for connector id 'shell' and class 'com.ktc.camunda.ShellConnector': 'com.ktc.camunda.ShellConnectorProvider'
29-Oct-2019 16:36:13.836 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo CNCT-01004 Discovered provider for connector id 'http-connector' and class 'org.camunda.connect.httpclient.impl.HttpConnectorImpl': 'org.camunda.connect.
httpclient.impl.HttpConnectorProviderImpl'
29-Oct-2019 16:36:13.840 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo CNCT-01004 Discovered provider for connector id 'soap-http-connector' and class 'org.camunda.connect.httpclient.soap.impl.SoapHttpConnectorImpl': 'org.ca
munda.connect.httpclient.soap.impl.SoapHttpConnectorProviderImpl'
29-Oct-2019 16:36:17.889 INFO [main] org.camunda.commons.logging.BaseLogger.logInfo ENGINE-00001 Process Engine default created.

```

Line "Discovered provider for connector id 'shell' and class 'com.ktc.camunda.ShellConnector': 'com.ktc.camunda.ShellConnectorProvider'" should be present without errors

## Test connector

1.  Deploy BPMN, might be used from demo/demo.bpmn
![BPMN process](/demo/demo_bpmn.png)

2. Start process from Camunda Tasklist
3. Look to the logs

```
29-Oct-2019 16:45:20.270 INFO [http-nio-8080-exec-9] com.ktc.camunda.LoggerDelegate.execute

  ... LoggerDelegate invoked by processDefinitionId=shell_exec:7:b59a0096-fa5a-11e9-833a-3ae05c2d1ee0, activtyId=ServiceTask_0ztjd7d, activtyName='Log input data', processInstanceId=baf59d8a-fa5a-11e9-833a-3ae05c2d1ee0, businessKey=asdas
dsadasd, executionId=baf59d8a-fa5a-11e9-833a-3ae05c2d1ee0


29-Oct-2019 16:45:20.270 INFO [http-nio-8080-exec-9] com.ktc.camunda.LoggerDelegate.execute

  {
  output => Value 'total 28
drwxr-xr-x  7 tomcat tomcat 4096 Oct 29 16:36 .
drwxr-xr-x 44 root   root   4096 Oct 29 09:57 ..
lrwxrwxrwx  1 tomcat tomcat   12 Sep 11 22:47 conf -> /etc/tomcat9
drwxr-xr-x  2 tomcat tomcat 4096 Oct 29 16:35 lib
lrwxrwxrwx  1 tomcat tomcat   17 Sep 11 22:47 logs -> ../../log/tomcat9
drwxr-xr-x  2 root   root   4096 Oct 29 16:36 policy
drwxr-xr-x 11 tomcat tomcat 4096 Oct 29 09:57 webapps
drwxr-xr-x 11 root   root   4096 Oct 10 14:49 webapps.bak
drwxrwxr-x 12 tomcat tomcat 4096 Oct 10 14:49 webapps.bak2
lrwxrwxrwx  1 tomcat tomcat   19 Sep 11 22:47 work -> ../../cache/tomcat9
' of type 'PrimitiveValueType[string]', isTransient=false
  code => Value '0' of type 'PrimitiveValueType[integer]', isTransient=false
  error => Value '' of type 'PrimitiveValueType[string]', isTransient=false
}


```

## Connector configuration

Input:
cmd - command line to execute (mandatory)
input - input buffer to send to the command line
dir - directory to start command

Output:
output - output buffer from command execution
code - execution exit status code
error - error buffer from command execution
