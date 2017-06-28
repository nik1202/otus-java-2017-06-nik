#!/bin/bash
MEMORY="-Xms512m -Xmx512m"
GC="-XX:+UseSerialGC"
java $MEMORY $GC -jar $1