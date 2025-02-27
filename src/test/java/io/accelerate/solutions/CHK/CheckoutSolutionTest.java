package io.accelerate.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution checkout;

    @BeforeEach
    public void setup() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void checkout_invalid() {
        assertThat(checkout.checkout("ABCDE"), equalTo(-1));
    }

    @Test
    public void checkout_null() {
        assertThat(checkout.checkout(null), equalTo(0));
    }

    @Test
    public void checkout_empty() {
        assertThat(checkout.checkout(""), equalTo(0));
    }

    @Test
    public void checkout_valid_noOffer() {
        assertThat(checkout.checkout("ABCD"), equalTo(115));
    }

    @Test
    public void checkout_valid_withOffer() {
        assertThat(checkout.checkout("AAABB"), equalTo(175));
    }

    @Test
    public void checkout_valid_overOffer() {
        int y = (2*130) + (2*50) + (2*45) + 30 + 20 + 15;
        System.out.println(y);
        assertThat(checkout.checkout("AAA AAA AA BB BB B CD"), equalTo(515));
    }

}