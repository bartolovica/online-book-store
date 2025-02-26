package antun.bart.onlinebookstore.service.impl;

import antun.bart.onlinebookstore.config.BookDiscountProperties;
import antun.bart.onlinebookstore.exception.BookNotFoundException;
import antun.bart.onlinebookstore.model.entity.Book;
import antun.bart.onlinebookstore.repository.BookRepository;
import antun.bart.onlinebookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {


    private final BookDiscountProperties properties;
    private final BookRepository bookRepository;


    @Override
    @Transactional(readOnly = true)
    public Double getBookDiscountByBookType(Integer bookId, Integer bundle) {

        Book book = findBookByBookId(bookId);
        Double discount;
        BookDiscountProperties.Discount discountProperties = properties.discount();

        switch (book.getBookType()) {

            case REGULAR:
                discount = calculateBookDiscount(discountProperties.regular(), bundle, discountProperties.zero());
                break;

            case OLD_EDITIONS:
                discount = calculateBookDiscount(discountProperties.oldEditions(), bundle, discountProperties.oldEditionsExtra());
                break;

            default:
                discount = discountProperties.noDiscount();
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
        if (bundle >= properties.bundleLimit()) {
            discount = (1 - baseDiscount) * (1 - extraDiscount);
        }
        return discount;
    }
}
