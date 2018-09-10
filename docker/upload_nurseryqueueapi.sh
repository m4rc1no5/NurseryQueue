#!/usr/bin/env bash

# war
echo -e "Update NurseryQueueApi war..."
docker cp ../NurseryQueueApi/target/NurseryQueueApi.war nurseryqueueapi:/opt/jboss/wildfly/standalone/deployments/NurseryQueueApi.war