package ua.skillsup.practice.restworkshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.restworkshop.model.Product;
import ua.skillsup.practice.restworkshop.model.Client;
import ua.skillsup.practice.restworkshop.services.ClientService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/clients")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts(
            @RequestParam(required = false, name = "from")
            @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate from,
            @RequestParam(required = false, name = "to")
            @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate to) {
        logger.info("Incoming parameters are: from '{}', to '{}'", from, to);
        return service.getAll(from, to);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addClient(@RequestBody @Valid Client newClient) {
        logger.info("New client: {}", newClient);
        service.store(newClient);
    }

    @PostMapping(path = "/{clientId}/product",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody Product product,
                           @PathVariable(name = "clientId") long clientId) {
        logger.info("New product: {} {}", clientId, product);
        service.addProducts(clientId, product);
    }
}