#!/bin/sh
URL=http://localhost:8080
curl -d '{"name":"bar","url":"http://www.yahoo.com"}' -H "Content-Type: application/json" $URL/restaurants
curl $URL/restaurants/1
