package com.example.th2_ltmb;

public class StoryEntity {
    private String title;
    private String desc;

    public StoryEntity(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StoryEntity that = (StoryEntity) obj;
        return title.equals(that.title);
    }
}
