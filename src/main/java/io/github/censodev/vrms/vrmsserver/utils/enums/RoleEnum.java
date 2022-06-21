package io.github.censodev.vrms.vrmsserver.utils.enums;

public enum RoleEnum {
    ROLE_ADMIN(RoleEnum.Const.ROLE_ADMIN),
    ROLE_AGENT(RoleEnum.Const.ROLE_AGENT),
    ROLE_AGENT_CHECKIN(Const.ROLE_AGENT_CHECKIN),
    ROLE_AGENT_TEST(Const.ROLE_AGENT_TEST),
    ROLE_AGENT_PAY(Const.ROLE_AGENT_PAY),
    ROLE_AGENT_INJECT(Const.ROLE_AGENT_INJECT),
    ROLE_AGENT_MONITOR(Const.ROLE_AGENT_MONITOR),
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
        public static final String ROLE_AGENT_CHECKIN = "ROLE_AGENT_CHECKIN";
        public static final String ROLE_AGENT_TEST = "ROLE_AGENT_TEST";
        public static final String ROLE_AGENT_PAY = "ROLE_AGENT_PAY";
        public static final String ROLE_AGENT_INJECT = "ROLE_AGENT_INJECT";
        public static final String ROLE_AGENT_MONITOR = "ROLE_AGENT_MONITOR";
        public static final String ROLE_GUEST = "ROLE_GUEST";
    }
}
