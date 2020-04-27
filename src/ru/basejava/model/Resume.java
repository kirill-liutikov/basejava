package ru.basejava.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        if (name != null) return uuid + " " + name;
        return uuid;
    }
}
