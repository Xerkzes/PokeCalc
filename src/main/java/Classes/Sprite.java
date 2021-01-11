package Classes;

public class Sprite {
    public int spriteId;
    public String spriteName;
    public String locationOfSprite;

    public Sprite(int spriteId, String spriteName, String locationOfSprite) {
        this.spriteId = spriteId;
        this.spriteName = spriteName;
        this.locationOfSprite = locationOfSprite;
    }

    @Override
    public String toString() {
        return spriteName;
    }
}
