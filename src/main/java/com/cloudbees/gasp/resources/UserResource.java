package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Restaurant;
import com.cloudbees.gasp.models.User;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Kohsuke Kawaguchi
 */
@Path("/users")
public class UserResource {
    @Inject
    EntityManager manager;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public User get(@PathParam("id") int id) {
        return manager.find(User.class,id);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public User update(@PathParam("id") int id, Restaurant src) {
        User r = get(id);
        r.setName(src.getName());
        return manager.find(User.class,id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") int id) {
        manager.remove(get(id));
        return Response.ok().build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public Response create(User r) {
        manager.persist(r);
        return Response.created(URI.create(r.getUrl())).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> list() {
        return manager.createQuery(
                String.format("SELECT u FROM %s u", User.class.getName()),User.class)
                .getResultList();
    }
}
