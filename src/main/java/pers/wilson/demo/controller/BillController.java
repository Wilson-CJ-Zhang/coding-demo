package pers.wilson.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.wilson.demo.model.Bill;
import pers.wilson.demo.service.BillService;
import pers.wilson.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Tag(
        name = "BillControllerAPI",
        description = "API for bill"
)
public class BillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);
    private final ProductService productService;
    private final BillService billService;

    public BillController(ProductService productService, BillService billService) {
        this.productService = productService;
        this.billService = billService;
    }

    @Operation(
            summary = "bill",
            description = "calculate the bill for a shopping list",
            parameters = {
                    @Parameter(name = "productNames", description = "shopping list", required = true, example = "[\"Apple\", \"Banana\", \"Melon\", \"Lime\"]")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "process success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "invalid input"
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<Bill> bill(@RequestBody List<String> productNames) {
        LOGGER.info("receive bill:{}", productNames);

        if (productService.isInvalid(productNames)) {
            LOGGER.warn("Invalid productNames:{}", productNames);
            return ResponseEntity.badRequest().build();
        }

        Bill bill = billService.calculate(productNames);
        LOGGER.info("calculated bill:{}", bill);

        return ResponseEntity.ok(bill);
    }
}
