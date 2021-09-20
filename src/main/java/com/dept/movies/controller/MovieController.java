package com.dept.movies.controller;

import com.dept.movies.constants.MovieConstants;
import com.dept.movies.entity.Result;
import com.dept.movies.entity.MovieRecords;
import com.dept.movies.service.MovieService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
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
    public ResponseEntity<List<Result>> searchMoviesByTitle(@PathVariable("title") String title) {

        List<Result> resultList = searchmoviesByTitle(title); //TOO get it as parameter
        String linkOfTheMovie;

        if (resultList == null || resultList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //TODO not sure how to manage there maybe we return with found

        //TODO 3 aggregate metodunu daha mantıklı bi yerde güzl bir şekilde yaz çalıştır !!!
        for (Result result : resultList) {
            //TODO burada yahoo API sin iarıycan ve neye gore arama yapıcagını bilmen lazım
            linkOfTheMovie = getMovieLinkByTitle(title);
            if (linkOfTheMovie != null) { //TODO null check find method or optional
                //movie.setMovieLink(linkOfTheMovie);  //TODO uncomment me late r on
            }
        }


        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    public String getMovieLinkByTitle(String title) {

        //TODO add method here

        //TODO 2 buradan link gelmesini yap ya da trailer gelmesini yap!!!!!

        return "www.youtube.com";//TODO
    }


    public List<Result> searchmoviesByTitle(String titleName) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add(MovieConstants.CONTENT_TYPE, MovieConstants.APPLICATION_JSON);
        headers.add(MovieConstants.AUTHORIZATION, MovieConstants.API_KEY);

        // build the request
        HttpEntity httpEntity = new HttpEntity(headers);

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        ParameterizedTypeReference<MovieRecords> parametrizedMovieList =
                new ParameterizedTypeReference<MovieRecords>() {
                };

        ResponseEntity<MovieRecords> response = restTemplate.exchange(MovieConstants.URI_IMDB_TITLE, HttpMethod.GET, httpEntity, parametrizedMovieList, titleName);

        MovieRecords movieRecordsList = (MovieRecords) response.getBody();
        List<Result> resultList = movieRecordsList.getResultList();
        for (Result result : resultList) {
            //TODO call other service from there !!
            System.out.println(result.getTitle());
        }

        List<Result> listOfString = null;
        return listOfString;
    }

}
