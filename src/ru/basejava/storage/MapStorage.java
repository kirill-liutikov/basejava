package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (storage.containsKey(uuid)) return uuid;
        return null;
    }

    @Override
    protected void saveResume(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public Resume getResume(Object index) {
        return storage.get(index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> values = storage.values();
        return values.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
