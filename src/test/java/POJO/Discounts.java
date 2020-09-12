package POJO;

import java.util.List;

public class Discounts {
    private String id;
    private String description;
    private String code;
    private List<Object> translateDescription;
    private boolean active;
    private int priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Object> getTranslateDescription() {
        return translateDescription;
    }

    public void setTranslateDescription(List<Object> translateDescription) {
        this.translateDescription = translateDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", translateDescription=" + translateDescription +
                ", active=" + active +
                ", priority=" + priority +
                '}';
    }
}
/*
    "id": "5f5cd950d12716766277513e",
    "description": "fsdfsdf1",
    "code": "sdfsdf1",
    "translateDescription": [],
    "active": true,
    "priority": 3
 */
