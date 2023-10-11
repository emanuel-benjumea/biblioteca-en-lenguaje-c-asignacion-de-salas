package com.uceva.microservicioreservas.ReservaRestController;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservaRestController {
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaRestController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
}

