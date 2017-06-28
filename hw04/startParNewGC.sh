#!/bin/bash
MEMORY="-Xms512m -Xmx512m"
GC="-XX:+UseParNewGC -XX:+UseConcMarkSweepGC"
java $MEMORY $GC -jar $1