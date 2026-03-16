package lab1.task3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SceneObjectTest {

  @Test
  @DisplayName("Проверка создания объекта сцены")
  public void testSceneObjectCreation() {
    SceneObject object = new SceneObject("Тестовый объект");
    assertEquals("Тестовый объект", object.getName());
  }

  @Test
  @DisplayName("Проверка имени объекта")
  public void testGetName() {
    SceneObject object = new SceneObject("Кит");
    assertEquals("Кит", object.getName());
  }

  @Test
  @DisplayName("Проверка создания объекта с пустым именем")
  public void testEmptyName() {
    SceneObject object = new SceneObject("");
    assertEquals("", object.getName());
  }
}