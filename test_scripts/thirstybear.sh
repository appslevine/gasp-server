curl -i -d  @- -H "Content-Type:application/json" -X POST http://localhost:8080/taverns << EOF 
    '{
      "name":"ThirstyBear Brewing Company",
      "website":"http://http://thirstybear.com/",
      "placesId":"e114ca9418e7a41938cbe515f0bc1052986ff6a3"
    }'
  EOF 


