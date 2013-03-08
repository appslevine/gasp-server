package com.cloudbees.gasp.models;

import org.codehaus.jackson.annotate.JsonIgnore;
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
