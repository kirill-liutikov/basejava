package ru.basejava.storage;

import ru.basejava.exception.ExistStorageException;
import ru.basejava.exception.NotExistStorageException;
import ru.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = checkExist(r.getUuid());
        updateResume(searchKey, r);
    }

    protected abstract void updateResume(Object searchKey, Resume r);


    @Override
    public void save(Resume r) {
        Object searchKey = checkNotExist(r.getUuid());
        saveResume(searchKey, r);
    }


    @Override
    public Resume get(String uuid) {
        Object searchKey = checkExist(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = checkExist(uuid);
        deleteResume(searchKey);
    }

    public Object checkNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) throw new ExistStorageException(uuid);
        return searchKey;
    }

    public Object checkExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        return searchKey;
    }

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

}
