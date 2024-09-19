package pers.wilson.demo.discount;

public class NoneDiscount implements Discount {
    @Override
    public double apply(double price, int number) {
        return price * number;
    }
}
