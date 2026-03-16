package lab1.task3.controllers;

import lab1.task3.commands.ChangePositionCommand;
import lab1.task3.commands.Command;
import lab1.task3.commands.RealizeCommand;
import lab1.task3.models.Whale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTest {

    private Scenario scenario;
    private Whale whale;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        scenario = new Scenario();
        whale = new Whale("Несчастное существо");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Проверка добавления команд в сценарий")
    public void testAddCommand() {
        Command command = new RealizeCommand(whale, RealizeCommand.Realization.BEING_WHALE);
        scenario.addCommand(command);

        assertEquals(1, scenario.getCommandsCount());
    }

    @Test
    @DisplayName("Проверка очистки сценария")
    public void testClearScenario() {
        scenario.addCommand(new RealizeCommand(whale, RealizeCommand.Realization.BEING_WHALE));
        scenario.addCommand(new RealizeCommand(whale, RealizeCommand.Realization.NOT_BEING_WHALE));

        assertEquals(2, scenario.getCommandsCount());

        scenario.clear();
        assertEquals(0, scenario.getCommandsCount());
    }

    @Test
    @DisplayName("Проверка выполнения пустого сценария")
    public void testExecuteEmptyScenario() {
        scenario.execute();
        assertEquals(0, scenario.getCommandsCount());
    }

    @Test
    @DisplayName("Проверка полного сценария из текста")
    public void testFullWhaleScenario() {
        scenario.addCommand(new ChangePositionCommand(whale, Whale.Position.UNNATURAL));
        scenario.addCommand(new RealizeCommand(whale, RealizeCommand.Realization.BEING_WHALE));
        scenario.addCommand(new RealizeCommand(whale, RealizeCommand.Realization.NOT_BEING_WHALE));

        assertFalse(whale.isAwareOfBeingWhale());
        assertFalse(whale.isAwareOfNotBeingWhale());

        scenario.execute();

        assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
        assertTrue(whale.hasLittleTime());
        assertTrue(whale.isAwareOfBeingWhale());
        assertTrue(whale.isAwareOfNotBeingWhale());

        String output = outContent.toString();
        assertTrue(output.contains("находится в неестественном положении"));
        assertTrue(output.contains("свыкается с осознанием того, что оно кит"));
        assertTrue(output.contains("свыкается с осознанием того, что оно уже больше не кит"));
    }
}
