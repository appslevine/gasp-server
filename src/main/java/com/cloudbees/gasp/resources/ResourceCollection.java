package com.cloudbees.gasp.resources;

import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class ResourceCollection<T> {
    private final Class<T> type;

    @Inject
    protected EntityManager manager;


    public ResourceCollection(Class<T> type) {
        this.type = type;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    public T get(@PathParam("id") int id) {
        return manager.find(type,id);
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        manager.remove(get(id));
        return Response.ok().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<T> list() {
        return manager.createQuery(
                String.format("SELECT u FROM %s u", type.getName()),type)
                .getResultList();
    }
}
