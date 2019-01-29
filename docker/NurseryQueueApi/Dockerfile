FROM m4rc1no5/wildfly:15.0.1.Final
MAINTAINER marcin.zaremba@gmail.com

ADD --chown=jboss:0 modules/ $JBOSS_HOME/modules/system
ADD --chown=jboss:0 standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml

RUN $JBOSS_HOME/bin/add-user.sh m4rc1no5 m4rc1no5 --silent

# deploy
COPY NurseryQueueApi.war $JBOSS_HOME/standalone/deployments/