package io.github.sboe0705.runa.server.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;

@RestController
@RequestMapping("/api/messages")
public class RunaRestController {

    private static final Logger logger = LoggerFactory.getLogger(RunaRestController.class);

    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message) {
        logger.atDebug().log("Received message from {} to {}.", message.sender(), message.recipient());
        return "Server received message at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(systemDefault())
                .format(now()) + ".";
    }

}
