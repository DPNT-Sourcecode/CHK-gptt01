package io.accelerate.solutions.CHK;

import java.util.List;
import java.util.Map;

public class GroupItemDiscountOffer implements Offer {

    private final List<Character> skus;
    private final int count;
    private final int cost;

    public GroupItemDiscountOffer(final List<Character> skus,
                                  final int count,
                                  final int cost) {
        this.skus = skus;
        this.count = count;
        this.cost = cost;
    }

    @Override
    public int apply(final Map<Character, Integer> order) {
        int applications = this.skus.stream().mapToInt(sku -> order.getOrDefault(sku, 0)).sum() / this.count;

        int toRemove = applications * this.count;

        for (final Character sku : this.skus) {
            int currentCount = order.get(sku);
            int newCount = Math.max(0, currentCount - toRemove);
            toRemove -= currentCount;

            order.put(sku, newCount);

            if (toRemove <= 0) break;
        }

        return applications * this.cost;
    }

}
