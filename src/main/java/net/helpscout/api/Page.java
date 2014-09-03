package net.helpscout.api;

import java.util.List;

public class Page<T> {
	private Integer page = null;
	private Integer pages = null;
	private Integer count = null;
	private List<T> items = null;
	
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
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", pages=" + pages + ", count=" + count + ", items=" + items + "]";
	}

}
