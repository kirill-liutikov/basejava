package ru.basejava.storage;

import ru.basejava.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void updateResume(Object index, Resume r) {
        storage.set((int) index, r);
    }

    @Override
    public void deleteResume(Object index) {
        storage.remove((int)index);
    }

    @Override
    public Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] r = new Resume[size()];
        r = storage.toArray(r);
        return r;
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        storage.add(r);
    }


    @Override
    public boolean isExist(String uuid) {
        for(Resume r : storage) {
            if (r.getUuid().equals(uuid)) return true;
        }
        return false;
        //return storage.stream().anyMatch(s-> s.getUuid().equals(uuid));
    }

    @Override
    protected Object getKeyOrIndex(String uuid) {
        for (int i = 0; i < size() ; i++) {
            if(storage.get(i).getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
