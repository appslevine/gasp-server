#!/bin/sh
URL=http://localhost:8080
curl --verbose -d '{"name":"bar","website":"http://www.yahoo.com"}' -H "Content-Type: application/json" $URL/restaurants
curl $URL/restaurants/1

curl $URL/restaurants

echo "\nUpdating"
curl -X PUT -d '{"name":"pub","website":"http://www.yahoo.com"}' -H "Content-Type: application/json" $URL/restaurants/1
curl $URL/restaurants/1

echo "\nDeleting"
curl -X DELETE $URL/restaurants/1
curl $URL/restaurants/1
