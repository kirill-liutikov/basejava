package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        if (!isExist(r.getUuid())) throw new NotExistStorageException(r.getUuid());
        updateResume(getKeyOrIndex(r.getUuid()), r);
    }

    protected abstract void updateResume(Object index, Resume r);


    @Override
    public void save(Resume r) {
        if (isExist(r.getUuid())) throw new ExistStorageException(r.getUuid());
        saveResume(getKeyOrIndex(r.getUuid()), r);
    }


    @Override
    public Resume get(String uuid) {
        if (!isExist(uuid)) throw new NotExistStorageException(uuid);
        return getResume(getKeyOrIndex(uuid));
    }

    @Override
    public void delete(String uuid) {
        if (!isExist(uuid)) throw new NotExistStorageException(uuid);
        deleteResume(getKeyOrIndex(uuid));
    }

    protected abstract void deleteResume(Object index);

    protected abstract Object getKeyOrIndex(String uuid);

    protected abstract void saveResume(Object index, Resume r);

    public abstract Resume getResume(Object index);

    public abstract boolean isExist(String uuid);

}
