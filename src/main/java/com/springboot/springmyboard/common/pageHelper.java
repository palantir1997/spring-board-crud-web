package com.springboot.springmyboard.common;


public class pageHelper {
	private String url;
	private String paramStr;
	
	private int totalRecord;

	private int totalPage = 0;
	private int pageSize = 10;

	private int requestPage = 0;
	private int startPage = 0;
	private int endPage = 0;
	private boolean prev = false;
	private boolean next = false;
	
	public pageHelper(int totalRecord, int requestPage, String url, String paramStr) {
		super();
		
		this.totalRecord = totalRecord;
		this.requestPage = requestPage;
		this.url = url;
		this.paramStr = paramStr;
		
		this.makePagination();
	}
	
	private void makePagination() {
		totalPage = (int) totalRecord / pageSize;

		if (totalRecord % pageSize != 0) {
			totalPage++;
		}

		if (totalPage > 0) {
			startPage = ((int)((requestPage - 1) / 10)) * 10 + 1;
			endPage = startPage + 10 - 1;
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			if(startPage > 10) {
				prev = true;
			}
			
			if(totalPage > endPage) {
				next = true;
			}
		}
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public String getUrl() {
		return url;
	}

	public String getParamStr() {
		return paramStr;
	}
}
