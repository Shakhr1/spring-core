package school.sorokin.springcore.firstHomework.operations;

public interface OperationCommandProcessor {
    void processOperation();
    ConsoleOperationType getConsoleOperationType();
}
