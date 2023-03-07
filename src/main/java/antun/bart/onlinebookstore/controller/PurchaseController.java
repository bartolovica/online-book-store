package antun.bart.onlinebookstore.controller;

import antun.bart.onlinebookstore.model.BookPurchaseRequest;
import antun.bart.onlinebookstore.model.entity.Invoice;
import antun.bart.onlinebookstore.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static antun.bart.onlinebookstore.controller.ControllerDefinitions.PURCHASE_API;
import static org.springframework.http.ResponseEntity.ok;

@Validated
@RestController
@RequestMapping(PURCHASE_API)
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Invoice> purchaseBook(@Valid @RequestBody BookPurchaseRequest bookPurchaseRequest) {
        return ok(purchaseService.purchaseBooks(bookPurchaseRequest));
    }
}
