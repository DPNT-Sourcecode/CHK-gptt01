package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<Character, Integer> PRICES = Map.of(
            | A    | 50    | 3A for 130, 5A for 200 |
            | B    | 30    | 2B for 45              |
            | C    | 20    |                        |
            | D    | 15    |                        |
            | E    | 40    | 2E get one B free      |
            | F    | 10    | 2F get one F free      |
            | G    | 20    |                        |
            | H    | 10    | 5H for 45, 10H for 80  |
            | I    | 35    |                        |
            | J    | 60    |                        |
            | K    | 80    | 2K for 150             |
            | L    | 90    |                        |
            | M    | 15    |                        |
            | N    | 40    | 3N get one M free      |
            | O    | 10    |                        |
            | P    | 50    | 5P for 200             |
            | Q    | 30    | 3Q for 80              |
            | R    | 50    | 3R get one Q free      |
            | S    | 30    |                        |
            | T    | 20    |                        |
            | U    | 40    | 3U get one U free      |
            | V    | 50    | 2V for 90, 3V for 130  |
            | W    | 20    |                        |
            | X    | 90    |                        |
            | Y    | 10    |                        |
            | Z    | 50    |
            );

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

