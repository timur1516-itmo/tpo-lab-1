package lab1.task3.commands;

import lab1.task3.models.Whale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

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
    RealizeCommand command = new RealizeCommand(whale, "being_whale");
    command.execute();
    
    assertTrue(whale.isAwareOfBeingWhale());
    assertTrue(outContent.toString().contains("свыкается с осознанием того, что оно кит"));
  }

  @Test
  @DisplayName("Проверка команды осознания того, что это не кит")
  public void testRealizeNotBeingWhale() {
    RealizeCommand command = new RealizeCommand(whale, "not_being_whale");
    command.execute();
    
    assertTrue(whale.isAwareOfNotBeingWhale());
    assertTrue(outContent.toString().contains("свыкается с осознанием того, что оно уже больше не кит"));
  }

  @Test
  @DisplayName("Проверка исключения при null ките")
  public void testNullWhale() {
    assertThrows(NullPointerException.class, () -> {
      new RealizeCommand(null, "being_whale");
    });
  }

  @Test
  @DisplayName("Проверка исключения при null осознании")
  public void testNullRealization() {
    assertThrows(NullPointerException.class, () -> {
      new RealizeCommand(whale, null);
    });
  }

  @Test
  @DisplayName("Проверка последовательности осознаний")
  public void testSequentialRealizations() {
    RealizeCommand command1 = new RealizeCommand(whale, "being_whale");
    command1.execute();
    assertTrue(whale.isAwareOfBeingWhale());
    assertFalse(whale.isAwareOfNotBeingWhale());
    
    RealizeCommand command2 = new RealizeCommand(whale, "not_being_whale");
    command2.execute();
    assertTrue(whale.isAwareOfBeingWhale());
    assertTrue(whale.isAwareOfNotBeingWhale());
  }
}