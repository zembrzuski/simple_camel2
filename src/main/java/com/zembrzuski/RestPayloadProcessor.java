package com.zembrzuski;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RestPayloadProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.setOut(exchange.getIn());

        RestPayload newBody = new RestPayload("mutuco", "sobrenome-1");

        exchange.getOut().setBody(newBody);
    }

}
