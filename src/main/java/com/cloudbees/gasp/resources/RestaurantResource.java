package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;
import com.cloudbees.gasp.models.Review;
import com.google.inject.persist.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/restaurants")
public class RestaurantResource extends ResourceCollection<Restaurant> {

    public RestaurantResource() {
        super(Restaurant.class);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public Restaurant update(@PathParam("id") int id, Restaurant src) {
        Restaurant r = get(id);
        r.setWebsite(src.getWebsite());
        r.setName(src.getName());
        return manager.find(Restaurant.class,id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public Response create(Restaurant r) {
        manager.persist(r);
        return Response.created(URI.create(r.getId()+"")).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}/reviews")
    public Collection<Review> reviews(@PathParam("id") int id) {
        return get(id).getReviews();
    }
}
