#!/bin/bash

docker-compose -f postgres.yaml up -d
sleep 10s
psql -f setup.sql "host=localhost port=5432 user=@flyway.user@ password=@flyway.password@ dbname=postgres"