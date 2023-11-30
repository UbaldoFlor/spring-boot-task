package com.gymsystem.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@Service
public class MetricsService {
	private static final Counter counter = Metrics.counter("my_service_requests_total");

    public static String handleRequest() {
        counter.increment();
        return "Handle request method.";
    }
}
