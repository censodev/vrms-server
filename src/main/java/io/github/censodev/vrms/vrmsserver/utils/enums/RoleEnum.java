package io.github.censodev.vrms.vrmsserver.utils.enums;

public enum RoleEnum {
    ROLE_ADMIN(RoleEnum.Const.ROLE_ADMIN),
    ROLE_AGENT(RoleEnum.Const.ROLE_AGENT),
    ROLE_GUEST(RoleEnum.Const.ROLE_GUEST),
    ;

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static class Const {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_AGENT = "ROLE_AGENT";
        public static final String ROLE_GUEST = "ROLE_GUEST";
    }
}
