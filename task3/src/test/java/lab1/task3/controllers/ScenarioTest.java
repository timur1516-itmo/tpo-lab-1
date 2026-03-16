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
    Command command = new RealizeCommand(whale, "being_whale");
    scenario.addCommand(command);
    
    assertEquals(1, scenario.getCommands().size());
  }

  @Test
  @DisplayName("Проверка выполнения сценария")
  public void testExecuteScenario() {
    scenario.addCommand(new ChangePositionCommand(whale, Whale.Position.UNNATURAL));
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    scenario.addCommand(new RealizeCommand(whale, "not_being_whale"));
    
    scenario.execute();
    
    assertTrue(whale.isAwareOfBeingWhale());
    assertTrue(whale.isAwareOfNotBeingWhale());
    assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
  }

  @Test
  @DisplayName("Проверка очистки сценария")
  public void testClearScenario() {
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    scenario.addCommand(new RealizeCommand(whale, "not_being_whale"));
    
    assertEquals(2, scenario.getCommands().size());
    
    scenario.clear();
    assertEquals(0, scenario.getCommands().size());
  }

  @Test
  @DisplayName("Проверка выполнения пустого сценария")
  public void testExecuteEmptyScenario() {
    scenario.execute(); // Не должно вызывать ошибок
    assertEquals(0, scenario.getCommands().size());
  }

  @Test
  @DisplayName("Проверка полного сценария из текста")
  public void testFullWhaleScenario() {
    // Сценарий: кит в неестественном положении, мало времени,
    // осознает что он кит, затем осознает что он больше не кит
    
    scenario.addCommand(new ChangePositionCommand(whale, Whale.Position.UNNATURAL));
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    scenario.addCommand(new RealizeCommand(whale, "not_being_whale"));
    
    // Начальное состояние
    assertFalse(whale.isAwareOfBeingWhale());
    assertFalse(whale.isAwareOfNotBeingWhale());
    
    // Выполнение сценария
    scenario.execute();
    
    // Проверка конечного состояния
    assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
    assertTrue(whale.hasLittleTime());
    assertTrue(whale.isAwareOfBeingWhale());
    assertTrue(whale.isAwareOfNotBeingWhale());
    
    // Проверка вывода
    String output = outContent.toString();
    assertTrue(output.contains("находится в неестественном положении"));
    assertTrue(output.contains("свыкается с осознанием того, что оно кит"));
    assertTrue(output.contains("свыкается с осознанием того, что оно уже больше не кит"));
  }

  @Test
  @DisplayName("Проверка последовательности выполнения команд")
  public void testCommandExecutionOrder() {
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    scenario.addCommand(new ChangePositionCommand(whale, Whale.Position.NATURAL));
    scenario.addCommand(new RealizeCommand(whale, "not_being_whale"));
    
    scenario.execute();
    
    // Все команды должны быть выполнены в порядке добавления
    assertTrue(whale.isAwareOfBeingWhale());
    assertEquals(Whale.Position.NATURAL, whale.getPosition());
    assertTrue(whale.isAwareOfNotBeingWhale());
  }

  @Test
  @DisplayName("Проверка неизменяемости списка команд")
  public void testCommandListImmutability() {
    Command command = new RealizeCommand(whale, "being_whale");
    scenario.addCommand(command);
    
    // Получаем список команд
    var commands = scenario.getCommands();
    assertEquals(1, commands.size());
    
    // Пытаемся изменить полученный список
    commands.clear();
    
    // Оригинальный список в сценарии не должен измениться
    assertEquals(1, scenario.getCommands().size());
  }

  @Test
  @DisplayName("Проверка множественного выполнения сценария")
  public void testMultipleExecutions() {
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    
    // Первое выполнение
    scenario.execute();
    assertTrue(whale.isAwareOfBeingWhale());
    
    // Сброс состояния кита
    whale.setAwareOfBeingWhale(false);
    assertFalse(whale.isAwareOfBeingWhale());
    
    // Второе выполнение того же сценария
    scenario.execute();
    assertTrue(whale.isAwareOfBeingWhale());
  }
}