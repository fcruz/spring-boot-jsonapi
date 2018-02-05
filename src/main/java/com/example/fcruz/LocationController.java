package com.example.fcruz;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LocationController {

    private static final Logger log = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    @EndpointInject(uri="direct:start")
    ProducerTemplate producerTemplate;

    @Value("${invalid.message}")
    String invalidMessage;

    @Value("${notfound.message}")
    String notFoundMessage;

    @RequestMapping(value = "/location",method = { RequestMethod.GET, RequestMethod.POST })
    public String getLocation(@RequestParam(value="address", required = true) String address) {
        final Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("address", address);

        Exchange exchange = producerTemplate.send("direct:start", (Processor) exchange1 -> exchange1.getIn().setHeaders(headers));

        String out = exchange.getOut().getBody(String.class);
        log.info(out);
        return out;
    }

    @RequestMapping(value = "*")
    public String defaultMapping(){
        return notFoundMessage;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        String invalidRequest = String.format(invalidMessage, ex.getParameterName());
        return invalidRequest;
    }
}
