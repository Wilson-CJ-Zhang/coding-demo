package pers.wilson.demo.discount;

public class DiscountFactory {
    public static Discount getDiscountByType(DiscountType discountType) {
        return switch (discountType) {
            case BUY_ONE_GET_ONE_FREE -> new BuyOneGetOneFreeDiscount();
            case BUY_THREE_FOR_PRICE_OF_TWO -> new BuyThreeForPriceOfTwoDiscount();
            default -> new NoneDiscount();
        };
    }
}
