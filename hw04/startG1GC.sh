#!/bin/bash
MEMORY="-Xms512m -Xmx512m"
GC="-XX:+UseG1GC"
java $MEMORY $GC -jar $1