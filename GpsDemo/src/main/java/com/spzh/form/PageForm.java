package com.spzh.form;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 *gps分页form 
 * @author hyq
 *
 */
@JsonRootName("pagination")
public class PageForm {
	private int totalPages;//总页数
	private int currentPage;//当前页号
	private int pageRecords;//页面记录数
	private int totalRecords;//总记录数
	private int previousPage;//前一页
	private int nextPage; //后一页
	private int startRecord;//开始记录
	private int endRecord; //
	
	private boolean hasNextPage;//是否有下一页
	private boolean hasPreviousPage;//是否有上一页
	private String sortParams;//排序参数
	
	
	
	
	public int getEndRecord() {
		return endRecord;
	}
	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageRecords() {
		return pageRecords;
	}
	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public String getSortParams() {
		return sortParams;
	}
	public void setSortParams(String sortParams) {
		this.sortParams = sortParams;
	}
	
	
	
}
