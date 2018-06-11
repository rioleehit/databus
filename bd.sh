#!/bin/bash
rm -f databus2-hqxc-relay-pkg-2.0.0.tar.gz
rm -f databus2-hqxc-client-pkg-2.0.0.tar.gz
gradle -Dopen_source=true assemble --stacktrace 
cp ./build/databus2-hqxc-relay-pkg/distributions/databus2-hqxc-relay-pkg-2.0.0.tar.gz ./databus2-hqxc-relay-pkg-2.0.0.tar.gz
cp ./build/databus2-hqxc-client-pkg/distributions/databus2-hqxc-client-pkg-2.0.0.tar.gz ./databus2-hqxc-client-pkg-2.0.0.tar.gz