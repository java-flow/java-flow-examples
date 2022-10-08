package com.javaflow.example.tcp;

import com.javaflow.core.network.TcpIn;
import com.javaflow.core.network.TcpOut;
import com.javaflow.core.support.Flow;
import com.javaflow.core.support.FlowDefiner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.javaflow.core.network.TcpIn.ContentType.STRING;

@Slf4j
@AllArgsConstructor
public class EchoFlow implements FlowDefiner {

    private final Integer listenPort;

    @Override
    public Flow define() {
        return new Flow("Echo")
                .next(new TcpIn(listenPort, STRING))
                .next(new TcpOut());
    }

    public static void main(String[] args) throws Exception {
        new EchoFlow(8080).deploy();
    }

}
