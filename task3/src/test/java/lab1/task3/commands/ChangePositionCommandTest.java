package lab1.task3.commands;

import lab1.task3.models.Whale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePositionCommandTest {

  private Whale whale;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  public void setUp() {
    whale = new Whale("Несчастное существо");
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @Test
  @DisplayName("Проверка команды изменения позиции на естественную")
  public void testChangeToNaturalPosition() {
    ChangePositionCommand command = new ChangePositionCommand(whale, Whale.Position.NATURAL);
    command.execute();
    
    assertEquals(Whale.Position.NATURAL, whale.getPosition());
    assertTrue(outContent.toString().contains("находится в естественном положении"));
  }

  @Test
  @DisplayName("Проверка команды изменения позиции на неестественную")
  public void testChangeToUnnaturalPosition() {
    whale.setPosition(Whale.Position.NATURAL);
    
    ChangePositionCommand command = new ChangePositionCommand(whale, Whale.Position.UNNATURAL);
    command.execute();
    
    assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
    assertTrue(outContent.toString().contains("находится в неестественном положении"));
  }

  @Test
  @DisplayName("Проверка исключения при null ките")
  public void testNullWhale() {
    assertThrows(NullPointerException.class, () -> {
      new ChangePositionCommand(null, Whale.Position.NATURAL);
    });
  }

  @Test
  @DisplayName("Проверка исключения при null позиции")
  public void testNullPosition() {
    assertThrows(NullPointerException.class, () -> {
      new ChangePositionCommand(whale, null);
    });
  }

  @Test
  @DisplayName("Проверка влияния позиции на наличие времени")
  public void testPositionAffectsTime() {
    ChangePositionCommand command1 = new ChangePositionCommand(whale, Whale.Position.UNNATURAL);
    command1.execute();
    assertTrue(whale.hasLittleTime());
    
    ChangePositionCommand command2 = new ChangePositionCommand(whale, Whale.Position.NATURAL);
    command2.execute();
    assertFalse(whale.hasLittleTime());
  }
}