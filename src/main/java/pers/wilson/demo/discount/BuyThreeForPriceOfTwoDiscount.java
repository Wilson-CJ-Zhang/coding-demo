package pers.wilson.demo.discount;

import java.math.BigInteger;

public class BuyThreeForPriceOfTwoDiscount implements Discount {
    @Override
    public double apply(double price, int number) {
        BigInteger[] divideAndRemainder = BigInteger.valueOf(number).divideAndRemainder(BigInteger.valueOf(3));
        return (divideAndRemainder[0].intValue() * 2 + divideAndRemainder[1].intValue()) * price;
    }
}
