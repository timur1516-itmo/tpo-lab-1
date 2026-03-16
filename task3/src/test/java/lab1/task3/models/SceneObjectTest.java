package lab1.task3.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SceneObjectTest {

    @Test
    @DisplayName("Проверка создания объекта сцены")
    public void testSceneObjectCreation() {
        SceneObject object = new SceneObject("Тестовый объект");
        assertEquals("Тестовый объект", object.getName());
    }
}