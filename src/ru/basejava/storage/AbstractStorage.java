package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        updateResume(checkNotExist(r.getUuid()), r);
    }

    protected abstract void updateResume(Object searchKey, Resume r);


    @Override
    public void save(Resume r) {
        saveResume(checkExist(r.getUuid()), r);
    }


    @Override
    public Resume get(String uuid) {
        return getResume(checkNotExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(checkNotExist(uuid));
    }

    public Object checkNotExist(String uuid) {
        if (!isExist(uuid)) throw new NotExistStorageException(uuid);
        return getSearchKey(uuid);
    }

    public Object checkExist(String uuid) {
        if (isExist(uuid)) throw new ExistStorageException(uuid);
        return getSearchKey(uuid);
    }

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean isExist(String uuid);

}
