package pl.szymon.gretka.services.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import java.lang.annotation.ElementType;

@Qualifier 
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Median {

}
