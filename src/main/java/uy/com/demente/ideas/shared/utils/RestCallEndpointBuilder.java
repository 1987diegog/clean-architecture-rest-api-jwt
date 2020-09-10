package uy.com.demente.ideas.shared.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class RestCallEndpointBuilder {

    private Logger log = LoggerFactory.getLogger(RestCallEndpointBuilder.class);

    public static String builderWhitPathParamsAndQueryParams(String url, Map<String,
            String> pathParams, MultiValueMap<String, String> queryParams) {
        assert queryParams != null;
        assert pathParams != null;

        UriComponents builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParams(queryParams)
                .buildAndExpand(pathParams);

        return builder.toString();
    }

    public static String builderWhitOnlyPathParams(String url, Map<String,
            String> pathParams) {
        assert pathParams != null;
        UriComponents builder = UriComponentsBuilder
                .fromHttpUrl(url)
                .buildAndExpand(pathParams);
        return builder.toString();
    }

    public static String builderWhitOnlyQueryParams(String url, MultiValueMap<String,
            String> queryParams) {
        assert queryParams != null;
        UriComponents builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParams(queryParams)
                .build();
        return builder.toString();
    }

    public static HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
