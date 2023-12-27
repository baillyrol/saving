package app.saving.controller;

import app.saving.facade.ConnectionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionFacade connectionFacade;
    @Value("${mock.userId}")
    private String user;

    @GetMapping
    public void add(@RequestParam String code, @RequestParam("connection_id") String externalConnectionId){
        connectionFacade.add(code, externalConnectionId, user);

    }
}
