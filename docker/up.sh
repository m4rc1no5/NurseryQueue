#!/usr/bin/env bash

if [ -f clean_install.sh ]; then
   source clean_install.sh;
fi

# war
echo -e "Update NurseryQueueApi war..."
cp -f ../NurseryQueueApi/target/NurseryQueueApi.war NurseryQueueApi/NurseryQueueApi.war

# up
if [ $# -gt 0 ]; then
    docker-compose up "$@"
else
    docker-compose up
fi