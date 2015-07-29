package com.innometrics.namedcounter.test;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class NamedCounterTest {
 
    @Test
    public void shouldCheckURIs() throws IOException {
 
        URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();
 
        // Create an HTTP server listening at port 8282
        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        // Create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new AppConfig(), HttpHandler.class);
        // Map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);
        // Start the server
        server.start();
 
        Client client = ClientBuilder.newClient();
 
        // Valid URIs
        assertEquals(200, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/").request().get().getStatus());
        //it seems impossible to test a put request without entity
       //assertEquals(201, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/insert/namedcounter").request().put().getStatus());
        assertEquals(200, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/getValue/namedcounter").request().get().getStatus());
        assertEquals(200, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/incrementValue/namedcounter/").request().get().getStatus());
 
        // Invalid URIs
        assertEquals(404, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/getValue/").request().get().getStatus());
        assertEquals(404, client.target("http://localhost:8080/NamedCounterAPI/NamedCounterAPI/incrementValue/").request().get().getStatus());
        // Stop HTTP server
        server.stop(0);
    }
}