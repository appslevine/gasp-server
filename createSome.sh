#!/bin/bash -e
[ "$URL" == "" ] && URL=http://localhost:8080

function create {
    curl -s -S -i -H "Content-Type: application/json" "$@" | tee /tmp/$$
    res=$(cat /tmp/$$ | grep Location | sed -e "s/Location: //g" | tr -d '\r')
    echo Created $res
    show $res
}

function show {
    curl -s -S $1 | json_xs
    echo
}

create -d '{"name":"bar","website":"http://www.yahoo.com"}' $URL/restaurants
restaurant=$res

show $URL/restaurants

echo "Updating"
curl -X PUT -d '{"name":"pub","website":"http://www.yahoo.com"}' -H "Content-Type: application/json" $restaurant
show $restaurant


echo Creating user
create -d '{"name":"someone"}' $URL/users
user=$res

echo Creating a review from $user to $restaurant
create -d "{\"star\":3, \"comment\":\"This place is great\", \"user\":\"$user\", \"restaurant\":\"$restaurant\"}" $URL/reviews

show $URL/reviews
show $restaurant/reviews
show $user/reviews

#echo "\nDeleting"
#curl -X DELETE $res
#curl $res
