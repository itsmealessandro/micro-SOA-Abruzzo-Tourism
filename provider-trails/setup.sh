#!/bin/bash

echo "hello from setup.sh"

cd ./app/

#ls -la
echo "----------------------------"
echo "lunching the console"

java -jar org.eclipse.osgi_3.21.0.v20240717-2103.jar -console
