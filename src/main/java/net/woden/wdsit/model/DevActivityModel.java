package net.woden.wdsit.model;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class DevActivityModel implements Serializable {
    
    private int taskId;
    private int activityId;
    private String name;
    private byte[]file;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
