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
import java.util.Collection;

/**
 * @author Kohsuke Kawaguchi
 */
@Entity
public class Tavern {
    @JsonProperty
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    /**
     * Human readable name of the tavern.
     */
    @JsonProperty
    @Column
    private String name;

    /**
     * URL of the tavern website.
     */
    @JsonProperty
    @Column
    private String website;

    /**
     * Google Places API Id for the tavern.
     */
    @JsonProperty
    @Column(unique = true)
    private String placesId;

    /**
      Added new field  'event'  = none, wine,beer,spirits,happyhour
     */
    @JsonProperty
    @Column
    private String event;

    @OneToMany(mappedBy= "tavern",cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JsonIgnore
    private Collection<Review> reviews;


    public Tavern() {
    }

    public Tavern(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return "/taverns/"+id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPlacesId() {
        return placesId;
    }

    public void setPlacesId(String address) {
        this.placesId = address;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

}