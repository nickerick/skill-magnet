#!/bin/bash
cd /home/ec2-user/dev

aws s3 cp s3://skillmagnet/server-0.0.1-SNAPSHOT.jar server.jar
sudo java -jar -Dspring.config.location=application.properties server.jar > /dev/null 2> /dev/null < /dev/null &