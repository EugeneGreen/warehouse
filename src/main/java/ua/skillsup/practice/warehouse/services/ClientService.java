package ua.skillsup.practice.warehouse.services;

import org.springframework.stereotype.Service;
import ua.skillsup.practice.warehouse.exceptions.InvalidFormatClientException;
import ua.skillsup.practice.warehouse.exceptions.NoSuchClientException;
import ua.skillsup.practice.warehouse.exceptions.NotEmptyProductListException;
import ua.skillsup.practice.warehouse.model.Client;
import ua.skillsup.practice.warehouse.repositories.ProductRepository;
import ua.skillsup.practice.warehouse.repositories.ClientRepository;
import ua.skillsup.practice.warehouse.repositories.entities.ClientEntity;

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
        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setCompany(entity.getCompany());
        client.setContacts(entity.getContacts());
        client.setProducts(entity.getProducts().stream()
                .map(ProductService::convertProductFromEntity)
                .collect(Collectors.toList()));
        return client;
    }

    @Transactional
    public void addClient(Client client) {
        ClientEntity entity = new ClientEntity();
        if(!client.isCorrect()) {
            throw new InvalidFormatClientException("All required fields must be filled");
        }
        entity.setFirstName(client.getFirstName());
        entity.setLastName(client.getLastName());
        entity.setCompany(client.getCompany());
        entity.setContacts(client.getContacts());
        clientRepository.save(entity);
    }

    @Transactional
    public void updateClient(Client client) {
        Optional<ClientEntity> entityOptional = clientRepository.findById(client.getId());
        if (!entityOptional.isPresent()) {
            throw new NoSuchClientException("No client with ID " + client.getId());
        }
        ClientEntity entity = entityOptional.get();
        if (client.getFirstName() != null) {
            entity.setFirstName(client.getFirstName());
        }
        if (client.getLastName() != null) {
            entity.setLastName(client.getLastName());
        }
        if (client.getCompany() != null) {
            entity.setCompany(client.getCompany());
        }
        if (client.getContacts() != null) {
            entity.setContacts(client.getContacts());
        }
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
