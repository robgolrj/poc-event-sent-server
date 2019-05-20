package com.example.serversentevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by robson on 19/05/2019.
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "mensagem", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sendMessage(){
        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> testService.mensagem());
    }

    @GetMapping(value = "mensagem/{msg}")
    public void sendMessage(@PathVariable("msg") String msg){
        testService.mensagem(msg);
    }
}
