package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private List<Entity> entitys = new ArrayList<>();

    public void addProjectile(Projectile projectile) {
        entitys.add(projectile);
    }

    public void updateAll(double deltaTime) {
        Iterator<Entity> ens1 = entitys.iterator();
        Iterator<Entity> ens2 = entitys.iterator();
        while (ens1.hasNext()) {
            Entity en1 = ens1.next();
            en1.update(deltaTime);

            if (en1 instanceof Player) {
                while (ens2.hasNext()) {
                    Entity en2 = ens2.next();
                    if (en1 != en2) {
                        if (collition(en1, en2)) {
                            System.out.println(en1 +" "+ en2);
                        }
                    }
                }
            }
        }
//        for (Entity entity : entitys) {
//            entity.update(deltaTime);
//            if(entity instanceof Player){
//
//            }
//            for (Entity entity1: entitys) {
//                if(entity == entity1){
//
//                } else {
//                    if(collition(entity,entity1)){
//
//                    }
//                }
//            }
//        }
    }

    public void drawAll() {
        for (Entity entity : entitys) {
            entity.draw();
        }
    }

    public void addPlayer(Player player) {
        entitys.add(player);
    }

    public boolean collition(Entity en1, Entity en2) {
        if (Math.pow(en1.getX() - en2.getX(), 2) + Math.pow(en1.getY() - en2.getY(), 2) <= Math.pow(en1.getSize(), 2)) {
            return true;
        }

        return false;
    }


}
