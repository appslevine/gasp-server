curl -i -d @- -H "Content-Type:application/json" -X POST http://localhost:8080/taverns << EOF 
    '{
      "name":"The Pressclub SF",
      "website":"http://www.pressclubsf.com/",
      "placesId":"34239fd357d200b3733f28743c86b0175bb7c9ac"
    }'
 EOF 
