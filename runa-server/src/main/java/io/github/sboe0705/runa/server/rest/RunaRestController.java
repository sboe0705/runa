package io.github.sboe0705.runa.server.rest;

import io.github.sboe0705.runa.server.service.MessageStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;

@RestController
@RequestMapping("/api/messages")
public class RunaRestController {

    private static final Logger logger = LoggerFactory.getLogger(RunaRestController.class);

    @Autowired
    private MessageStorage messageStorage;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message) {
        logger.atInfo().log("Received message for {}.", message.recipient());
        messageStorage.addMessage(message);
        return "Server received message at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(systemDefault())
                .format(now()) + ".";
    }

    @GetMapping("/receive")
    public List<Message> receiveMessage(@RequestParam("recipient") String recipient) {
        List<Message> messages = messageStorage.retrieveMessagesFor(recipient);
        logger.atInfo().log("{} messages have been delivered to {}.", messages.size(), recipient);
        return messages;
    }

}
