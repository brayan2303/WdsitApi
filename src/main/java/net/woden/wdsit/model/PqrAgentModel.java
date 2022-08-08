package net.woden.wdsit.model;

public class PqrAgentModel {
    private String agent;
    private int assigned;
    private int process;
    private int finished;
    private int scaled;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getScaled() {
        return scaled;
    }

    public void setScaled(int scaled) {
        this.scaled = scaled;
    }
}
