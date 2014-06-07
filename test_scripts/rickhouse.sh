curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/taverns << EOF 
    '{
      "name":"Rickhouse Bar",
      "website":"http://rickhousebar.com/",
      "placesId":"741018f384fe613129a17228d7163627c81c8413"
    }'
    
EOF
