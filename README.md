<p align="center">
<a href="https://www.aliyun.com/product/graphcompute"><img src="https://raw.githubusercontent.com/aliyun/alibabacloud-graphcompute-java-sdk/master/src/resources/GraphCompute-blue.png"></a>
</p>

<h1 align="center">GraphCompute SDK for Java</h1>
<p align="center">

The GraphCompute SDK for Java allows to access [GraphCompute Service](https://www.aliyun.com/product/graphcompute) on Alibaba Cloud. You can access Graph Compute service without the need to generate accesskey-related signature manually. This README document introduces how to obtain and call GraphCompute SDK for Java. If you have any problem while using GraphCompute SDK for Java, please [submit an issue](https://github.com/aliyun/alibabacloud-graphcompute-java-sdk/issues/new).

## Requirements

- To use GraphCompute SDK for Java, you must have an Alibaba Cloud account as well as an `AccessKey ID` and an `AccessKey Secret`. Create and view your AccessKey on the [RAM console](https://ram.console.aliyun.com "RAM console") or contact your system administrator.
- To use  GraphCompute SDK for Java to access the APIs of a product, you must first activate the product on the [Alibaba Cloud console](https://homenew.console.aliyun.com/) if required.
- The GraphCompute SDK for Java requires JDK 1.8 or later.


## Installation

If you use Apache Maven to manage Java projects, you need to add following corresponding dependency to the pom.xml files of the projects. 

```xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-graphcompute</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Examples

The following code example shows several steps to create a Graph **Client**  using GraphCompute SDK for Java :

1. Prepare your accessKey and accessSecret
2. Prepare your instance domain and port
3. Prepare `MessageSerializer ` for serialization
4. Create Gremlin `Cluster` and `Client`

```java
import com.alibaba.maxgraph.credentials.CredentialsManager;
import com.alibaba.maxgraph.io.MaxGraphIORegistry;
import org.apache.tinkerpop.gremlin.driver.*;
import org.apache.tinkerpop.gremlin.driver.MessageSerializer;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV1d0;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;

public class GraphComputeJavaSDKExample {
    public static void main(String[] args) {
        Cluster cluster;
        Client client;
        CredentialsManager credentialsManager;
      
      	String accessKey = "This is your accessKey";
        String accessSecret = "This is your accessSecret";

        String instanceDomain = "This is your instance domain";
        int instanceDomainPort = 80; //This is your port of instance domain
        
        credentialsManager = new CredentialsManager(accessKey, accessSecret);

        GryoMapper.Builder kryo = 
          GryoMapper.build().addRegistry(MaxGraphIORegistry.getInstance());
        MessageSerializer serializer = 
          new GryoMessageSerializerV1d0(kryo);
        try {
            cluster = Cluster.build()
                    .addContactPoint(instanceDomain)
                    .port(instanceDomainPort)
                    .serializer(serializer)
                    .credentials(credentialsManager.getUserName(), 
                                 credentialsManager.getPassword())
                    .create();
        } catch (Exception e) {
            throw new IllegalArgumentException("build credentials fail", e);
        }
        client = cluster.connect();
      	String query="your Gremlin Query";
        ResultSet resultSet = client.submit("g.V()");
      	...
        //don't forget close the connection
        client.close();
        cluster.close();
    }
}
```

You can find more interesting examples in `com.alibaba.maxgraph.examples` package. :)

## License

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)

