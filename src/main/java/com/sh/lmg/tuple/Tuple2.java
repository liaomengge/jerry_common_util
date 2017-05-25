package com.sh.lmg.tuple;

import java.util.Optional;

public class Tuple2<A, B> extends Tuple {
    private A a;
    private B b;

    Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public Optional<A> _1() {
        return Optional.of(a);
    }

    public Optional<B> _2() {
        return Optional.of(b);
    }

    public <C> Optional<C> _3() {
        return Optional.empty();
    }

    public <D> Optional<D> _4() {
        return Optional.empty();
    }

    public <E> Optional<E> _5() {
        return Optional.empty();
    }


    public String toString() {
        return "Tuple2{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}