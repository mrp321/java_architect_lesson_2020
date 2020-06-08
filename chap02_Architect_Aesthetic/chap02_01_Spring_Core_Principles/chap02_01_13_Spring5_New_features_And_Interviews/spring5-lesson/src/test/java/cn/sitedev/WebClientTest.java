package cn.sitedev;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class WebClientTest {
    @Test
    public void webClientTest() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> response = webClient.get().uri("/times").retrieve().bodyToMono(String.class);
        response.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }
}
