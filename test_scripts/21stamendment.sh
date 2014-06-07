curl -i -d @- -H "Content-Type:application/json" -X POST http://localhost:8080/taverns << EOF 
    '{
      "name":"21st Amendment Brewery",
      "website":"http://21st-amendment.com/",
      "placesId":"1a2b945f442515a064543cdb049db0ac8d5f1654"
    }'
EOF
    
    

