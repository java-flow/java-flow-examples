package com.javaflow.example.http;

import com.javaflow.core.network.HttpRequest;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import com.javaflow.core.support.Msg;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestFlow implements FlowDefiner {

    @Override
    public Flow define() {
        return new Flow("Request")
                .next(msg -> {
                    msg.put(HttpRequest.Fields.method, "POST");
                    msg.put(HttpRequest.Fields.url, "http://localhost:8080/echo");
                    return msg;
                })
                .next(new HttpRequest())
                .next(msg -> msg);
    }

    public static void main(String[] args) {
        Flow flow = new RequestFlow().deploy();
        flow.invoke(new Msg().payload("hello"));
    }

}
