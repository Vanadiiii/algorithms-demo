package me.imatveev.leetcode;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<T> predicate;
    private T value;

    public FilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        shiftToNext();
    }


    @Override
    public boolean hasNext() {
        return value != null;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (this.value == null) {
            throw new NoSuchElementException("OOops");
        }

        final T value = this.value;
        shiftToNext();

        return value;
    }

    private void shiftToNext() {
        while (iterator.hasNext()) {
            final T next = iterator.next();
            if (predicate.test(next)) {
                this.value = next;
                return;
            }
        }
        this.value = null;
    }
}
