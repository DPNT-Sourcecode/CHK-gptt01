package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private static final String SHOP = """
            | A    | 50    | 3A for 130, 5A for 200          |
            | B    | 30    | 2B for 45                       |
            | C    | 20    |                                 |
            | D    | 15    |                                 |
            | E    | 40    | 2E get one B free               |
            | F    | 10    | 2F get one F free               |
            | G    | 20    |                                 |
            | H    | 10    | 5H for 45, 10H for 80           |
            | I    | 35    |                                 |
            | J    | 60    |                                 |
            | K    | 70    | 2K for 120                      |
            | L    | 90    |                                 |
            | M    | 15    |                                 |
            | N    | 40    | 3N get one M free               |
            | O    | 10    |                                 |
            | P    | 50    | 5P for 200                      |
            | Q    | 30    | 3Q for 80                       |
            | R    | 50    | 3R get one Q free               |
            | S    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
            | T    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
            | U    | 40    | 3U get one U free               |
            | V    | 50    | 2V for 90, 3V for 130           |
            | W    | 20    |                                 |
            | X    | 17    | buy any 3 of (S,T,X,Y,Z) for 45 |
            | Y    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
            | Z    | 21    | buy any 3 of (S,T,X,Y,Z) for 45 |""";

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
        PRICES.put('X', 17);
        PRICES.put('Y', 20);
        PRICES.put('Z', 21);
    }

    private static final Map<Character, List<Offer>> OFFERS = Map.of(
            'A', List.of(
                    new MultiPurchaseDiscountOffer('A', 5, 200),
                    new MultiPurchaseDiscountOffer('A', 3, 130)
            ),
            'B', List.of(
                    new GetSomethingFreeOffer('B', 'E', 2),
                    new MultiPurchaseDiscountOffer('B', 2, 45)
            ),
            'F', List.of(
                    new MultiPurchaseDiscountOffer('F', 3, 20)
            ),
            'H', List.of(
                    new MultiPurchaseDiscountOffer('H', 10, 80),
                    new MultiPurchaseDiscountOffer('H', 5, 45)
            ),
            'K', List.of(
                    new MultiPurchaseDiscountOffer('K', 2, 150)
            ),
            'M', List.of(
                    new GetSomethingFreeOffer('M', 'N', 3)
            ),
            'P', List.of(
                    new MultiPurchaseDiscountOffer('P', 5, 200)
            ),
            'Q', List.of(
                    new GetSomethingFreeOffer('Q', 'R', 3),
                    new MultiPurchaseDiscountOffer('Q', 3, 80)
            ),
            'U', List.of(
                    new MultiPurchaseDiscountOffer('U', 4, 120)
            ),
            'V', List.of(
                    new MultiPurchaseDiscountOffer('V', 3, 130),
                    new MultiPurchaseDiscountOffer('V', 2, 90)
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
