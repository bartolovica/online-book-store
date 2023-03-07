package antun.bart.onlinebookstore.service;

import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.BookDto;
import antun.bart.onlinebookstore.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    /**
     * Get all books available in book store
     * @return
     */
    List<BookDto> getAllAvailableBooks();

    /**
     * Check if Book in shopping cart is available in inventory
     * @param bookId
     * @param itemCount
     * @return
     */
     Boolean checkIfCartItemIsAvailableInInventory(Integer bookId, Integer itemCount) throws BookNotFoundException;

    /**
     *
     * @param bookId
     * @param quantity
     */
    void reduceBookFromInventory(Integer bookId, Integer quantity);

    /**
     *
     * @param bookId
     * @return
     */
    Inventory getInventoryByBookId(Integer bookId);

}
