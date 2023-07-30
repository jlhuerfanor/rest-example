package com.endava.workshops.restexample.application.adapter.primary.rest;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public final class ResponseUtils {
    public static URI getLocationUri(Object id) {
        return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();
    }

    public static URI replaceLocationUri(Object id) {
        return ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/../{id}")
                    .buildAndExpand(id)
                    .normalize()
                    .toUri();
    }

    private ResponseUtils() { }
}
