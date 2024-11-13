import java.util.*;
import java.util.function.Predicate;

public class EntityManager<T> {
    private List<T> entities;

    public EntityManager() {
        this.entities = new ArrayList<>();
    }

    public void addEntity(T entity) {
        entities.add(entity);
    }

    public T getEntityById(String id, Predicate<T> predicate) {
        for (T entity : entities) {
            if (predicate.test(entity)) {
                return entity;
            }
        }
        return null;
    }

    public List<T> getAllEntities() {
        return entities;
    }
}
