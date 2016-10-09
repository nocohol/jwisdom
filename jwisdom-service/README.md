#jwisdom service
This is a project to practice how to use zookeeper, cuactor and cxf rs to build a auto registered / discovered, 
restful style web services.
It mainly referred to [this link](https://github.com/reta/service-autodiscovery).

To run this project, below prerequisites are needed:

* JDK 1.7 or above 
* The stable zookeeper should be installed in your machine.

*****

Below is the simply introduction for start zookeeper server:

* download latest stable version of zookeeper from [http://zookeeper.apache.org/releases.html](http://zookeeper.apache.org/releases.html). 
* Unzip the tar file somewhere, e.g. /opt/zookeeper-3.4.9
* Go into /opt/zookeeper-3.4.9/conf, then create a configuration file naming zoo.cfg or you can rename zoo_sample.cfg directly to zoo.cfg.
   Add below content into zoo.cfg:
   tickTime = 2000
   dataDir = /var/lib/zookeeper
   clientPort =  2181
* Now start zookeeper with command:
   `sudo bin/zkServer.sh start`
* Connect to zookeeper server with CLI:
   `sudo bin/zkCli.sh -server 127.0.0.1:2181``
* Build project with maven command:
   `mvn clean package` 
    It will generate the single jar under target/jwisdom-services.jar
* Start two service instances:
   `java -jar target/jwisdom-services.jar 8080`
   `java -jar target/jwisdom-services.jar 8081`
* Run ClientApplication to test the services.
