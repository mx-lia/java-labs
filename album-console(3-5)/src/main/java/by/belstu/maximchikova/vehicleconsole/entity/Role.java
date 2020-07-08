package by.belstu.maximchikova.vehicleconsole.entity;

public enum Role {
    CLIENT("client"),
    ADMIN("admin");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
