package net.helpscout.api.model;

import java.util.Date;

/**
 * Tag -
 *
 * @author briandame@gmail.com
 */
public class Tag {

    private Long id;
    private String slug;
    private String tag;
    private Integer count;
    private String color;
    private Date createdAt;
    private Date modifiedAt;

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", slug=" + slug + ", tag=" + tag + ", count=" + count + ", color=" + color
                + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
    }

}
