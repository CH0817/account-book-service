package tw.com.rex.accountbookservice.define;

import java.util.Objects;
import java.util.stream.Stream;

public enum CategoryTypeEnum {
    INCOME(0, "收入"),//
    SPEND(1, "支出");
    private Integer code;
    private String name;

    CategoryTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static CategoryTypeEnum getEnumByType(Integer type) {
        return Stream.of(CategoryTypeEnum.values())//
                .filter(em -> em.getCode().equals(type))//
                .findFirst()//
                .orElseThrow(() -> new RuntimeException("cannot find CategoryTypeEnum by code: " + type));
    }

    public static boolean isCategory(Integer type) {
        return Objects.nonNull(getEnumByType(type));
    }
}
