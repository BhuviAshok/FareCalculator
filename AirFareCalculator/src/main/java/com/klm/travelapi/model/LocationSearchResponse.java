package com.klm.travelapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

/**
 * Created by Bhuvaneswari Ashok.
 */
@JsonSerializableSchema
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationSearchResponse {

    private LocationSearchResult _embedded;

    public LocationSearchResult get_embedded() {
        return _embedded;
    }

    public void set_embedded(LocationSearchResult _embedded) {
        this._embedded = _embedded;
    }

    @Override
    public String toString() {
        return "LocationSearchResponse{" +
                "_embedded=" + _embedded +
                '}';
    }
}
