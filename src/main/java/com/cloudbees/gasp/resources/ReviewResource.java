package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;
import com.cloudbees.gasp.models.Review;
import com.cloudbees.gasp.models.User;
import com.google.inject.persist.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/reviews")
public class ReviewResource extends ResourceCollection<Review> {
    public ReviewResource() {
        super(Review.class);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public Response create(Review r) {
        // the deserialized review object contains a bogus Restaurant and User, which needs to be fixed
        r.setUser(manager.find(User.class,r.getUser().getId()));
        r.setRestaurant(manager.find(Restaurant.class,r.getRestaurant().getId()));

        manager.persist(r);
        return Response.created(URI.create(r.getId()+"")).build();
    }
}
