package cn.sitedev.spring5.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@ComponentScan("cn.sitedev.spring5.demo")
public class RouterConfig {
    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter1() {
        return route(GET("/time"), req -> timeHandler.getTime(req)).
                // 这种方式相对于上一行更加简洁
                        andRoute(GET("/date"), timeHandler::getDate);

    }

    @Bean
    public RouterFunction<ServerResponse> timerRouter2() {
        return route(GET("/time"), timeHandler::getTime).andRoute(GET("/date"), timeHandler::getDate).andRoute(GET(
                "/times"), timeHandler::sendTimePerSec);
    }
}
