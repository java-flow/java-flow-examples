package com.javaflow.example.http;

import com.javaflow.core.network.HttpIn;
import com.javaflow.core.network.HttpOut;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import lombok.AllArgsConstructor;
import spark.route.HttpMethod;

import java.time.ZonedDateTime;

@AllArgsConstructor
public class PrintTimeFlow implements FlowDefiner {

    private final Integer listenPort;

    private final HttpMethod method;

    private final String url;

    @Override
    public Flow define() {
        return new Flow("PrintTime")
                .next(new HttpIn(listenPort, method, url))
                .next(msg -> msg.payload(ZonedDateTime.now()))
                .next(new HttpOut());
    }

    public static void main(String[] args) {
        new PrintTimeFlow(8080, HttpMethod.get, "/time").deploy();
    }

}
