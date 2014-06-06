curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/taverns << EOF
    {
      "name":"Tadich Grill",
      "website":"http://tadichgrill.com/",
      "placesId":"35f2341ce16ce57e6b3992b56f738e1f9f112a2d"
    }
    EOF

