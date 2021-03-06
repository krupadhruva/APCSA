public interface CommandHandler {
    boolean execute(String cmd);

    void printHelp();
}
