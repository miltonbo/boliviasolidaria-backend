package com.bs.domain.utils;

import java.util.Collection;

/**
 * 
 * @author J. Milton Chambi M.
 * @param <T> Clase que se está evaluando para ver si esta o no el objeto presente
 */
public class Exist<T> {

    /**
     * Elemento por defecto
     */
    public static final Exist DOESNTEXIST = new Exist(null);

    /**
     *
     * @param <T>
     * @return
     */
    public static final <T> Exist<T> doesntexist () {
        return DOESNTEXIST;
    }

    private final T value;

    public Exist (final T value) {
        this.value = value;
    }

    public T get () {
        if (value == null) {
            throw new IllegalStateException("Null value");
        }
        return value;
    }

    public boolean exist () {
        return value != null;
    }

    public static <T> Exist<T> of (final T value) {
        if (value == null) {
            return DOESNTEXIST;
        }
        return new Exist<T>(value);
    }

    public static <T> Exist<T> firstElement (final Collection<T> list) {
        return list == null || list.isEmpty() ? DOESNTEXIST : of(list.iterator().next());
    }

}
