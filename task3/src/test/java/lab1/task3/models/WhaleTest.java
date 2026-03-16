package lab1.task3.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WhaleTest {

  private Whale whale;

  @BeforeEach
  public void setUp() {
    whale = new Whale("Несчастное существо");
  }

  @Test
  @DisplayName("Проверка начального состояния кита")
  public void testInitialState() {
    assertEquals("Несчастное существо", whale.getName());
    assertFalse(whale.isAwareOfBeingWhale());
    assertFalse(whale.isAwareOfNotBeingWhale());
    assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
    assertTrue(whale.isUnfortunate());
  }

  @Test
  @DisplayName("Проверка установки осознания того, что это кит")
  public void testSetAwareOfBeingWhale() {
    whale.setAwareOfBeingWhale(true);
    assertTrue(whale.isAwareOfBeingWhale());
  }

  @Test
  @DisplayName("Проверка установки осознания того, что это не кит")
  public void testSetAwareOfNotBeingWhale() {
    whale.setAwareOfNotBeingWhale(true);
    assertTrue(whale.isAwareOfNotBeingWhale());
  }

  @Test
  @DisplayName("Проверка изменения позиции")
  public void testChangePosition() {
    whale.setPosition(Whale.Position.NATURAL);
    assertEquals(Whale.Position.NATURAL, whale.getPosition());
    
    whale.setPosition(Whale.Position.UNNATURAL);
    assertEquals(Whale.Position.UNNATURAL, whale.getPosition());
  }

  @Test
  @DisplayName("Проверка наличия малого времени в неестественном положении")
  public void testHasLittleTime() {
    whale.setPosition(Whale.Position.UNNATURAL);
    assertTrue(whale.hasLittleTime());
    
    whale.setPosition(Whale.Position.NATURAL);
    assertFalse(whale.hasLittleTime());
  }

  @Test
  @DisplayName("Проверка установки состояния несчастности")
  public void testSetUnfortunate() {
    whale.setUnfortunate(false);
    assertFalse(whale.isUnfortunate());
    
    whale.setUnfortunate(true);
    assertTrue(whale.isUnfortunate());
  }

  @Test
  @DisplayName("Проверка полного жизненного цикла кита")
  public void testWhaleLifecycle() {
    // Начальное состояние: неестественное положение, не осознает себя
    assertTrue(whale.hasLittleTime());
    assertFalse(whale.isAwareOfBeingWhale());
    assertFalse(whale.isAwareOfNotBeingWhale());
    
    // Кит осознает, что он кит
    whale.setAwareOfBeingWhale(true);
    assertTrue(whale.isAwareOfBeingWhale());
    
    // Затем кит осознает, что он больше не кит
    whale.setAwareOfNotBeingWhale(true);
    assertTrue(whale.isAwareOfNotBeingWhale());
    
    // Оба осознания могут быть истинными одновременно
    assertTrue(whale.isAwareOfBeingWhale());
    assertTrue(whale.isAwareOfNotBeingWhale());
  }
}