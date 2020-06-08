package cn.sitedev.spring5.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class TimeHandler {
    public Mono<ServerResponse> getTime(ServerRequest request) {
        String time = "Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println(time);
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just(time), String.class);
    }

    public Mono<ServerResponse> getDate(ServerRequest request) {
        String date = "Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(date);
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just(date), String.class);
    }

    public Mono<ServerResponse> sendTimePerSec(ServerRequest request) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1L)).map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }
}
