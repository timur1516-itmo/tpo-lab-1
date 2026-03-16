package lab1.task3.commands;

import lab1.task3.models.Whale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealizeCommandTest {

    private Whale whale;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        whale = new Whale("Несчастное существо");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Проверка команды осознания того, что это кит")
    public void testRealizeBeingWhale() {
        RealizeCommand command = new RealizeCommand(whale, RealizeCommand.Realization.BEING_WHALE);
        command.execute();

        assertTrue(whale.isAwareOfBeingWhale());
        assertTrue(outContent.toString().contains("свыкается с осознанием того, что оно кит"));
    }

    @Test
    @DisplayName("Проверка команды осознания того, что это не кит")
    public void testRealizeNotBeingWhale() {
        RealizeCommand command = new RealizeCommand(whale, RealizeCommand.Realization.NOT_BEING_WHALE);
        command.execute();

        assertTrue(whale.isAwareOfNotBeingWhale());
        assertTrue(outContent.toString().contains("свыкается с осознанием того, что оно уже больше не кит"));
    }
}
