package metier.all_purpose;

import java.util.HashMap;

public class RepoGlobalObjects {

	private static RepoGlobalObjects instance;

	private HashMap<String, Object> globalObjectsMap;

	private RepoGlobalObjects() {
		this.globalObjectsMap = new HashMap<String, Object>();
	}

	public static RepoGlobalObjects getInstance() {
		if (instance == null) {
			instance = new RepoGlobalObjects();
		}
		return instance;
	}

	public void put(String key, Object obj) {
		globalObjectsMap.put(key, obj);
	}

	public Object get(String key) {
		return globalObjectsMap.containsKey(key) ? globalObjectsMap.get(key)
				: null;
	}
}
