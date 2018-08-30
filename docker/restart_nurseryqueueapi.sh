#!/usr/bin/env bash

if [ -f clean_install.sh ]; then
   source clean_install.sh;
fi

# war
echo -e "Update NurseryQueueApi war..."
cp -f ../NurseryQueueApi/target/NurseryQueueApi.war NurseryQueueApi/NurseryQueueApi.war

# restart
docker-compose stop -t 1 nurseryqueueapi
docker-compose build nurseryqueueapi
docker-compose create nurseryqueueapi
docker-compose start nurseryqueueapi