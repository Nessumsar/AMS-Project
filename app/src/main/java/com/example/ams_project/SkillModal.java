package com.example.ams_project;


public class SkillModal {

    private int id;
    private String name;
    private String tooltip;
    private String effect;

    public SkillModal(int id, String name, String tooltip, String effect) {
        this.id = id;
        this.name = name;
        this.tooltip = tooltip;
        this.effect = effect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return name+"\n\n"
                +tooltip;
    }
}