package lab1.task3;

import lab1.task3.commands.ChangePositionCommand;
import lab1.task3.commands.RealizeCommand;
import lab1.task3.controllers.Scenario;
import lab1.task3.models.Whale;

public class App {
  public static void main(String[] args) {
    System.out.println("Демонстрация доменной модели для текста о ките:");
    System.out.println("================================================");
    System.out.println();
    System.out.println("Исходный текст:");
    System.out.println("\"И поскольку это далеко не самое естественное положение для кита,");
    System.out.println("то у этого несчастного существа было очень мало времени на то,");
    System.out.println("чтобы успеть свыкнуться с осознанием того, что оно кит,");
    System.out.println("перед тем, как ему пришлось свыкнуться с осознанием того,");
    System.out.println("что оно уже больше не кит.\"");
    System.out.println();
    System.out.println("Моделирование сценария:");
    System.out.println("========================");
    
    // Создаем кита
    Whale whale = new Whale("Несчастное существо");
    
    // Создаем сценарий
    Scenario scenario = new Scenario();
    
    // Добавляем команды в сценарий
    scenario.addCommand(new ChangePositionCommand(whale, Whale.Position.UNNATURAL));
    scenario.addCommand(new RealizeCommand(whale, "being_whale"));
    scenario.addCommand(new RealizeCommand(whale, "not_being_whale"));
    
    // Выполняем сценарий
    scenario.execute();
    
    // Выводим итоговое состояние
    System.out.println();
    System.out.println("Итоговое состояние кита:");
    System.out.println("========================");
    System.out.println("Имя: " + whale.getName());
    System.out.println("Положение: " + (whale.getPosition() == Whale.Position.NATURAL ? 
      "естественное" : "неестественное"));
    System.out.println("Мало времени: " + (whale.hasLittleTime() ? "да" : "нет"));
    System.out.println("Осознает, что кит: " + (whale.isAwareOfBeingWhale() ? "да" : "нет"));
    System.out.println("Осознает, что не кит: " + (whale.isAwareOfNotBeingWhale() ? "да" : "нет"));
    System.out.println("Несчастное существо: " + (whale.isUnfortunate() ? "да" : "нет"));
  }
}