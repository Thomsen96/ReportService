#!/bin/bash
set -e
mvn clean package

docker build . -t report-service
