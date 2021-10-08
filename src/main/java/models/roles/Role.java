package models.roles;

public class Role {

    private RoleType roleType;
    private String accessType;

    public Role(RoleType roleType, String accessType) {
        this.roleType = roleType;
        this.accessType = accessType;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
