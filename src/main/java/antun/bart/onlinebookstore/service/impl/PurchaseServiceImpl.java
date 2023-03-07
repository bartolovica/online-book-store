package antun.bart.onlinebookstore.service.impl;

import antun.bart.onlinebookstore.model.BookCartItem;
import antun.bart.onlinebookstore.model.BookPurchaseRequest;
import antun.bart.onlinebookstore.model.entity.Book;
import antun.bart.onlinebookstore.model.entity.Customer;
import antun.bart.onlinebookstore.model.entity.Invoice;
import antun.bart.onlinebookstore.model.entity.InvoiceItem;
import antun.bart.onlinebookstore.model.enums.BookType;
import antun.bart.onlinebookstore.repository.InvoiceRepository;
import antun.bart.onlinebookstore.service.BookService;
import antun.bart.onlinebookstore.service.CustomerService;
import antun.bart.onlinebookstore.service.InventoryService;
import antun.bart.onlinebookstore.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static antun.bart.onlinebookstore.service.impl.CustomerServiceImpl.LOYALTY_BONUS_LIMIT;
import static antun.bart.onlinebookstore.util.MathOperations.calculatePercentage;
import static antun.bart.onlinebookstore.util.MathOperations.roundValueOnTwoDecimals;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    Logger LOG = LoggerFactory.getLogger(PurchaseServiceImpl.class);
    private final BookService bookService;
    private final CustomerService customerService;
    private final InventoryService inventoryService;
    private final InvoiceRepository invoiceRepository;

    public PurchaseServiceImpl(BookService bookService,
                               CustomerService customerService,
                               InventoryService inventoryService,
                               InvoiceRepository invoiceRepository) {
        this.bookService = bookService;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    @Transactional
    public Invoice purchaseBooks(BookPurchaseRequest bookPurchaseRequest) {
        Integer customerId = bookPurchaseRequest.getCustomerId();
        Double totalCost = 0.0;
        Integer customerPoints = 0;
        List<InvoiceItem> items = new ArrayList<>();
        List<Book> boughtBooks = new ArrayList<>();
        Customer customer = customerService.getCustomerById(customerId);

        List<BookCartItem> purchasedBooks = getBooksAvailableInInventory(bookPurchaseRequest.getBookCartItemList());
        if (purchasedBooks.isEmpty()) {
            throw new IllegalArgumentException("No books were purchased");
        } else {
            for (BookCartItem purchasedBook : purchasedBooks) {
                Integer purchasedBookId = purchasedBook.getBookId();
                Integer purchasedBookQuantity = purchasedBook.getQuantity();
                Book boughtBook = bookService.findBookByBookId(purchasedBookId);
                Double oldPrice = boughtBook.getPrice();
                LOG.debug("Book price: {}", oldPrice);
                Double bookDiscount = bookService.getBookDiscountByBookType(purchasedBook.getBookId(), purchasedBooks.size());
                Double bookPriceWithDiscount = roundValueOnTwoDecimals(oldPrice * bookDiscount.doubleValue());
                Double discountInPercentage = calculatePercentage(bookDiscount);
                LOG.debug("Book price with discount: {}% is: {}", discountInPercentage, bookPriceWithDiscount);
                totalCost += roundValueOnTwoDecimals(purchasedBookQuantity * bookPriceWithDiscount);
                items.add(new InvoiceItem(boughtBook, purchasedBookQuantity, oldPrice, discountInPercentage + "%", bookPriceWithDiscount, bookPriceWithDiscount * purchasedBookQuantity));
                inventoryService.reduceBookFromInventory(purchasedBookId, purchasedBookQuantity);
                customerPoints++;
                Book boughtBookUpdated = new Book(boughtBook.getBookName(), boughtBook.getBookType(), bookPriceWithDiscount, boughtBook.getGenre(), boughtBook.getAuthor());
                boughtBooks.add(boughtBookUpdated);
            }

            Integer totalPoints = customer.getLoyaltyPoints() + customerPoints;
            Boolean userDiscount = checkCustomerLoyaltyBonus(customer.getLoyaltyPoints() + customerPoints);
            if (userDiscount) {
                List<Book> regularOrOldBooks = getRegularOrOldBooks(boughtBooks);
                if (!regularOrOldBooks.isEmpty()) {
                    Book cheapestBook = regularOrOldBooks.get(0);
                    LOG.debug("Price of book {} that's for free: {}", cheapestBook.getBookName(), cheapestBook.getPrice());
                    purchasedBooks.remove(cheapestBook);
                    totalCost -= cheapestBook.getPrice();
                    LOG.debug("Customer {} got book: {} for free", customer.getUsername(), cheapestBook.getBookName());
                    customer = customerService.updateLoyaltyPoints(customer, totalPoints - LOYALTY_BONUS_LIMIT);
                }
            } else {
                Integer updatedPoints = purchasedBooks.size() + customer.getLoyaltyPoints();
                customer = customerService.updateLoyaltyPoints(customer, updatedPoints);
            }

            Invoice invoice = new Invoice(items, customer, userDiscount, roundValueOnTwoDecimals(totalCost), getCurrentDate());
            return invoiceRepository.save(invoice);
        }
    }

    private List<BookCartItem> getBooksAvailableInInventory(List<BookCartItem> bookCartItems) {
        return bookCartItems
                .stream()
                .filter(item -> inventoryService.checkIfCartItemIsAvailableInInventory(item.getBookId(), item.getQuantity()))
                .collect(Collectors.toList());
    }

    private List<Book> getRegularOrOldBooks(List<Book> books) {
        return books.stream()
                .filter(book -> book.getBookType() == BookType.REGULAR || book.getBookType() == BookType.OLD_EDITIONS)
                .sorted(Comparator.comparing(Book::getPrice))
                .collect(Collectors.toList());
    }

    private Boolean checkCustomerLoyaltyBonus(Integer points) {
        return (points > LOYALTY_BONUS_LIMIT);
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(currentDate);
    }
}
