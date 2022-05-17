package com.ampamt.moduler.common;

import org.hibernate.HibernateException;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomizedResultTransformer extends AliasedTupleSubsetResultTransformer {

    private static final long serialVersionUID = -3779317531110592988L;

    @SuppressWarnings("rawtypes")
    private final Class resultClass;
    private Setter[] setters;
    private List<Field> fields = new ArrayList<>();
    private String[] aliases;

    @SuppressWarnings("rawtypes")
    public CustomizedResultTransformer(final Class resultClass) {
        if (resultClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        }
        this.resultClass = resultClass;
        for (Field field : this.resultClass.getDeclaredFields()) {
            if (!(Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))) {
                this.fields.add(field);
            }
        }
    }

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    @Override
    public Object transformTuple(final Object[] tuple, final String[] aliases) {
        Object result;

        try {
            if (this.setters == null) {
                this.aliases = aliases;

                setSetters(aliases);
            }
            result = this.resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if (this.setters[i] != null && this.setters[i].getMethod() != null) {
                    Type parameterType = this.setters[i].getMethod().getGenericParameterTypes()[0];
                    if ("class java.lang.Long".equals(parameterType.toString()) && tuple[i] != null) {
                        tuple[i] = Long.parseLong(String.valueOf(tuple[i]));
                    } else if ("class java.lang.Character".equals(parameterType.toString()) && tuple[i] != null) {
                        tuple[i] = String.valueOf(tuple[i]).charAt(0);
                    } else if ("class java.lang.Integer".equals(parameterType.toString()) && tuple[i] != null && "class java.math.BigDecimal".equals(tuple[i].getClass().toString())) {
                        tuple[i] = Integer.valueOf(tuple[i].toString());
                    } else if ("class java.math.BigDecimal".equals(parameterType.toString()) && tuple[i] != null && "class java.lang.Long".equals(tuple[i].getClass().toString())) {
                        tuple[i] = new BigDecimal(tuple[i].toString());
                    }
                    this.setters[i].set(result, tuple[i], null);
                }
            }
        } catch (final InstantiationException | IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName(), e);
        }

        return result;
    }

    private void setSetters(final String[] aliases) {
        PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(
                PropertyAccessStrategyBasicImpl.INSTANCE,
                PropertyAccessStrategyFieldImpl.INSTANCE,
                PropertyAccessStrategyMapImpl.INSTANCE
        );

        this.setters = new Setter[aliases.length];
        for (int i = 0; i < aliases.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                alias = alias.replaceAll("_", "");
                for (final Field field : this.fields) {
                    final String fieldName = field.getName();
                    if (fieldName.equalsIgnoreCase(alias)) {
                        alias = fieldName;
                        break;
                    }
                }
                setters[i] = propertyAccessStrategy.buildPropertyAccess(resultClass, alias).getSetter();
            }
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List transformList(final List collection) {
        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomizedResultTransformer that = (CustomizedResultTransformer) o;

        if (!resultClass.equals(that.resultClass)) {
            return false;
        }
        return Arrays.equals(aliases, that.aliases);
    }

    @Override
    public int hashCode() {
        int result = resultClass.hashCode();
        result = 31 * result + (aliases != null ? Arrays.hashCode(aliases) : 0);
        return result;
    }

}
