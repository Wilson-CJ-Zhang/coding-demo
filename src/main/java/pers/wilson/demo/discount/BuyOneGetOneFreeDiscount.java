package pers.wilson.demo.discount;

import java.math.BigInteger;

public class BuyOneGetOneFreeDiscount implements Discount {
    @Override
    public double apply(double price, int number) {
        BigInteger[] divideAndRemainder = BigInteger.valueOf(number).divideAndRemainder(BigInteger.valueOf(2));
        return (divideAndRemainder[0].intValue() + divideAndRemainder[1].intValue()) * price;
    }
}
