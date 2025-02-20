package antun.bart.onlinebookstore.service;


import antun.bart.onlinebookstore.model.entity.Book;

public interface BookService {
    /**
     * Get book discount by book type (REGULAR or OLD_EDITIONS)
     *
     * @param bookId
     * @param bundle
     * @return
     */
    Double getBookDiscountByBookType(Integer bookId, Integer bundle);

    /**
     * @param bookId
     * @return
     */
    Book findBookByBookId(Integer bookId);
}
