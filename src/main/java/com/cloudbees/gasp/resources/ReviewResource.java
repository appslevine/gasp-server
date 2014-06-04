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
        // the deserialized review object contains a bogus Tavern and User, which needs to be fixed
        r.setUser(manager.find(User.class,r.getUser().getId()));
        r.setTavern(manager.find(Tavern.class,r.getTavern().getId()));

        manager.persist(r);
        return Response.created(URI.create(r.getId()+"")).build();
    }
}
