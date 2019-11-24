package ua.skillsup.practice.warehouse.services;

import org.springframework.stereotype.Service;
import ua.skillsup.practice.warehouse.model.Client;
import ua.skillsup.practice.warehouse.model.Product;
import ua.skillsup.practice.warehouse.repositories.TransactionRepository;
import ua.skillsup.practice.warehouse.repositories.entities.TransactionEntity;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void addTransaction(Client client, Product product, boolean add) {
        String uid = UUID.randomUUID().toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        TransactionEntity entity = new TransactionEntity();
        entity.setTransId(product.getDateUpdate().format(dateTimeFormatter)+uid);
        entity.setClientId(client.getId());
        entity.setAdd(add);
        entity.setDateUpdate(product.getDateUpdate());
        entity.setCount(product.getCount());
        transactionRepository.save(entity);
    }
}
