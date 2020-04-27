package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0,size,null);
        size = 0;
    }

    public void save(Resume r) {
        //каой размер size
        if (size == storage.length) {
            System.out.println("Storage full, cannot be saved");
            return;}
        if (!contains(r)) {
            storage[size] = r;
            size++;
        } else System.out.println("Resume "+ r + " already exists");
    }

    public void update(Resume r) {
        if (contains(r)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                }
            }
        } else System.out.println("Resume " + r + " does`t exists");
    }

    public Resume get(String uuid) {
        if (contains(uuid)) {
            for (int i = 0; i < size(); i++) {
                if (storage[i].getUuid().equals(uuid)) return storage[i];
            }
        }
        System.out.println("Resume does`t exists");
        return null;
    }

    public void delete(String uuid) {
        if (contains(uuid)) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                size--;
                break;
            }
        }}
        else {
            System.out.println("ru.basejava.model.Resume does`t exists");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size()];
        if (size() >= 0) System.arraycopy(storage, 0, allResumes, 0, size());
        return allResumes;
    }

    public int size() {
        return size;
    }

     private boolean contains(Resume r){
        return Arrays.asList(getAll()).contains(r);
    }

     private boolean contains(String uuid) {
        return Arrays.stream(getAll()).anyMatch(x ->x.getUuid().equals(uuid));
    }
}
