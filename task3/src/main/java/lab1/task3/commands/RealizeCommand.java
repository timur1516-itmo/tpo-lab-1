package lab1.task3.commands;

import lab1.task3.models.Whale;

import java.util.Objects;

public class RealizeCommand implements Command {

  private final Whale whale;
  private final String realization;

  public RealizeCommand(Whale whale, String realization) {
    this.whale = Objects.requireNonNull(whale, "Кит не может быть null");
    this.realization = Objects.requireNonNull(realization, "Осознание не может быть null");
  }

  @Override
  public void execute() {
    if (realization.equals("being_whale")) {
      whale.setAwareOfBeingWhale(true);
      System.out.println(whale.getName() + " свыкается с осознанием того, что оно кит.");
    } else if (realization.equals("not_being_whale")) {
      whale.setAwareOfNotBeingWhale(true);
      System.out.println(whale.getName() + " свыкается с осознанием того, что оно уже больше не кит.");
    }
  }
}