package io.accelerate.solutions.SUM;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SumSolutionTest {

    private SumSolution sum;

    @BeforeEach
    public void setUp() {
        sum = new SumSolution();
    }

    @Test
    public void compute_sum() {
        assertThat(sum.compute(1, 1), equalTo(2));
    }

    @Test
    public void compute_sum_min() {
        assertThat(sum.compute(0, 0), equalTo(0));
    }

    @Test
    public void compute_sum_max() {
        assertThat(sum.compute(100, 100), equalTo(200));
    }
}

