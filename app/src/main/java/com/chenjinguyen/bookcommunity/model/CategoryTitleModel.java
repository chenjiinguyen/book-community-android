package com.chenjinguyen.bookcommunity.model;

import java.io.Serializable;

public class CategoryTitleModel implements Serializable {
    String title;
    String id;

    public CategoryTitleModel(String id, String title) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
