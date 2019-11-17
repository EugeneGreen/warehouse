package ua.skillsup.practice.warehouse.services;

import org.springframework.stereotype.Service;
import ua.skillsup.practice.warehouse.exceptions.NoSuchClientException;
import ua.skillsup.practice.warehouse.exceptions.NoSuchProductException;
import ua.skillsup.practice.warehouse.exceptions.NotEnoughProductException;
import ua.skillsup.practice.warehouse.model.Product;
import ua.skillsup.practice.warehouse.repositories.ClientRepository;
import ua.skillsup.practice.warehouse.repositories.ProductRepository;
import ua.skillsup.practice.warehouse.repositories.entities.ClientEntity;
import ua.skillsup.practice.warehouse.repositories.entities.ProductEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ProductService(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public static Product convertProductFromEntity(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setTitle(entity.getTitle());
        product.setDescription(entity.getDescription());
        product.setCategories(entity.getCategories());
        product.setDateCreate(entity.getDateCreate());
        product.setDateUpdate(entity.getDateUpdate());
        product.setCount(entity.getCount());
        return product;
    }

    public void increaseCount(long productId, int count) {
        Optional<ProductEntity> existProductEntity = productRepository.findById(productId);
        if (!existProductEntity.isPresent()) {
            throw new NoSuchProductException("No product with ID " + productId);
        }
        ProductEntity entity = existProductEntity.get();
        entity.setCount(entity.getCount() + count);
        entity.setDateUpdate(LocalDateTime.now());
        productRepository.save(entity);
    }

    public void decreaseCount(long productId, int count) {
        Optional<ProductEntity> existProductEntity = productRepository.findById(productId);
        if (!existProductEntity.isPresent()) {
            throw new NoSuchProductException("No product with ID " + productId);
        }
        ProductEntity entity = existProductEntity.get();
        if (count > entity.getCount()) {
            throw new NotEnoughProductException("Invalid amount of product " + productId);
        } else if (count == entity.getCount()) {
            productRepository.delete(entity);
        } else {
            entity.setCount(entity.getCount() - count);
            entity.setDateUpdate(LocalDateTime.now());
            productRepository.save(entity);
        }
    }

    public void addProduct(long clientId, Product product) {
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (!client.isPresent()) {
            throw new NoSuchClientException("No client with ID " + clientId);
        }
        ProductEntity entity = new ProductEntity();
        entity.setTitle(product.getTitle());
        entity.setDescription(product.getDescription());
        entity.setCount(entity.getCount() + product.getCount());
        entity.setCategories(product.getCategories());
        entity.setDateCreate(LocalDateTime.now());
        entity.setDateUpdate(LocalDateTime.now());
        entity.setClient(client.get());
        productRepository.save(entity);
    }
}
