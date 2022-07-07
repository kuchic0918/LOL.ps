package com.yg_ac.dto;

public class ChampPatchHistoryDto {
	
    private String name;
    private String version;
    private String skillKey;
    private String skillName;
    private String content;
    private String image;

    public ChampPatchHistoryDto(String name, String version, String skillKey, String skillName, String content, String image) {
        this.name = name;
        this.version = version;
        this.skillKey = skillKey;
        this.skillName = skillName;
        this.content = content;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSkillKey() {
        return skillKey;
    }

    public void setSkillKey(String skillKey) {
        this.skillKey = skillKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
	
}
