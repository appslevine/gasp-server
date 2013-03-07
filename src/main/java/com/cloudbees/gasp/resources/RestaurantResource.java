package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/restaurants/{id}")
public class RestaurantResource {
    @GET
    @Produces("application/json")
    public Restaurant get() {
        Restaurant r = new Restaurant();
        r.setName("Foo");
        r.setUrl("http://kohsuke.org/");
        return r;
    }
}
