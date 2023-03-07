package antun.bart.onlinebookstore.controller;

import antun.bart.onlinebookstore.model.entity.Book;
import antun.bart.onlinebookstore.model.BookDto;
import antun.bart.onlinebookstore.repository.BookRepository;
import antun.bart.onlinebookstore.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static antun.bart.onlinebookstore.controller.ControllerDefinitions.INVENTORY_API;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(INVENTORY_API)
public class InventoryController {

    private final InventoryService inventoryService;
    private final BookRepository bookRepository;

    public InventoryController(InventoryService inventoryService, BookRepository bookRepository) {
        this.inventoryService = inventoryService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ok(bookRepository.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAllAvailableBooks() {
        return ok(inventoryService.getAllAvailableBooks());
    }
}
