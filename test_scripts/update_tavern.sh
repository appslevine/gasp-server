curl -i -X PUT -d @- -H "Content-Type:application/json" http://localhost:8080/taverns/4 << EOF
    {
      "name":"Sumika",
      "website":"http://new.sumikagrill.com/",
      "placesId":"218713866ea945402b0967d3192fa537cde42f36"
    }
    EOF

