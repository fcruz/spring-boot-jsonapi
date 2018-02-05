package com.example.fcruz;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocationRouter extends RouteBuilder {

    @Value("${endpoint}")
    private String endPoint;

    @Value("${key}")
    private String apiKey;

    public LocationRouter() {
    }

    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setTrimSpaces(true);
        xmlJsonFormat.setRootName("GeocodeResponse");

        from("direct:start")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.HTTP_QUERY, simple("key="+apiKey))
                .setHeader(Exchange.HTTP_QUERY, simple("address=${in.headers.address}"))
                .to(endPoint).to("xslt:transform.xsl")
                .marshal(xmlJsonFormat);
    }
}

