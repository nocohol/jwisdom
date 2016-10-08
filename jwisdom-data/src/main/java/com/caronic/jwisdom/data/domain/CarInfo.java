package com.caronic.jwisdom.data.domain;

import javax.persistence.*;

/**
 * Created by caronic on 2016/3/28.
 */
@Entity
@Table(name = "car_info")
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //it will generate " call next value for hibernate_sequence" statement to get the next value,
    //so need to create a sequence hibernate_sequence in H2. Before it doesn't need this.
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "rate")
    private Float rate;

    protected CarInfo() {

    }

    public CarInfo(String imageUrl, Float rate) {
        this.imageUrl = imageUrl;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "id=" + id +
                ", imageUrl=" + imageUrl +
                ", rate=" + rate +
                '}';
    }
}
