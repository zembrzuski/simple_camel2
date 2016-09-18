package com.zembrzuski;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.apache.camel.http.common.HttpOperationFailedException;

public class MyRoute {

    private static final String RMQ = "rabbitmq://localhost:5672/myExchange?routingKey=myQueue&durable=false&autoDelete=false";
    private static final String FOLDER_EXIST = "file:/home/nozes/temp/exist";
    private static final String FOLDER_NON_EXIST = "file:/home/nozes/temp/non-exist";

    private final Processor createRestPayloadProcessor = new RestPayloadProcessor();

    public RouteBuilder createRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                GsonDataFormat dataFormat = new GsonDataFormat();

                onException(HttpOperationFailedException.class)
                        .handled(true)
                        .setHeader("Content-Type", constant("application/json"))
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.PUT))
                        .to("http4://localhost:8080/customer/57ddb9d4bc84876b221656d2")
                        .to(FOLDER_NON_EXIST);

                from(RMQ)
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.GET))
                        .toD("http4://localhost:8080/customer/${body}")
                        .process(createRestPayloadProcessor)
                        .marshal(dataFormat)
                        .setHeader("Content-Type", constant("application/json"))
                        .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.PUT))
                        .to("http4://localhost:8080/customer/57ddb9d4bc84876b221656d2")
                        .to(FOLDER_EXIST);
            }
        };
    }


}
