package models.roles;

public enum RoleType {
    ADMIN,
    MODERATOR,
    USER,
    NONE;

    public static RoleType find(String name) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.toString().equalsIgnoreCase(name)) {
                return roleType;
            }
        }
        return RoleType.NONE;
    }
    public String convertToString() {
        for (RoleType currentRoleType : RoleType.values()) {
            return currentRoleType.toString();
        }
        return null;
    }




}
