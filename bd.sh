#!/bin/bash
rm -f databus2-example-relay-pkg-2.0.0.tar.gz
rm -f databus2-example-client-pkg-2.0.0.tar.gz
gradle -Dopen_source=true assemble
cp ./build/databus2-example-relay-pkg/distributions/databus2-example-relay-pkg-2.0.0.tar.gz ./databus2-example-relay-pkg-2.0.0.tar.gz
cp ./build/databus2-example-client-pkg/distributions/databus2-example-client-pkg-2.0.0.tar.gz ./databus2-example-client-pkg-2.0.0.tar.gz