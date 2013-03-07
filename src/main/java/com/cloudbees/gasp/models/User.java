package com.cloudbees.gasp.models;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Kohsuke Kawaguchi
 */
public class User {
    @JsonProperty
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    /**
     * Human readable name of the restaurant.
     */
    @JsonProperty
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
