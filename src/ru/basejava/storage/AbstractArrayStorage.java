package ru.basejava.storage;

import ru.basejava.exception.StorageException;
import ru.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage[(int) index] = r;
    }

    @Override
    public void saveResume(Object index, Resume r) {
        if (size == storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveResume(r, (int) index);
        size++;
    }

    @Override
    public void deleteResume(Object searchKey) {
        deleteResume((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    public int size() {
        return size;
    }

    @Override
    public Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    public boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);


}
