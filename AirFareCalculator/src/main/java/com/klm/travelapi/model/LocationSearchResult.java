package com.klm.travelapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import java.util.List;

/**
 * Created by Bhuvaneswari Ashok.
 */
@JsonSerializableSchema
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchResult {

    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "LocationSearchResult{" +
                "locations=" + locations +
                '}';
    }
}
