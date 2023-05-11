package team.sxcoding.Config;

public enum PermissionLevel {

    SUPER_ADMIN("0"),

    ADMIN("1"),

    USER_MANAGER("2"),

    WAREHOUSE_KEEPER("3"),

    USER("4");



    private final String level;

    PermissionLevel(String level) {
        this.level = level;
    }


    public String getLevel() {
        return level;
    }

}
