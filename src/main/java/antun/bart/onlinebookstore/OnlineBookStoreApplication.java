package antun.bart.onlinebookstore;

import antun.bart.onlinebookstore.repository.BookRepository;
import antun.bart.onlinebookstore.repository.CustomerRepository;
import antun.bart.onlinebookstore.repository.InventoryRepository;
import antun.bart.onlinebookstore.util.DataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public DataLoader testDataLoader(BookRepository bookRepository, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
        return new DataLoader(bookRepository, inventoryRepository, customerRepository);
    }

}
