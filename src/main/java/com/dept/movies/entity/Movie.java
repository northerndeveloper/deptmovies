package com.dept.movies.entity;

import lombok.Data;

@Data
public class Movie {

    private String title;

    private String year;

    private String imdbID;

    private String type;

    private String poster;

    private String movieLink;
}
