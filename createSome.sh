#!/bin/sh
URL=http://localhost:8080
curl -d '{"name":"bar","url":"http://www.yahoo.com"}' -H "Content-Type: application/json" $URL/restaurants
curl $URL/restaurants/1

echo "\nUpdating"
curl -X PUT -d '{"name":"pub","url":"http://www.yahoo.com"}' -H "Content-Type: application/json" $URL/restaurants/1
curl $URL/restaurants/1

echo "\nDeleting"
curl -X DELETE $URL/restaurants/1
curl $URL/restaurants/1
