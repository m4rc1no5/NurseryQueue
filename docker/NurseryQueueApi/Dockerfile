FROM jboss/wildfly:13.0.0.Final
MAINTAINER marcin.zaremba@gmail.pl

USER root

WORKDIR /opt
RUN yum install wget -y
RUN wget https://download.java.net/java/GA/jdk9/9.0.4/binaries/openjdk-9.0.4_linux-x64_bin.tar.gz
RUN tar zxvf openjdk-9.0.4_linux-x64_bin.tar.gz
RUN rm -f openjdk-9.0.4_linux-x64_bin.tar.gz
ENV JAVA_HOME=/opt/jdk-9.0.4
WORKDIR ~/

RUN localedef -i pl_PL -c -f UTF-8 -A /usr/share/locale/locale.alias pl_PL.UTF-8
ENV LANG pl_PL.UTF-8
ENV LANGUAGE pl_PL:pl
ENV LC_ALL pl_PL.UTF-8

RUN rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/Europe/Warsaw /etc/localtime

#add user to mgmt.properties so that we can actually access admin console using LDAP auth
RUN $JBOSS_HOME/bin/add-user.sh marceen marceen --silent

# deploy
COPY NurseryQueueApi.war $JBOSS_HOME/standalone/deployments/