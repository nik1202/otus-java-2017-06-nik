#!/bin/bash
MEMORY="-Xms512m -Xmx512m"
GC="-XX:+UseSerialGC"
java $GC -jar ./target/hw04.jar