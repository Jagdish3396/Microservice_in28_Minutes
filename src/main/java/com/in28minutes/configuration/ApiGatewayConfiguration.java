package com.in28minutes.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/get")
				.filters(f -> f.addRequestHeader("MyHeaders", "MyHeaders").addRequestParameter("param", "myValue"))
				.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.build();
		
	}

}
