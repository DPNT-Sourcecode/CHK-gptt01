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

        if (!order.containsKey(this.sku)) return 0;

        int startingCount = order.get(this.sku);
        int totalCost = (startingCount / this.count) * this.cost;
        int newCount = startingCount % this.count;

        order.put(this.sku, newCount);

        return totalCost;
    }

}


