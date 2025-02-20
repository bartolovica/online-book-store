package antun.bart.onlinebookstore.service;

import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.BookDto;
import antun.bart.onlinebookstore.model.entity.Inventory;

import java.util.List;

public interface InventoryService {

    List<BookDto> getAllAvailableBooks();

    Boolean checkIfCartItemIsAvailableInInventory(Integer bookId, Integer itemCount) throws BookNotFoundException;

    void reduceBookFromInventory(Integer bookId, Integer quantity);

    Inventory getInventoryByBookId(Integer bookId);

}
