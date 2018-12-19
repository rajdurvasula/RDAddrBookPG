#!/bin/bash
host_mapping=$(grep "$PG_HOST" /etc/hosts | wc | awk '{ print $1}')
#echo "host_mapping = $host_mapping"
if [ "$host_mapping" != "1" ]; then
  echo "$PG_HOST_IP $PG_HOST" >> /etc/hosts
fi
cat /etc/hosts
