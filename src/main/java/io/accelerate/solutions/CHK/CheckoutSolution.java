package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<Character, Integer> PRICES = new HashMap<>();
    static {
        PRICES.put('A', 50);
        PRICES.put('B', 30);
        PRICES.put('C', 20);
        PRICES.put('D', 15);
        PRICES.put('E', 40);
        PRICES.put('F', 10);
        PRICES.put('G', 20);
        PRICES.put('H', 10);
        PRICES.put('I', 35);
        PRICES.put('J', 60);
        PRICES.put('K', 80);
        PRICES.put('L', 90);
        PRICES.put('M', 15);
        PRICES.put('N', 40);
        PRICES.put('O', 10);
        PRICES.put('P', 50);
        PRICES.put('Q', 30);
        PRICES.put('R', 50);
        PRICES.put('S', 30);
        PRICES.put('T', 20);
        PRICES.put('U', 40);
        PRICES.put('V', 50);
        PRICES.put('W', 20);
        PRICES.put('X', 90);
        PRICES.put('Y', 10);
        PRICES.put('Z', 50);
    }

    private static final Map<Character, List<Offer>> OFFERS = Map.of(
            'A', List.of(
                    new MultiPurchaseDiscountOffer('A', 5, 200),
                    new MultiPurchaseDiscountOffer('A', 3, 130)
            ),
            'B', List.of(
                    order -> {
                        order.put('B', Math.max(0, order.getOrDefault('B', 0) - order.getOrDefault('E', 0) / 2));
                        return 0;
                    },
                    new MultiPurchaseDiscountOffer('B', 2, 45)
            ),
            'F', List.of(
                    new MultiPurchaseDiscountOffer('F', 3, 20)
            )
    );

    public Integer checkout(String skus) {

        // Validate SKUs
        if (skus == null) return 0;
        if (!isValidSKUs(skus)) return -1;

        // Build a map of SKU to count - the 'order'
        final Map<Character, Integer> order = buildOrderFromSKUs(skus);

        return totalOffers(order) + totalOrder(order);
    }

    private static boolean isValidSKUs(final String skus) {
        return skus.codePoints().allMatch(sku -> PRICES.containsKey((char) sku));
    }

    private static Map<Character, Integer> buildOrderFromSKUs(final String skus) {
        final Map<Character, Integer> order = new HashMap<>();

        skus.codePoints()
                .forEach(sku -> order.put(
                        (char) sku,
                        order.getOrDefault((char) sku, 0) + 1));

        return order;
    }

    private static int totalOrder(final Map<Character, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(entry ->
                        PRICES.get(entry.getKey()) * entry.getValue()
                )
                .sum();
    }

    // Warning - this modifies the map in place and removes products that have been purchased via an offer
    private static int totalOffers(final Map<Character, Integer> order) {
        int total = 0;

        // Loop through the items and apply offers
        for (final Map.Entry<Character, Integer> item : order.entrySet()) {

            if (!OFFERS.containsKey(item.getKey())) continue; // No offer for item

            // Apply all offers for that item and add to the total cost
            total += OFFERS.get(item.getKey()).stream().mapToInt(offer -> offer.apply(order)).sum();
        }

        return total;
    }

}


