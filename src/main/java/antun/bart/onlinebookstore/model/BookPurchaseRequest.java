package antun.bart.onlinebookstore.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookPurchaseRequest {

    @NotNull(message = "Customer id cannot be null")
    Integer customerId;

    @NotNull(message = "No selected books in purchased items")
    @Valid
    List<BookCartItem> bookCartItemList;
}
