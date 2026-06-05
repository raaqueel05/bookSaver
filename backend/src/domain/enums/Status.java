package domain.enums;

public enum Status {
    READ("Llegit"),
    PENDING("Pendent"),
    READING("Llegint"),
    PAUSED("Pausat");

    private final String label;

    Status(String label) { this.label = label; }

    public String getLabel() { return label; }
}
