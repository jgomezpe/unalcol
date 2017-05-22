package unalcol.algorithm.iterative;

import unalcol.math.function.Function;

public interface StepFunction<I,O> extends Function<O,O>{
    public O init(I input);
}
