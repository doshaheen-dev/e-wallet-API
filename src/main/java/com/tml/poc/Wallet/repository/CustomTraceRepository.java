package com.tml.poc.Wallet.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;
import org.springframework.boot.actuate.trace.*;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

    public static AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();
    
    List<HttpTrace> listOFHttp=new ArrayList<HttpTrace>();
    
    Logger logger = LoggerFactory.getLogger(CustomTraceRepository.class);

    @Override
    public List<HttpTrace> findAll() {
    	
        return listOFHttp;
    }

    @Override
    public void add(HttpTrace trace) {
//        if ("GET".equals(trace.getRequest().getMethod())) {
            lastTrace.set(trace);
            
            logger.trace("A TRACE Message"+ trace.getRequest());
            logger.debug("A DEBUG Message"+ trace.getResponse());
            logger.info("An INFO Message");
            logger.warn("A WARN Message"+ trace.getRequest().toString());
            logger.error("An ERROR Message"+ trace.getResponse().toString());
            listOFHttp.add(trace);
//        }
       /**
        * here put HTTP Request Resp to DB
        */
    }

}
//
//@Repository
//public class CustomTraceRepository extends InMemoryTraceRepository {
//
//    private static final Logger log = LoggerFactory.getLogger(CustomTraceRepository.class);
//
//    public CustomTraceRepository() {
//        super.setCapacity(200);
//    }
//
//    @Override
//    public void add(Map<String, Object> map) {
//        super.add(map);
//
//        log.info("traced object: {}", map);
//    }
//}