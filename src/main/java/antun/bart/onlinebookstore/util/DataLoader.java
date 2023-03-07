package antun.bart.onlinebookstore.util;

import antun.bart.onlinebookstore.model.entity.Book;
import antun.bart.onlinebookstore.model.entity.Customer;
import antun.bart.onlinebookstore.model.entity.Inventory;
import antun.bart.onlinebookstore.model.enums.BookType;
import antun.bart.onlinebookstore.model.enums.Genre;
import antun.bart.onlinebookstore.repository.BookRepository;
import antun.bart.onlinebookstore.repository.CustomerRepository;
import antun.bart.onlinebookstore.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private static final String BOOK_INSERTED = "Book inserted: {} id, name {} ,price {}";
    private static final String CUSTOMER_CREATED = "Customer {} id {} created";
    Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private final BookRepository bookRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;

    public DataLoader(BookRepository bookRepository,
                      InventoryRepository inventoryRepository,
                      CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        inventoryRepository.deleteAll();
        bookRepository.deleteAll();
        customerRepository.deleteAll();

        Book harryPotter = new Book("Harry Potter", BookType.NEW_RELEASES, 100.00, Genre.FICTION, "J.K. Rowling");
        bookRepository.save(harryPotter);
        inventoryRepository.save(new Inventory(harryPotter, 100));
        LOG.debug(BOOK_INSERTED, harryPotter.getBookId(), harryPotter.getBookName(), harryPotter.getPrice());

        Book animalFarm = new Book("Animal Farm", BookType.REGULAR, 6.99, Genre.FICTION, "George Orwell");
        bookRepository.save(animalFarm);
        inventoryRepository.save(new Inventory(animalFarm, 20));
        LOG.debug(BOOK_INSERTED, animalFarm.getBookId(), animalFarm.getBookName(), animalFarm.getPrice());

        Book theOldManAndTheSea = new Book("The Old Man and the Sea", BookType.REGULAR, 20.00, Genre.DRAMA, "Ernest Hemingway");
        bookRepository.save(theOldManAndTheSea);
        inventoryRepository.save(new Inventory(theOldManAndTheSea, 40));
        LOG.debug(BOOK_INSERTED, theOldManAndTheSea.getBookId(), theOldManAndTheSea.getBookName(), theOldManAndTheSea.getPrice());

        Book naDriniCuprija = new Book("Na Drini chuprija", BookType.OLD_EDITIONS, 9.99, Genre.DRAMA, "Ivo Andric");
        bookRepository.save(naDriniCuprija);
        inventoryRepository.save(new Inventory(naDriniCuprija, 0));
        LOG.debug(BOOK_INSERTED, naDriniCuprija.getBookId(), naDriniCuprija.getBookName(), naDriniCuprija.getPrice());

        Book karenjina = new Book("A. Karenjina", BookType.OLD_EDITIONS, 10.00, Genre.DRAMA, "L.N. Tolstoj");
        bookRepository.save(karenjina);
        inventoryRepository.save(new Inventory(karenjina, 1));
        LOG.debug(BOOK_INSERTED, karenjina.getBookId(), karenjina.getBookName(), karenjina.getPrice());

        Customer customerOne = new Customer("johnB", "john@gmail.com", 9);
        customerRepository.save(customerOne);
        LOG.debug(CUSTOMER_CREATED, customerOne.getCustomerId(), customerOne.getUsername());
        Customer customerTwo = new Customer("mateMatic", "matematic@gmail.com", 4);
        customerRepository.save(customerTwo);
        LOG.debug(CUSTOMER_CREATED, customerTwo.getCustomerId(), customerTwo.getUsername());
        Customer customerThree = new Customer("johnDoe", "johnDoe@gmail.com", 0);
        customerRepository.save(customerThree);
        LOG.debug(CUSTOMER_CREATED, customerThree.getCustomerId(), customerThree.getUsername());
    }

}
