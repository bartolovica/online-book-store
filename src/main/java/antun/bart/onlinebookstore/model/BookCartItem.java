package antun.bart.onlinebookstore.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCartItem {

    @NotNull(message = "Book id has to be populated")
    private int bookId;

    @NotNull(message = "Quantity of book cannot be null")
    @Min(value = 1, message = "quantity id can't equal or less than 0")
    private int quantity;
}
