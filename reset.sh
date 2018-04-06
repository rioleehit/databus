#!/bin/bash
echo "Hello World !"
./stop.sh
rm -rf ./client
rm -rf ./relay
mkdir client
mkdir relay
tar -zxvf databus2-example-client-pkg-2.0.0.tar.gz -C client
tar -zxvf databus2-example-relay-pkg-2.0.0.tar.gz -C relay
echo 'ok'
