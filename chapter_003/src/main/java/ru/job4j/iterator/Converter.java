package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter Iterators to Iterator
 */
public class Converter {
    /**
     * @param its Iterators array
     * @return converted iterator
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {

        return new Iterator<>() {
            Iterator<Integer> iterator = its.next();

            /**
             * @return true if next value
             */
            @Override
            public boolean hasNext() {
                boolean res = false;
                if (iterator != null && iterator.hasNext()) {
                    res = true;
                } else {
                    while (its.hasNext()) {
                        iterator = its.next();
                        if (iterator.hasNext()) {
                            res = true;
                            break;
                        }
                    }
                }
                return res;
            }

            /**
             * @return next value
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No such");
                }
                return iterator.next();
            }
        };
    }
}
