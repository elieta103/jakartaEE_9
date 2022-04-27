package org.elhg.apiservlet.webapp.cdi.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.elhg.apiservlet.webapp.cdi.interceptors.Logging;
import org.elhg.apiservlet.webapp.cdi.interceptors.TransactionalJdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TransactionalJdbc
@Logging
@ApplicationScoped
@Stereotype
@Named
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}

/*
@Stereotype
A CDI stereotype allows you to create a annotation that club together several  CDI annotations.
For example, if we needed to create several CDI named beans with a scope of session,
we would have to use two annotations in each of these beans,
namely @Named and @SessionScoped. Instead of providing two annotations to each of the beans,
you would create a stereotype, and then annotate the beans with it.
 */