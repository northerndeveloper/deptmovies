package com.dept.movies.controller;

import com.dept.movies.constants.MovieConstants;
import com.dept.movies.entity.Movie;
import com.dept.movies.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    //TODO ikinci metodu ekle

    // TODO aggregate fieldini ekle

    public final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieService movieService;

    private RestTemplate restTemplate;

    public MovieController(MovieService movieService, RestTemplate restTemplate) {
        this.movieService = movieService;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/searchmoviesByTitle/{title}")
    public ResponseEntity<List<Movie>> searchMoviesByTitle(@PathVariable("title") String title) {

        List<Movie> movieList = searchmoviesByTitle(title); //TOO get it as parameter
        String linkOfTheMovie;

        if (movieList == null || movieList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //TODO not sure how to manage there maybe we return with found

        //TODO 3 aggregate metodunu daha mantıklı bi yerde güzl bir şekilde yaz çalıştır !!!
        for (Movie movie : movieList) {
            //TODO burada yahoo API sin iarıycan ve neye gore arama yapıcagını bilmen lazım
            linkOfTheMovie = getMovieLinkByTitle(title);
            if (linkOfTheMovie != null) { //TODO null check find method or optional
                movie.setMovieLink(linkOfTheMovie);
            }
        }


        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    public String getMovieLinkByTitle(String title) {

        //TODO add method here

        //TODO 2 buradan link gelmesini yap ya da trailer gelmesini yap!!!!!

        return "www.youtube.com";//TODO
    }


    public List<Movie> searchmoviesByTitle(String titleName) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set(MovieConstants.CONTENT_TYPE, MovieConstants.APPLICATION_JSON);
        headers.set(MovieConstants.AUTHORIZATION, MovieConstants.API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("query", titleName);

        //TODO there is no way to return it with list
        //   ResponseEntity<Movie[]> responseEntity = restTemplate
        //         .exchange(MovieConstants.URI_IMDB_TITLE, HttpMethod.GET, entity, Movie[].class, params);

        //TODO return as a list

        //TODO first buradan liste gelmesini yap !!!!
        ResponseEntity<Movie> responseEntity = restTemplate
                .getForEntity(MovieConstants.URI_IMDB_TITLE, Movie.class, entity, params);

        return Arrays.asList(responseEntity.getBody());


    }


}
