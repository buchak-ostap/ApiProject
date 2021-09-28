package com.apiProject.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

public class SessionProvider implements ApplicationConstants{

    public static String getSessionToken(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(getParams(email, password), headers);
        ResponseEntity<String> response = restTemplate.exchange(SS_BASE_URL + LOGIN_ENDPOINT, POST, httpEntity, String.class);

        List<String> map = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        try {
            return map.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NullPointerException("JSESSIONID not found in cookie");
        }
    }

    public static LinkedMultiValueMap<String, String> getParams(String email, String password) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(J_USERNAME, email);
        params.add(J_PASSWORD, password);
        return params;
    }
}
