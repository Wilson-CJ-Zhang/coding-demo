package pers.wilson.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Bill {
    private List<Cart> carts;
    private double total;
}
