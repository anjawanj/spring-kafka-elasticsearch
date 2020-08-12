#!/usr/bin/env bash

./mvnw clean compile jib:dockerBuild --projects eureka-server
./mvnw clean compile jib:dockerBuild --projects producer-api
./mvnw clean compile jib:dockerBuild --projects client-app
