package com.example.ams_project;

public class OfficerModal {

    private String name;
    private String type;
    private SkillModal skillModal1;
    private SkillModal skillModal2;
    private SkillModal skillModal3;

    public OfficerModal() {}

    public OfficerModal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public OfficerModal(String name, String type, SkillModal skillModal1, SkillModal skillModal2, SkillModal skillModal3) {
        this.name = name;
        this.type = type;
        this.skillModal1 = skillModal1;
        this.skillModal2 = skillModal2;
        this.skillModal3 = skillModal3;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SkillModal getSkillModal1() {
        return skillModal1;
    }

    public void setSkillModal1(SkillModal skillModal1) {
        this.skillModal1 = skillModal1;
    }

    public SkillModal getSkillModal2() {
        return skillModal2;
    }

    public void setSkillModal2(SkillModal skillModal2) {
        this.skillModal2 = skillModal2;
    }

    public SkillModal getSkillModal3() {
        return skillModal3;
    }

    public void setSkillModal3(SkillModal skillModal3) {
        this.skillModal3 = skillModal3;
    }

    @Override
    public String toString() {
        return "OfficerModal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", skillModal1=" + skillModal1 +
                ", skillModal2=" + skillModal2 +
                ", skillModal3=" + skillModal3 +
                '}';
    }
}
