package lab1.task3.models;

public class Whale extends SceneObject {

  private boolean isAwareOfBeingWhale;
  private boolean isAwareOfNotBeingWhale;
  private Position position;
  private boolean isUnfortunate;

  public enum Position {
    NATURAL,
    UNNATURAL
  }

  public Whale(String name) {
    super(name);
    this.isAwareOfBeingWhale = false;
    this.isAwareOfNotBeingWhale = false;
    this.position = Position.UNNATURAL;
    this.isUnfortunate = true;
  }

  public boolean isAwareOfBeingWhale() {
    return isAwareOfBeingWhale;
  }

  public void setAwareOfBeingWhale(boolean awareOfBeingWhale) {
    isAwareOfBeingWhale = awareOfBeingWhale;
  }

  public boolean isAwareOfNotBeingWhale() {
    return isAwareOfNotBeingWhale;
  }

  public void setAwareOfNotBeingWhale(boolean awareOfNotBeingWhale) {
    isAwareOfNotBeingWhale = awareOfNotBeingWhale;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public boolean isUnfortunate() {
    return isUnfortunate;
  }

  public void setUnfortunate(boolean unfortunate) {
    isUnfortunate = unfortunate;
  }

  public boolean hasLittleTime() {
    return position == Position.UNNATURAL;
  }
}