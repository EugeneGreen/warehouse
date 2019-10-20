package ua.skillsup.practice.warehouse.services;

import org.springframework.stereotype.Service;
import ua.skillsup.practice.warehouse.exceptions.NoSuchClientException;
import ua.skillsup.practice.warehouse.exceptions.NotEmptyProductListException;
import ua.skillsup.practice.warehouse.model.Client;
import ua.skillsup.practice.warehouse.model.Product;
import ua.skillsup.practice.warehouse.repositories.ProductRepository;
import ua.skillsup.practice.warehouse.repositories.ClientRepository;
import ua.skillsup.practice.warehouse.repositories.entities.ClientEntity;
import ua.skillsup.practice.warehouse.repositories.entities.ProductEntity;

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
    private final ProductService productService;

    public ClientService(ClientRepository clientRepository, ProductRepository productRepository, ProductService productService) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public List<Client> getAll() {
        List<ClientEntity> entities = clientRepository.findAll();
        return entities.stream()
                .map(ClientService::convertClientFromEntity)
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
                .map(ProductService::convertProductFromEntity)
                .collect(Collectors.toList()));
        return client;
    }

    @Transactional
    public void addClient(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setName(client.getName());
        entity.setDescription(client.getDescription());
        clientRepository.save(entity);
    }

    @Transactional
    public void deleteClient(long clientId) {
        Optional<ClientEntity> entity = clientRepository.findById(clientId);
        if (!entity.isPresent()) {
            throw new NoSuchClientException("No client with ID " + clientId);
        }
        if (entity.get().getProducts().size() > 0) {
            throw new NotEmptyProductListException("Client " + clientId + " has products");
        }
        clientRepository.delete(entity.get());
    }
}
