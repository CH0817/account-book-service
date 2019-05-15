package tw.com.rex.accountbookservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NecessaryData {

    enum DLL {INSERT, UPDATE, DELETE}

    DLL[] useIn();

}
