package com.a.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-20 17:19
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator cafdsf(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes=routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",r -> r.path("/domestic").uri("http://www.baidu.com")).build();
        RouteLocator routeLocator=routes.build();
        return routeLocator;
    }
}
