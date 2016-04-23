package com.caronic.jwisdom.data.domain;

import javax.persistence.*;
import java.net.URL;

/**
 * Created by caronic on 2016/3/28.
 */
@Entity
@Table(name = "CAR_INFO")
public class CarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private URL imageUrl;

    @Column(name = "rate")
    private Float rate;

    protected CarInfo() {

    }

    public CarInfo(URL imageUrl, Float rate) {
        this.imageUrl = imageUrl;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
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
