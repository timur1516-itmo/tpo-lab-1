package lab1.task3.commands;

import lab1.task3.models.Whale;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RealizeCommand implements Command {

    private final Whale whale;
    private final Realization realization;

    @Override
    public void execute() {
        switch (realization) {
            case BEING_WHALE -> {
                whale.setAwareOfBeingWhale(true);
                System.out.println(whale.getName() + " свыкается с осознанием того, что оно кит.");
            }
            case NOT_BEING_WHALE -> {
                whale.setAwareOfNotBeingWhale(true);
                System.out.println(whale.getName() + " свыкается с осознанием того, что оно уже больше не кит.");
            }
        }
    }

    public enum Realization {
        BEING_WHALE,
        NOT_BEING_WHALE
    }
}
