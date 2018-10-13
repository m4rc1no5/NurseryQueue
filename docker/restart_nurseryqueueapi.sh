#!/usr/bin/env bash

# war
echo -e "Update NurseryQueueApi war..."
cp -f ../NurseryQueueApi/target/NurseryQueueApi.war NurseryQueueApi/NurseryQueueApi.war

# restart
docker-compose stop -t 1 nurseryqueueapi
docker-compose build nurseryqueueapi
docker-compose up nurseryqueueapi