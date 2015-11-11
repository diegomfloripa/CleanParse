package br.com.diegomelo.cleanparse.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Prevents the mapping of a type Pojo in the representation of serialization.
 * @author Diego Melo
 * @since 10/11/2015
 */
@Retention(RUNTIME) @Target({FIELD	})
public @interface ParseTransient {

}
