package antun.bart.onlinebookstore.service.impl;

import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.entity.Book;
import antun.bart.onlinebookstore.repository.BookRepository;
import antun.bart.onlinebookstore.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private static final Double REGULAR_BOOK_DISCOUNT = 0.1;
    private static final Double OLD_EDITIONS_BOOK_DISCOUNT = 0.2;
    private static final Double OLD_EDITIONS_BOOK_EXTRA_DISCOUNT = 0.05;
    private static final Double ZERO = 0.0;
    private static final Double NO_DISCOUNT = 1.00;
    private static final int BUNDLE_LIMIT = 3;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Double getBookDiscountByBookType(Integer bookId, Integer bundle) {

        Book book = findBookByBookId(bookId);
        Double discount;

        switch (book.getBookType()) {

            case REGULAR:
                discount = calculateBookDiscount(REGULAR_BOOK_DISCOUNT, bundle, ZERO);
                break;

            case OLD_EDITIONS:
                discount = calculateBookDiscount(OLD_EDITIONS_BOOK_DISCOUNT, bundle, OLD_EDITIONS_BOOK_EXTRA_DISCOUNT);
                break;

            default:
                discount = NO_DISCOUNT;
                break;
        }
        return discount;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookByBookId(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book %s not found", bookId)));
    }

    private Double calculateBookDiscount(Double baseDiscount, Integer bundle, Double extraDiscount) {
        Double discount = baseDiscount;
        if (bundle >= BUNDLE_LIMIT) {
            discount = (1 - baseDiscount) * (1 - extraDiscount);
        }
        return discount;
    }
}
