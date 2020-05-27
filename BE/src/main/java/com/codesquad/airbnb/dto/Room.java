package com.codesquad.airbnb.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@ToString
public class Room {

    @Id
    private final Long id;

    private final Boolean superHost;

    private final String location;

    private final String title;

    private final Integer price;

    private final Float reviewScore;

    private final String thumbnail;

    private Integer totalPrice;

    private final Integer discountedPrice;

    @Builder
    public Room(Long id, Boolean superHost, String location, String title, Integer price,
                Float reviewScore, String thumbnail, Integer totalPrice, Integer discountedPrice) {
        this.id = id;
        this.superHost = superHost;
        this.location = location;
        this.title = title;
        this.price = price;
        this.reviewScore = reviewScore;
        this.thumbnail = thumbnail;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
