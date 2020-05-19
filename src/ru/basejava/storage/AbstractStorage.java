package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) throw new NotExistStorageException(r.getUuid());
        updateResume(index, r);
    }

    protected abstract void updateResume(int index, Resume r);

    @Override
    public abstract void clear();

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) throw new ExistStorageException(r.getUuid());
        saveResume(index, r);
    }


    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        deleteResume(index);
    }

    protected abstract void deleteResume(int index);


    @Override
    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(int index, Resume r);

    public abstract Resume getResume(int index);
}
