package view;

public enum Colors {
    RED("\u001B[31m"), YELLOW("\u001B[33m"), WHITE("\u001B[0m"), GREEN("\u001B[32m");

    private String code;

    public String getCode() {
        return this.code;
    }

    Colors(String code) {
        this.code = code;
    }
}
