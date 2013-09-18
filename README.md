        GGGGGGGGGGGGG               AAA                 SSSSSSSSSSSSSSS PPPPPPPPPPPPPPPPP    !!!
     GGG::::::::::::G              A:::A              SS:::::::::::::::SP::::::::::::::::P  !!:!!
    GG:::::::::::::::G             A:::::A            S:::::SSSSSS::::::SP::::::PPPPPP:::::P !:::!
    G:::::GGGGGGGG::::G            A:::::::A           S:::::S     SSSSSSSPP:::::P     P:::::P!:::!
    G:::::G       GGGGGG           A:::::::::A          S:::::S              P::::P     P:::::P!:::!
    G:::::G                        A:::::A:::::A         S:::::S              P::::P     P:::::P!:::!
    G:::::G                       A:::::A A:::::A         S::::SSSS           P::::PPPPPP:::::P !:::!
    G:::::G    GGGGGGGGGG        A:::::A   A:::::A         SS::::::SSSSS      P:::::::::::::PP  !:::!
    G:::::G    G::::::::G       A:::::A     A:::::A          SSS::::::::SS    P::::PPPPPPPPP    !:::!
    G:::::G    GGGGG::::G      A:::::AAAAAAAAA:::::A            SSSSSS::::S   P::::P            !:::!
    G:::::G        G::::G     A:::::::::::::::::::::A                S:::::S  P::::P            !!:!!
    G:::::G       G::::G    A:::::AAAAAAAAAAAAA:::::A               S:::::S  P::::P             !!!
    G:::::GGGGGGGG::::G   A:::::A             A:::::A  SSSSSSS     S:::::SPP::::::PP
    GG:::::::::::::::G  A:::::A               A:::::A S::::::SSSSSS:::::SP::::::::P           !!!
     GGG::::::GGG:::G A:::::A                 A:::::AS:::::::::::::::SS P::::::::P          !!:!!
        GGGGGG   GGGGAAAAAAA                   AAAAAAASSSSSSSSSSSSSSS   PPPPPPPPPP           !!!

Great local restaurant search that takes your breath away!

> <img src="http://www.cloudbees.com/sites/all/themes/custom/cloudbees_zen/css/bidesign/_ui/images/logo.png"/>
>
> <b>Note</b>: <i>This repo is part of the Gasp demo project - a showcase of <a href="https://developer.cloudbees.com/bin/view/Mobile">cloudbees mobile services</a>.
> You can see the big picture of the <a href="http://mobilepaas.cloudbees.com">showcase here</a>.
> Feel free to fork and use this repo as a template.</i>

This is a small Java RESTful web application for demonstrating the CloudBees platform.
The app is a hypothetical Yelp-like restaurant recommendation application.
It has users, restaurants, and reviews that users post to restaurants.

It contains enough common building blocks (JAX-RS, Guice, JPA, and Jackson) to make
it look like a standard Java web application (war that talks to database). It is also
small enough to serve as an example.

# Endpoints
## Restaurants
### Create a new restaurant
Post the following JSON to `/restaurant` to create a new restaurant:

    % curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/restaurants << EOF
    {
      "name":"Sumika",
      "website":"http://sumikagrill.com/",
      "address":"236 Central Plaza Los Altos, CA 94022"
    }
    EOF

The response will indicate the URL of the restaurant resource:

    HTTP/1.1 201 Created
    Location: http://localhost:8080/restaurants/4
    Content-Length: 0

### Update restaurant
`PUT` the same JSON format to a restaurant URL and you'll update the restaurant information:

    % curl -i -X PUT -d @- -H "Content-Type:application/json" http://localhost:8080/restaurants/4 << EOF
    {
      "name":"Sumika",
      "website":"http://new.sumikagrill.com/",
      "address":"236 Central Plaza Los Altos, CA 94022"
    }
    EOF

The response will contain the update restaurant information:

    HTTP/1.1 200 OK
    Content-Type: application/json

    {
      "id":4,
      "name":"Sumika",
      "website":"http://new.sumikagrill.com/",
      "address":"236 Central Plaza Los Altos, CA 94022",
      "reviews":[],
      "url":"/restaurants/4"
    }

### Read restaurant info
Make a `GET` call to the same URL as above and you retrieve the restaurant information:

    % curl -i http://localhost:8080/restaurants/4

And here is the same response:

    HTTP/1.1 200 OK
    Content-Type: application/json

    {
      "id":4,
      "name":"Sumika",
      "website":"http://new.sumikagrill.com/",
      "address":"236 Central Plaza Los Altos, CA 94022",
      "url":"/restaurants/4"
    }

### List up all the reviews
To list up all the reviews associated with a restaurant, make a `GET` request
to the `/reviews` sub-resource:

    % curl -i http://localhost:8080/restaurants/4/reviews
    HTTP/1.1 200 OK
    Content-Type: application/json

    [
       {
          "comment" : "This place is great",
          "star" : 5,
          "user" : "/users/5",
          "restaurant" : "/restaurants/4",
          "url" : "/reviews/2",
          "id" : 2
       },
       {
          "comment" : "Beware that the place is closed Monday",
          "star" : 4,
          "user" : "/users/7",
          "restaurant" : "/restaurants/4",
          "url" : "/reviews/3",
          "id" : 3
       }
    ]


### Delete a restaurant
Make `DELETE` call to the URL and its record will be discarded:

    % curl -i -X DELETE http://localhost:8080/restaurants/4

The response will simply indicate that the deletion was performed:

    HTTP/1.1 200 OK
    Content-Length: 0



## Users
Creating, updating, reading, and deleting the user is exactly the same as restaurants
(including reviews that a given user has created),
except that the URL is `/users` instead of `/restaurants`, and the format of JSON is
as follows:


    % curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/users << EOF
    {
      "name":"Kohsuke Kawaguchi"
    }
    EOF

## Reviews
### Post a new review
To post a new URL, send a `POST` request to `/reviews`:

    % curl -i -d @- -H "Content-Type:application/json" http://localhost:8080/reviews << EOF
    {
        "star": 4,
        "comment": "This is my favorite place",
        "user": "http://localhost:8080/users/1",
        "restaurant": "http://localhost:8080/restaurants/4"
    }
    EOF

The response will indicate the URL of the newly created review:

    HTTP/1.1 201 Created
    Location: http://localhost:8080/reviews/4

### Update, read, and delete a review
These operations follow the same pattern as users and restaurants.

### Listing all the reviews
Send a `GET` request and you can list up all the reviews ever posted to all the restaurants

    curl http://localhost:8080/reviews

# Developing with this application
## Running locally
Check out this source repository, then run the following command to launch this app
in http://localhost:8080/:

    mvn jetty:run

This will run Gasp with local H2 database in `./work.h2.db`. If you want to wipe out
the database, stop the server and delete this file.

## Deploying to CloudBees
First, create a new database for this app from the web UI. It doesn't matter
what the name is or what the password is.

Likewise, run the following command to deploy this app to CloudBees

    mvn bees:deploy -Dbees.appid=gasp

If this is the first time you deploy this app, link the database and restart
the application:

    bees app:bind -db kohsuke/gasp-db -a kohsuke/gasp -as db
    bees app:restart kohsuke/gasp

(`kohsuke/gasp-db` is the name of the database you created.
`kohsuke/gasp` is the name of the application that you deployed.)
