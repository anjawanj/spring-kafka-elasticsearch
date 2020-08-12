#!/usr/bin/env bash

source scripts/my-functions.sh

echo
echo "Starting eureka..."

docker run -d   --rm --name eureka \
  -p 8761:8761  \
  --health-cmd="curl -f http://localhost:8761/actuator/health || exit 1" \
  docker.mycompany.com/eureka-server:1.0.0

wait_for_container_log "eureka" "Started"

echo
echo "Starting producer-api..."

docker run -d --rm --name producer-api \
  -p 9080:8080  \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka -e \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" \
  docker.mycompany.com/producer-api:1.0.0

wait_for_container_log "producer-api" "Started"

echo
echo "Starting fclient..."

docker run -d --rm --name client-app \
  -p 8080:8080  \
  -e KAFKA_HOST=kafka -e KAFKA_PORT=9092 -e EUREKA_HOST=eureka -e \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1"\
  docker.mycompany.com/client-app:1.0.0

wait_for_container_log "client" "Started"

printf "\n"
printf "%14s | %37s |\n" "Application" "URL"
printf "\n"
