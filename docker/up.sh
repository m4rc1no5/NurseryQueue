#!/usr/bin/env bash

# war
echo -e "Update NurseryQueueApi war..."
cp -f ../NurseryQueueApi/target/NurseryQueueApi.war NurseryQueueApi/NurseryQueueApi.war

# up
if [ $# -gt 0 ]; then
    docker-compose up "$@"
else
    docker-compose up
fi