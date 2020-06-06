package ru.job4j.design.isp2;

public interface Menu extends Showable {
    void add(Item item);
    void add(Item item, String parentId);
    void choose(String id);
}
