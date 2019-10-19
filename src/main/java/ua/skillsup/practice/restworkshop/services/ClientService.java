package ua.skillsup.practice.restworkshop.services;

import org.springframework.stereotype.Service;
import ua.skillsup.practice.restworkshop.exceptions.NoSuchClientException;
import ua.skillsup.practice.restworkshop.model.Client;
import ua.skillsup.practice.restworkshop.model.Product;
import ua.skillsup.practice.restworkshop.repositories.ProductRepository;
import ua.skillsup.practice.restworkshop.repositories.ClientRepository;
import ua.skillsup.practice.restworkshop.repositories.entities.ClientEntity;
import ua.skillsup.practice.restworkshop.repositories.entities.ProductEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ClientService(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAll(LocalDate from, LocalDate to) {
        List<ProductEntity> entities;
        if (from == null && to == null) {
            entities = productRepository.findAll();
        } else if (from == null) {
            entities = productRepository.findByDateUpdateBefore(convert(to));
        } else if (to == null) {
            entities = productRepository.findByDateUpdateAfter(convert(from));
        } else {
            entities = productRepository.findByDateUpdateBetween(convert(from), convert(to));
        }
        return entities.stream()
                .map(ClientService::convertProductFromEntity)
                .collect(Collectors.toList());
    }

    private static LocalDateTime convert(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
    }

    private static Client convertClientFromEntity(ClientEntity entity) {
        Client client = new Client();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setDescription(entity.getDescription());
        client.setProducts(entity.getProducts().stream()
                .map(ClientService::convertProductFromEntity)
                .collect(Collectors.toList()));
        return client;
    }

    private static Product convertProductFromEntity(ProductEntity entity) {
        Product product = new Product();
        product.setTitle(entity.getTitle());
        product.setDescription(entity.getDescription());
        return product;
    }

    @Transactional
    public void store(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setName(client.getName());
        entity.setDescription(client.getDescription());
        clientRepository.save(entity);
    }

    public void addProducts(long clientId, Product product) {
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (!client.isPresent()) {
            throw new NoSuchClientException("No client with ID " + clientId);
        }
        ProductEntity entity = new ProductEntity();
        entity.setTitle(product.getTitle());
        entity.setClient(client.get());
        productRepository.save(entity);
    }
}
