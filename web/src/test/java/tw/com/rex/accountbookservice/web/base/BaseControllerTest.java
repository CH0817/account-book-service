package tw.com.rex.accountbookservice.web.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
public class BaseControllerTest {

    protected static ObjectMapper mapper;

    @BeforeClass
    public static void init() {
        mapper = getObjectMapper();
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new JavaTimeModule());
        return mapper;
    }

}
