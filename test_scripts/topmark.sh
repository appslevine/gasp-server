curl -i -d @- -H "Content-Type:application/json" -X POST http://localhost:8080/taverns << EOF 
    '{
      "name":"Top of the Mark",
      "website":"http://www.intercontinentalmarkhopkins.com/top-of-the-mark.aspx/",
      "placesId":"f375972a24d3db3afe06843dc57c50f6168faff5"
    }'
    
EOF
