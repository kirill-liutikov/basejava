package ru.basejava.storage;

import ru.basejava.model.Resume;

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
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) return entry.getKey();
        }
        return -1;
    }

    @Override
    protected void saveResume(Object index, Resume r) {
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
        Resume[] resumes = new Resume[storage.size()];
        int i = 0;
        for (Resume r : storage.values()) {
            resumes[i] = r;
            i++;
        }
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isExist(String uuid) {
        for (String s : storage.keySet()) {
            if (s.equals(uuid)) return true;
        }
        return false;
        //return storage.keySet().stream().anyMatch(s -> s.equals(uuid));
    }
}
