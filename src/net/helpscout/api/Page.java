package net.helpscout.api;

import java.util.Collection;

public class Page {
    private Integer page = null;
	private Integer pages = null;
	private Integer count = null;
	private Collection<?> items = null;
	
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Collection<?> getItems() {
		return items;
	}

	public void setItems(Collection<?> items) {
		this.items = items;
	}	
}
