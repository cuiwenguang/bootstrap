package com.cwg.bootstrap.merits.model;

import java.util.List;

public class Standard {
    private Integer standardId;

    private String standardName;

    private String standardDesc;

    private Integer parentId;

    private String standardType;
    
    private List<Standard> children;

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    public String getStandardDesc() {
        return standardDesc;
    }

    public void setStandardDesc(String standardDesc) {
        this.standardDesc = standardDesc == null ? null : standardDesc.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType == null ? null : standardType.trim();
    }

	public List<Standard> getChildren() {
		return children;
	}

	public void setChildren(List<Standard> children) {
		this.children = children;
	}
}