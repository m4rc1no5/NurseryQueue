#!/usr/bin/env bash

if [ -f clean_install.sh ]; then
   source clean_install.sh;
fi

# war
echo -e "Update NurseryQueueApi war..."
docker cp ../NurseryQueueApi/target/NurseryQueueApi.war nurseryqueueapi:/opt/jboss/wildfly/standalone/deployments/NurseryQueueApi.war