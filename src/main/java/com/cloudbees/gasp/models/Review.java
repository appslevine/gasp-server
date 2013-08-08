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

package com.cloudbees.gasp.models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

/**
 * @author Kohsuke Kawaguchi
 */
@Entity
public class Review {
    @ManyToOne(optional=false)
    @JoinColumn
    @JsonIgnore // serialized as reference
    private User user;

    @ManyToOne(optional=false)
    @JoinColumn
    @JsonIgnore // serialized as reference
    private Restaurant restaurant;

    @Column
    @JsonProperty
    private int star;

    @Column
    @JsonProperty
    private String comment;

    @JsonProperty
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return "/reviews/"+id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant review) {
        this.restaurant = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

// On JSON, serialize as reference

    @JsonProperty("restaurant")
    public String getRestaurantUrl() {
        return restaurant.getUrl();
    }

    public void setRestaurantUrl(String url) {
        restaurant = new Restaurant(toId(url));
    }

    @JsonProperty("user")
    public String getUserUrl() {
        return user.getUrl();
    }

    public void setUserUrl(String url) {
        user = new User(toId(url));
    }

    private static int toId(String url) {
        return Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
    }
}
