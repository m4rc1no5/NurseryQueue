FROM m4rc1no5/wildfly:14.0.0.Final
MAINTAINER marcin.zaremba@gmail.com

ADD --chown=jboss:0 modules/ $JBOSS_HOME/modules/system
ADD --chown=jboss:0 standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml

# deploy
COPY NurseryQueueApi.war $JBOSS_HOME/standalone/deployments/