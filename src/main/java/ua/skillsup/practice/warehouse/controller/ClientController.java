package ua.skillsup.practice.warehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.practice.warehouse.model.ProductCount;
import ua.skillsup.practice.warehouse.model.Product;
import ua.skillsup.practice.warehouse.model.Client;
import ua.skillsup.practice.warehouse.model.ProductCountList;
import ua.skillsup.practice.warehouse.services.ClientService;
import ua.skillsup.practice.warehouse.services.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/clients")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;
    private final ProductService productService;

    public ClientController(ClientService clientService, ProductService productService) {
        this.clientService = clientService;
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClients(){
        return clientService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addClient(@RequestBody @Valid Client newClient) {
        logger.info("New client: {}", newClient);
        clientService.addClient(newClient);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody Client existingClient) {
        logger.info("Update client: {}", existingClient);
        clientService.updateClient(existingClient);
    }

    @PostMapping(path = "/{clientId}/product",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody Product product,
                              @PathVariable(name = "clientId") long clientId) {
        logger.info("New product: {} {}", clientId, product);
        productService.addProduct(clientId, product);
    }

    @DeleteMapping(path = "/{clientId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@PathVariable(name = "clientId") long clientId) {
        logger.info("Delete client: {}", clientId);
        clientService.deleteClient(clientId);
    }

    @PostMapping(path = "/{clientId}/{productId}/add",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCountToProduct(@RequestBody ProductCount productCount,
                                  @PathVariable(name = "clientId") long clientId,
                                  @PathVariable(name = "productId") long productId) {
        logger.info("Add productCount to product: {} {} {}", clientId, productId, productCount);
        productService.increaseCount(productId, productCount.getCount());
    }

    @PostMapping(path = "/{clientId}/{productId}/withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdrawProduct(@RequestBody ProductCount productCount,
                                @PathVariable(name = "clientId") long clientId,
                                @PathVariable(name = "productId") long productId) {
        logger.info("decreease productCount from product: {} {} {}", clientId, productId, productCount);
        productService.decreaseCount(productId, productCount.getCount());
    }

    @PostMapping(path = "/{clientId}/withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdrawProducts(@RequestBody ProductCountList products,
                                @PathVariable(name = "clientId") long clientId) {
        logger.info("group withdraw products: {}", clientId);
        for (int i = 0; i < products.getProducts().size(); i++) {
            productService.decreaseCount(products.getProducts().get(i).getId(), products.getProducts().get(i).getCount());
        }
    }
}