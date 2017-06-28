#!/bin/bash
MEMORY="-Xms512m -Xmx512m"
GC="-XX:+UseParallelGC -XX:+UseParallelOldGC"
java $MEMORY $GC -jar $1