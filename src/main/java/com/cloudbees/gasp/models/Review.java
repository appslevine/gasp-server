package com.cloudbees.gasp.models;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Kohsuke Kawaguchi
 */
@Entity
public class Review {
    @ManyToOne(optional=false)
    @JoinColumn
    private User user;

    @ManyToOne(optional=false)
    @JoinColumn
    private Restaurant restaurant;

    @Column
    @JsonProperty
    private int star;

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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
