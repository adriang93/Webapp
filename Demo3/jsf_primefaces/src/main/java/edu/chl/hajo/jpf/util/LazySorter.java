package edu.chl.hajo.jpf.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 * Generic sorting utility class
 *
 * @param <T>
 */
public class LazySorter<T> implements Comparator<T> {

    private final String sortField;
    private final SortOrder sortOrder;

    /**
     * initializing sorting field and sorting order
     *
     * @param sortField
     * @param sortOrder
     */
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    /**
     * Comparing object1 and object2 with reflection (possible bad
     *
     * @param object1
     * @param object2
     * @return
     */
    @Override
    public int compare(T object1, T object2) {
        try {
            Field field1 = object1.getClass().getDeclaredField(this.sortField);
            Field field2 = object2.getClass().getDeclaredField(this.sortField);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object value1 = field1.get(object1);
            Object value2 = field2.get(object2);

            int value = ((Comparable) value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}