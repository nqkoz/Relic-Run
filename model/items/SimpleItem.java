package model.items;

import model.Item;
import model.UseContext;

public class SimpleItem extends Item {
    public SimpleItem(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean use(UseContext ctx) {
        return false;
    }
}