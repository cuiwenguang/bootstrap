package com.cwg.bootstrap.system.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Resource {
    private Integer resourceId;
    
    @NotBlank(message = "资源名称不能为空")
    @Length(min = 2, max = 20, message = "名称长度在{min}~{max}之间" )
    private String resourceName;

    @NotBlank(message = "资源类型不能为空")
    @Pattern(regexp = "F|M|C", message = "资源类型数据非法")
    private String resourceType;

    private String resourceCode;

    private String resourcePath;

    private Integer resourceParent;
    
    private List<Resource> children;

    public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public Integer getResourceParent() {
        return resourceParent;
    }

    public void setResourceParent(Integer resourceParent) {
        this.resourceParent = resourceParent;
    }
}