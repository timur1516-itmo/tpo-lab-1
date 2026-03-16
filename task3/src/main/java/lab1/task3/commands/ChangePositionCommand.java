package lab1.task3.commands;

import lab1.task3.models.Whale;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangePositionCommand implements Command {

    private final Whale whale;
    private final Whale.Position newPosition;

    @Override
    public void execute() {
        whale.setPosition(newPosition);
        switch (newPosition) {
            case NATURAL -> System.out.println(whale.getName() + " находится в естественном положении.");
            case UNNATURAL -> System.out.println(whale.getName() + " находится в неестественном положении.");
        }
    }
}