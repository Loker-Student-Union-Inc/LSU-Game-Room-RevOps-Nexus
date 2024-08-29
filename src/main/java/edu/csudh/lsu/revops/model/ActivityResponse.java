package edu.csudh.lsu.revops.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonDeserialize
@JsonSerialize
public class ActivityResponse {

    private String activity;
    private String category;
    Map<String, Map<String, String>> price; // Nested map to store prices for different user types and time slots
    private String imageLocation;
}
