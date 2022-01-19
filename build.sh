#!/bin/bash
set -e

# Build the services
pushd ReportService
./build.sh
popd
