package com.yg_ac.dto;

public class ChampBasicSkillDto {
    private String name;
    private String skillName;
    private String skillKey;
    private String function;
    private String image;


    public ChampBasicSkillDto(String name, String skillName, String skillKey, String function, String image) {
        this.name = name;
        this.skillName = skillName;
        this.skillKey = skillKey;
        this.function = function;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillKey() {
        return skillKey;
    }

    public void setSkillKey(String skillKey) {
        this.skillKey = skillKey;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
