package com.book.API.command.application.controller;

import com.book.Post.command.application.dto.PostDTO;
import com.book.Post.command.application.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class APIController {
    String words = "gigig";
    private final String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbhorry71729001&output=js&Query=aladin";

    private final RestTemplate restTemplate;
    private final PostService postservice;


    @Autowired
    public APIController(RestTemplate restTemplate, PostService postservice) {
        this.restTemplate = restTemplate;
        this.postservice = postservice;
    }

    @GetMapping("/listBook")
    public Map<String, Object> callApi(@RequestParam(value = "pbNum", required = false) String query) {

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<?> entity = new HttpEntity<>(headers);

        URI builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("Query", query)
                .queryParam("output", "js")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
        };

        ResponseEntity<Map<String, Object>> result = restTemplate.exchange(builder, HttpMethod.GET, entity, responseType);
        System.out.println(result);
        return result.getBody();
    }

    @GetMapping("/listBook2") //왜 되는건지 알아보기 알라딘 api
    public String fetch() throws UnsupportedEncodingException, JsonProcessingException, ParseException {

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(BASE_URL, HttpMethod.GET, entity, Map.class);
       // System.out.println(resultMap.getBody());
       // System.out.println(resultMap.getStatusCode());
       // System.out.println(resultMap);
        ArrayList<Map<String, Object>> colors = (ArrayList<Map<String, Object>>) resultMap.getBody().get("item");
      //  System.out.println("colors = " + colors);
      //  System.out.println("colors.get = " + colors.get(1).get("title").toString());
      //  System.out.println("colors = " + colors.size());
        ObjectMapper mapper = new ObjectMapper();
      //  System.out.println("hii = " + resultMap.getBody().get("item"));

        String jsonInString;
        jsonInString = mapper.writeValueAsString(resultMap.getBody().get("item"));
        System.out.println("jsonInString = " + jsonInString);
        int i;
        // real
        for (i=0;i<colors.size();i++){
            System.out.println("colors.get = " + colors.get(i).get("title").toString());
            System.out.println("colors.get = " + colors.get(i).get("link").toString());
            System.out.println("colors.get = " + colors.get(i).get("description").toString());
            System.out.println("colors.get = " + colors.get(i).get("cover").toString());

            PostDTO postdto = new PostDTO();
            postdto.setTitle(colors.get(i).get("title").toString());
            postdto.setContent(colors.get(i).get("description").toString());
            postdto.setMemberid("111111");
            postdto.setAuthor(colors.get(i).get("author").toString());
            postdto.setPublisher(colors.get(i).get("cover").toString());
            postdto.setIsdelete("0");
            postservice.savePost(postdto);
        }

        return jsonInString;

    }



    @GetMapping("/listBook3")
    public String hello() {
      //  JSONParser jsonParser = new JSONParser();
      //  JSONObject jsonObject = (JSONObject) jsonParser.parse(resultMap.getBody().get("item").toString());
      //  JSONArray track = (JSONArray) jsonObject.get("item");
        // uri 주소 생성
        URI uri = UriComponentsBuilder
                .fromUriString(BASE_URL) //http://localhost에 호출
                .queryParam("Query", "steve")
                .queryParam("Content-Type","application/json; charset=UTF-8.")// query parameter가 필요한 경우 이와 같이 사용
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplete = new RestTemplate();
        restTemplete.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        ResponseEntity<Map> result = restTemplete.getForEntity(uri, Map.class);
        // entity로 데이터를 가져오겠다(Get)~~
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return null;
    }

}