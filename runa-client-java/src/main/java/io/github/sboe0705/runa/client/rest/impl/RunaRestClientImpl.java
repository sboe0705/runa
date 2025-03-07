package io.github.sboe0705.runa.client.rest.impl;

import io.github.sboe0705.runa.client.rest.RunaRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RunaRestClientImpl implements RunaRestClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public String sendMessage(String sender, String recipient, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Message message = new Message(sender, recipient, text);
        HttpEntity<Message> requestEntity = new HttpEntity<>(message, headers);
        ResponseEntity<String> responseEntity = restTemplateBuilder.build().exchange("http://localhost:8080/api/messages/send", HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }

}
