package com.dogj.pojo;

import java.io.Serializable;
import java.util.Date;

public class DogjItemParam implements Serializable {
    private Long id;

    /**
     * 商品类目ID
     */
    private Long itemCatId;

    private Date created;

    private Date updated;

    /**
     * 参数数据，格式为json格式
     */
    private String paramData;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemCatId=").append(itemCatId);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", paramData=").append(paramData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}