package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import entity.base.Updateable;

public class RenderableHolder {
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	private static final RenderableHolder instance = new RenderableHolder();
	
	static {
		loadResource();
	}
	public static void loadResource() {
		
		
	}
	public void update() {
		Collections.sort(entities, comparator);
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i) instanceof Updateable) {
				((Updateable) entities.get(i)).update();
			}
		}
		for (int i1 = entities.size() - 1; i1 >= 0; i1--) {
			if (!entities.get(i1).isDestroyed()) {
				entities.remove(i1);
			}
		}
	}
	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}
	
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			}
		return -1;
		};
	}
	public static RenderableHolder getInstance() {
		return instance;
	}
	public ArrayList<IRenderable> getEntities() {
		return entities;
	}
}
