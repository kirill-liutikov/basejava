package ru.basejava.storage;

import ru.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
        } else System.out.println("Resume " + r + " does`t exists");
    }

    @Override
    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage full, cannot be saved");
            return;
        }

        int index = getIndex(r.getUuid());

        if (index >= 0) {
            System.out.println("Resume " + r + " already exists");
            return;
        }
        saveResume(r, index);
    }

    protected abstract void  saveResume(Resume r, int index);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        }
        System.out.println("Resume does`t exists");
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

    protected abstract int getIndex(String uuid);
}
