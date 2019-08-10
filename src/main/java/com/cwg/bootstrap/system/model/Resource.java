package com.cwg.bootstrap.system.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Resource {
    private Long resourceId;
    
    @NotBlank(message = "资源名称不能为空")
    @Length(min = 2, max = 20, message = "名称长度在{min}~{max}之间" )
    private String resourceName;

    @NotBlank(message = "资源类型不能为空")
    @Pattern(regexp = "F|M|C", message = "资源类型数据非法")
    private String resourceType;

    private String resourceCode;

    private String resourcePath;

    private Long resourceParent;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
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

    public Long getResourceParent() {
        return resourceParent;
    }

    public void setResourceParent(Long resourceParent) {
        this.resourceParent = resourceParent;
    }
}