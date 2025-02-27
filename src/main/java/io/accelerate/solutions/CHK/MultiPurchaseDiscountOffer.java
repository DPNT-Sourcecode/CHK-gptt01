package io.accelerate.solutions.CHK;

import java.util.Map;

public class MultiPurchaseDiscountOffer implements Offer {

    private final char sku;
    private final int count;
    private final int cost;

    public MultiPurchaseDiscountOffer(final char sku,
                                      final int count,
                                      final int cost) {
        this.sku = sku;
        this.count = count;
        this.cost = cost;
    }

    @Override
    public int apply(final Map<Character, Integer> order) {
        int startingCount = order.getOrDefault(sku, 0);

        int totalCost = (startingCount / this.count) * this.cost;

        int newCount = startingCount % this.count;

        order.put(sku, newCount);

        return totalCost;
    }

}

