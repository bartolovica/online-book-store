package antun.bart.onlinebookstore.repository;

import antun.bart.onlinebookstore.model.BookDto;
import antun.bart.onlinebookstore.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT new antun.bart.onlinebookstore.model.BookDto(i.book.bookId, i.book.bookName, i.itemQuantity) " +
            "FROM Inventory i " +
            "WHERE i.itemQuantity > 0")
    List<BookDto> getAllAvailableBooks();

    @Transactional(readOnly = true)
    Optional<Inventory> findByBookBookId(@Param("bookId") Integer bookId);

    @Transactional
    @Modifying
    @Query("UPDATE Inventory i SET i.itemQuantity = i.itemQuantity - :quantity WHERE i.book.id = :bookId")
    void updateItemCountByBookId(@Param("bookId") Integer bookId, @Param("quantity") Integer quantity);
}