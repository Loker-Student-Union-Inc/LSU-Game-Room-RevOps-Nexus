package edu.csudh.lsu.revops.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ActivityResponse {

    private String activity;
    private String category;
    private Integer price;
    private String imageLocation;
}
