curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/reviews << EOF
    {
        "star": 4,
        "comment": "This is my favorite place",
        "user": "http://localhost:8080/users/1",
        "tavern": "http://localhost:8080/taverns/1"
    }
    EOF
