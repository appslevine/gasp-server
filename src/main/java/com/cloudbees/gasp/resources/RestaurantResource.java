package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/restaurants")
public class RestaurantResource {
    @Inject
    EntityManager manager;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public Restaurant get(@PathParam("id") int id) {
        return manager.find(Restaurant.class,id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public void create(Restaurant r) {
        manager.persist(r);
    }
}
