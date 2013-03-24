CEDSIF Classifiers Module
=========================

Author: Mauricio T. Ferraz.

What is it?

This is a compliant Java EE 6 application using JSF 2.0, CDI 1.0, EJB 3.1, JPA 2.0 and Bean Validation 1.0 with Maven 3 building tool ready to Jboss AS 7.x

System requirements

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss AS 7.x.

Start JBoss AS 7

For Linux:   JBOSS_HOME/bin/standalone.sh

Build and Deploy

mvn clean package jboss-as:deploy

Access the application

The application will be running at the following URL: http://apphost:8080/classifiers

Run the Arquillian Tests

mvn clean test -Parq-jbossas-managed

Undeploy the Archive

mvn jboss-as:undeploy

Debug the Application

mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc

Git Tips:
Making code changes:

git add .
git commit -m 'My changes'
git push

AS7 Tips:
To start a cluster env... on standalone mode:

./standalone.sh -c standalone-ha.xml -b 192.168.0.20 -u 230.0.0.4 -Djboss.server.base.dir=../standalone-node1 -Djboss.node.name=node1 -Djboss.socket.binding.port-offset=100
./standalone.sh -c standalone-ha.xml -b 192.168.0.20 -u 230.0.0.4 -Djboss.server.base.dir=../standalone-node2 -Djboss.node.name=node2 -Djboss.socket.binding.port-offset=200

