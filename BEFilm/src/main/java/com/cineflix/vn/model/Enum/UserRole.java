package com.cineflix.vn.model.Enum;

public enum UserRole {
    ADMIN("ADMIN", "Admin", "Quản trị viên - Có quyền cao nhất"),
    MODERATOR("MODERATOR", "Moderator", "Người kiểm duyệt - Quản lý nội dung"),
    USER("USER", "User", "Người dùng thông thường"),
    GUEST("GUEST", "Guest", "Khách vãng lai - Quyền truy cập hạn chế");

    private final String idRole;
    private final String roleName;
    private final String description;

    UserRole(String idRole, String roleName, String description) {
        this.idRole = idRole;
        this.roleName = roleName;
        this.description = description;
    }

    public String getIdRole() {
        return idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }
}
