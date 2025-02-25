package antun.bart.onlinebookstore.service;


import antun.bart.onlinebookstore.model.entity.Book;

public interface BookService {

    Double getBookDiscountByBookType(Integer bookId, Integer bundle);

    Book findBookByBookId(Integer bookId);
}
