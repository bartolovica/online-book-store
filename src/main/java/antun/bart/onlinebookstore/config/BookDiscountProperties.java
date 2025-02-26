package antun.bart.onlinebookstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "book")
public record BookDiscountProperties(
        Discount discount,
        Integer bundleLimit
) {
    public record Discount(Double regular,
                    Double oldEditions,
                    Double oldEditionsExtra,
                    Double zero,
                    Double noDiscount) {

    }
}
