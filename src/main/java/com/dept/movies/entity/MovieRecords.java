package com.dept.movies.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MovieRecords implements Serializable {

    @JsonProperty( "result" )
    private List<Result> resultList;

}
