package demo.annotations;

import demo.enums.Authors;
import demo.enums.CategoryType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Framework Annotation is user built annotation which is annotated on top of test methods to log the author details and
 * category details to the extent report.
 * <p>
 * Runtime retention value indicate that this annotation will be available at run time for reflection operations.
 *
 * @author SURENDIRAN
 */
@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotations {

    Authors[] author();

    CategoryType[] category();

}
