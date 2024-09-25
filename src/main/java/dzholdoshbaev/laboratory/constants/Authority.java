package dzholdoshbaev.laboratory.constants;

public enum Authority {

    USER("USER");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
