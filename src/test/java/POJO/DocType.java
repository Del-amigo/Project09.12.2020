package POJO;

import java.util.ArrayList;
import java.util.List;

public class DocType {
    private String id;
    private String name;
    private String description;
    private boolean active;
    private boolean required;
    private String schoolId;
    private List<String> attachmentStages;


    public void setAttachmentStages(List<String> attachmentStages) {
        this.attachmentStages = attachmentStages;
    }
    public List<String> getAttachmentStages() {
        return attachmentStages;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "DocType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", required=" + required +
                ", schoolId='" + schoolId + '\'' +
                ", attachmentStages=" + attachmentStages +
                '}';
    }
}
/*
{
    "id": null,
    "name": "Del1",
    "description": "sdfsdfs",
    "attachmentStages": [
        "CERTIFICATE"
    ],
    "active": true,
    "required": true,
    "useCamera": false,
    "translateName": [],
    "schoolId": "5c5aa8551ad17423a4f6ef1d"
}
 */
