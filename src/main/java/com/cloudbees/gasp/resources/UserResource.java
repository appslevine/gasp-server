/*
 * Copyright (c) 2013 CloudBees
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudbees.gasp.resources;

import com.cloudbees.gasp.models.Tavern;
import com.cloudbees.gasp.models.Review;
import com.cloudbees.gasp.models.User;
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
@Path("/users")
public class UserResource extends ResourceCollection<User> {
    public UserResource() {
        super(User.class);
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("{id}")
    @Transactional
    public User update(@PathParam("id") int id, Tavern src) {
        User r = get(id);
        r.setName(src.getName());
        return manager.find(User.class,id);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Transactional
    public Response create(User r) {
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
