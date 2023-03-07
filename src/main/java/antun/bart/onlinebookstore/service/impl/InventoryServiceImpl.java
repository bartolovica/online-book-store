package antun.bart.onlinebookstore.service.impl;

import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.BookDto;
import antun.bart.onlinebookstore.model.entity.Inventory;
import antun.bart.onlinebookstore.repository.InventoryRepository;
import antun.bart.onlinebookstore.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllAvailableBooks() {
        return inventoryRepository.getAllAvailableBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkIfCartItemIsAvailableInInventory(Integer bookId, Integer quantity) {
        Inventory inventoryItem = getInventoryByBookId(bookId);
        Integer inventoryItemCount = inventoryItem.getItemQuantity();
        if (quantity > inventoryItemCount) {
            throw new IllegalArgumentException(String.format("Wanted quantity of book: %s is not available in book store." +
                    " The available quantity is: %s", bookId, inventoryItemCount));
        } else return true;
    }

    @Override
    @Transactional
    public void reduceBookFromInventory(Integer bookId, Integer quantity) {
        inventoryRepository.updateItemCountByBookId(bookId, quantity);
    }

    @Override
    @Transactional(readOnly = true)
    public Inventory getInventoryByBookId(Integer bookId) {
        return inventoryRepository.findByBookBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book %s not found in the inventory", bookId)));
    }
}
