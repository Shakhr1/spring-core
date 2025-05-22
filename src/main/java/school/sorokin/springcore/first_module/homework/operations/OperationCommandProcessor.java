package school.sorokin.springcore.first_module.homework.operations;

public interface OperationCommandProcessor {
    void processOperation();
    ConsoleOperationType getConsoleOperationType();
}
