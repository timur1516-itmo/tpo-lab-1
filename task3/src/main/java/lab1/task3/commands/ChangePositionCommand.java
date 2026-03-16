package lab1.task3.commands;

import lab1.task3.models.Whale;

import java.util.Objects;

public class ChangePositionCommand implements Command {

  private final Whale whale;
  private final Whale.Position newPosition;

  public ChangePositionCommand(Whale whale, Whale.Position newPosition) {
    this.whale = Objects.requireNonNull(whale, "Кит не может быть null");
    this.newPosition = Objects.requireNonNull(newPosition, "Позиция не может быть null");
  }

  @Override
  public void execute() {
    whale.setPosition(newPosition);
    if (newPosition == Whale.Position.UNNATURAL) {
      System.out.println(whale.getName() + " находится в неестественном положении.");
    } else {
      System.out.println(whale.getName() + " находится в естественном положении.");
    }
  }
}