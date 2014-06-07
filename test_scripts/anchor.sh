curl -i d @- -H "Content-Type:application/json" -X POST http://localhost:8080/taverns << EOF 
    '{
      "name":"Anchor Brewing Company",
      "website":"http://www.anchorbrewing.com/",
      "placesId":"c1c4eb58d9464534bb426042172104c8745ccac9"
    }'
EOF
    

