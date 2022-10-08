package com.javaflow.example.tcp;

import com.javaflow.core.network.TcpRequest;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import com.javaflow.core.support.Msg;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RequestFlow implements FlowDefiner {

    private final String host;

    private final Integer port;

    @Override
    public Flow define() {
        return new Flow("Request")
                .next(new TcpRequest(host, port))
                .next(msg -> msg);
    }

    public static void main(String[] args) throws Exception {
        Flow flow = new RequestFlow("localhost", 8080).deploy();
        flow.invoke(new Msg().payload("hello"));
    }

}
