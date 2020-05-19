package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> storage = new ArrayList<Resume>();

    @Override
    public void updateResume(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    public void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    public Resume getResume(int index) {
        return storage.get(index);
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
    protected void saveResume(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected int getIndex(String uuid) {
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
