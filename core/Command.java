package core;

public class Command {
    private final CommandType type;
    private final String arg1;
    private final String arg2;
    public Command(CommandType type, String arg1, String arg2) {
        this.type = type; this.arg1 = arg1; this.arg2 = arg2;
    }
    public CommandType type() { return type; }
    public String arg1() { return arg1; }
    public String arg2() { return arg2; }
}
