package game;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class EntityManager implements Serializable {
    private List<Entity> entities = new ArrayList<>();
    private HashSet<Entity> entitiesToRemove = new HashSet<>();
    double WIDTH, HEIGHT;

    /**
     * Adds entity to the entityManager. This should only be called from the entity itself
     *
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Sets the border of the entity to the bounds of the game
     * Since the entity does not know of the game size, this is an easy way to let it know.
     * @param entity
     */
    public void setBorderAsOuterBounds(Entity entity){
        entity.setBorder(0, 0, WIDTH, HEIGHT);
    }


    /**
     * Updates all entities based on deltaTime.
     * Also checks for collision between ALL entities. (players and projectiles currently)
     *
     * @param deltaTime
     */
    public void updateAll(double deltaTime) {
        entities.forEach(en1 -> {
            en1.update(deltaTime);
            entities.forEach(en2 -> {
                if (en1 != en2) {
                    if (collision(en1, en2)) {
                        en1.onCollision(en2);
                        en2.onCollision(en1);
                    }
                }
            });
        });


        entitiesToRemove.forEach(entity -> entities.remove(entity));
        if (entitiesToRemove.size() > 0) entitiesToRemove = new HashSet<>();
    }

    /**
     * Draws all entities
     */
    public void drawAll() {
        entities.forEach((entity -> entity.draw()));
    }

    /**
     * Checks the collision between two entities
     *
     * @param en1 Entity, order doesn't matter
     * @param en2 Entity, order doesn't matter
     * @return true if the distance is less than the biggest Entity size
     */
    public boolean collision(Entity en1, Entity en2) {
        double size;
        if (en1.getSize() > en2.getSize()) size = en1.getSize();
        else size = en2.getSize();
        double distance = Math.pow(en1.getX() - en2.getX(), 2) + Math.pow(en1.getY() - en2.getY(), 2);
        if (Math.sqrt(distance) <= size / 2) {
            return true;
        }
        return false;
    }

    /**
     * If the entities where to be removed at the direct moment, it could break loops. Therefore it puts it in a remove list which is executed after all list looping is done.
     *
     * @param entity
     */
    public void removeEntity(Entity entity) {
        entitiesToRemove.add(entity);
    }

    /**
     * Scales Entity
     * @param width
     * @param height
     */
    public void onResize(double width, double height) {
        Entity.setScale(width / 1424);
        WIDTH = width;
        HEIGHT = height;
    }

    @Override
    public String toString() {
        return "EntityManager{" +
                "entities=" + entities +
                '}';
    }
}
