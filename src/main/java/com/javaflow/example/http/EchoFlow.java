package com.javaflow.example.http;

import com.javaflow.core.network.HttpIn;
import com.javaflow.core.network.HttpOut;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import lombok.AllArgsConstructor;
import spark.route.HttpMethod;

@AllArgsConstructor
public class EchoFlow implements FlowDefiner {

    private final Integer listenPort;

    private final HttpMethod method;

    private final String url;

    @Override
    public Flow define() {
        return new Flow("Echo")
                .next(new HttpIn(listenPort, method, url))
                .next(new HttpOut());
    }

    public static void main(String[] args) {
        new EchoFlow(8080, HttpMethod.post, "/echo").deploy();
    }

}
