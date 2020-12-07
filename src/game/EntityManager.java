package game;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EntityManager {
    //Both players and projectiles
    private List<Entity> entities = new ArrayList<>();
    private HashSet<Entity> collidedEntities = new HashSet<>();
    //Only players
    private HashMap<Integer, Player> players = new HashMap<>();

    /**
     * Adds entity to the entityManager. This should only be called from the entity itself
     *
     * @param entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Sets the acceleration for all players based on which keys are pressed.
     * All players have specific key schemas.
     *
     * @param keys
     */
    public void movePlayers(HashMap<String, Boolean> keys) {
        players.forEach((number, player) -> {
            player.move(keys);
        });
    }

    /**
     * Gets player based on number.
     * Usually 1 and 2
     *
     * @param number
     * @return
     */
    public Player getPlayer(int number) {
        return players.get(number);
    }

    /**
     * Updates all entities based on deltaTime.
     * Also checks for collision between ALL entities. (players and projectiles currently)
     *
     * @param deltaTime
     */
    public void updateAll(double deltaTime) {
        Iterator<Entity> ens1 = entities.iterator();
        while (ens1.hasNext()) {
            Entity en1 = ens1.next();
            en1.update(deltaTime);

            Iterator<Entity> ens2 = entities.iterator();
            while (ens2.hasNext()) {
                Entity en2 = ens2.next();
                if (en1 != en2) {
                    if (collision(en1, en2)) {
                        collidedEntities.add(en2);
                        collidedEntities.add(en1);
                    }
                }
            }

        }

        collidedEntities.forEach((entity -> entity.onCollision()));
        if (collidedEntities.size() > 0) collidedEntities = new HashSet<>();
    }

    /**
     * Draws all entities
     */
    public void drawAll() {
        entities.forEach((entity -> entity.draw()));
    }

    /**
     * Adds the player
     *
     * @param player
     */
    public void addPlayer(Player player) {
//        entities.add(player);
        players.put(player.getNumber(), player);
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

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void onResize(double width, double height){
        entities.forEach(entity -> {
            entity.setScale(width/1424);
        });
    }


}
