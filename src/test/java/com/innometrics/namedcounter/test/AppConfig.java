package com.innometrics.namedcounter.test;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.innometrics.namedcounter.rest.jersey.Welcome;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rs")
public class AppConfig extends Application {

    private final Set<Class<?>> classes;

    public AppConfig() {
        HashSet<Class<?>> c = new HashSet();
        c.add(Welcome.class);
        classes = Collections.unmodifiableSet(c);
    }


    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}