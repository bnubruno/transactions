#!/bin/sh
#set -e

java -jar /app/app.jar \
  --spring.profiles.active="production"