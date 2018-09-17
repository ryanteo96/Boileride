#!/bin/bash
pkill java
export CLASSPATH=~/CS408-project/src/mysql-connector-java-8.0.12.jar:$CLASSPATH
export CLASSPATH=~/CS408-project/src/json-simple-1.1.1.jar:$CLASSPATH
export CLASSPATH=~/CS408-project/src/javax.mail.jar:$CLASSPATH
export CLASSPATH=~/CS408-project/src/activation.jar:$CLASSPATH
javac BoilerideServer.java
java BoilerideServer
