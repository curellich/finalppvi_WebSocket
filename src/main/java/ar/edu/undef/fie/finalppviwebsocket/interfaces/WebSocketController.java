package ar.edu.undef.fie.finalppviwebsocket.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @GetMapping("/finalppvi")
    public String getFinalPPVI() {
        return "Final PPVI";
    }
}

