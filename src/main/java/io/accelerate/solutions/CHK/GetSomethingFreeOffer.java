package io.accelerate.solutions.CHK;

import java.util.Map;

public class GetSomethingFreeOffer implements Offer {

    private final char freeSKU;
    private final char requiredSKU;
    private final int amountNeeded;

    public GetSomethingFreeOffer(final char freeSKU,
                                 final char requiredSKU,
                                 final int amountNeeded) {
        this.freeSKU = freeSKU;
        this.requiredSKU = requiredSKU;
        this.amountNeeded = amountNeeded;
    }

    @Override
    public int apply(final Map<Character, Integer> order) {
        order.put(freeSKU, Math.max(0, order.getOrDefault(freeSKU, 0) - order.getOrDefault(requiredSKU, 0) / amountNeeded));
        return 0;
    }

}
