package com.example.ams_project;


public class SkillModal {
    private String skillName;
    private String skillEffect;
    private String skillTooltip;


    public SkillModal(String skillName, String skillEffect, String skillTooltip) {
        this.skillName = skillName;
        this.skillEffect = skillEffect;
        this.skillTooltip = skillTooltip;
    }


    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillEffect() {
        return skillEffect;
    }

    public void setSkillEffect(String skillEffect) {
        this.skillEffect = skillEffect;
    }

    public String getSkillTooltip() {
        return skillTooltip;
    }

    public void setSkillTooltip(String skillTooltip) {
        this.skillTooltip = skillTooltip;
    }
}
