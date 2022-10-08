package com.javaflow.example.tcp;

import com.javaflow.core.network.TcpIn;
import com.javaflow.core.network.TcpOut;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

import static com.javaflow.core.network.TcpIn.ContentType.STRING;

@AllArgsConstructor
public class PrintTimeFlow implements FlowDefiner {

    private final Integer listenPort;

    @Override
    public Flow define() {
        return new Flow("PrintTime")
                .next(new TcpIn(listenPort, STRING))
                .next(msg -> msg.payload(ZonedDateTime.now()))
                .next(new TcpOut());
    }

    public static void main(String[] args) {
        new PrintTimeFlow(8080).deploy();
    }

}
