package antun.bart.onlinebookstore.service;

import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.BookPurchaseRequest;
import antun.bart.onlinebookstore.model.entity.Invoice;

public interface PurchaseService {

    /**
     * Purchase books from online book store shopping cart for regarding customer
     *
     * @param bookPurchaseRequest
     * @return
     */
    Invoice purchaseBooks(BookPurchaseRequest bookPurchaseRequest) throws BookNotFoundException;
}
