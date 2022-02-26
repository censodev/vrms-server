package io.github.censodev.vrms.vrmsserver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.censodev.vrms.vrmsserver.utils.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonTests {
    @Test
    void testMapInsensitiveEnums() throws Exception {
        var mapper = BeanUtil.getBean(ObjectMapper.class);
        TestObject uppercase =
                mapper.readValue("{ \"testEnum\": \"ONE\" }", TestObject.class);
        TestObject lowercase =
                mapper.readValue("{ \"testEnum\": \"one\" }", TestObject.class);
        TestObject mixedcase =
                mapper.readValue("{ \"testEnum\": \"oNe\" }", TestObject.class);
        System.out.println(uppercase.toString());
        System.out.println(lowercase.toString());
        System.out.println(mixedcase.toString());
        if (uppercase.testEnum != TestEnum.ONE) throw new Exception("cannot deserialize uppercase value");
        if (lowercase.testEnum != TestEnum.ONE) throw new Exception("cannot deserialize lowercase value");
        if (mixedcase.testEnum != TestEnum.ONE) throw new Exception("cannot deserialize mixedcase value");

        System.out.println("Success: all deserializations worked");
    }

    static class TestObject {
        private TestEnum testEnum;

        public TestEnum getTestEnum() {
            return testEnum;
        }

        public void setTestEnum(TestEnum testEnum) {
            this.testEnum = testEnum;
        }

        @Override
        public String toString() {
            return "TestObject{" +
                    "testEnum=" + testEnum +
                    '}';
        }
    }

    enum TestEnum {
        ONE,
    }
}
