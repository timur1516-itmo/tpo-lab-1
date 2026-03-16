package lab1.task3.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Whale extends SceneObject {

    private boolean isAwareOfBeingWhale;
    private boolean isAwareOfNotBeingWhale;
    private Position position;
    private boolean isUnfortunate;

    public Whale(String name) {
        super(name);
        this.isAwareOfBeingWhale = false;
        this.isAwareOfNotBeingWhale = false;
        this.position = Position.UNNATURAL;
        this.isUnfortunate = true;
    }

    public boolean hasLittleTime() {
        return position == Position.UNNATURAL;
    }

    public enum Position {
        NATURAL,
        UNNATURAL
    }
}
