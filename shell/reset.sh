#!/bin/bash
echo "Hello World !"
./stop.sh
mv ./relay/maxScn ./maxScn.tmp
rm -rf ./client
rm -rf ./relay
mkdir client
mkdir relay
tar -zxvf databus2-hqxc-client-pkg-2.0.0.tar.gz -C client
tar -zxvf databus2-hqxc-relay-pkg-2.0.0.tar.gz -C relay
mv ./maxScn.tmp ./relay/maxScn
echo 'ok'