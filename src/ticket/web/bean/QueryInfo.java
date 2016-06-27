package ticket.web.bean;


public class QueryInfo {

	private int currentPage = 1;
	private int pageSize = 5;
	private int startIndex;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		this.startIndex = (this.currentPage - 1) * this.pageSize ;
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	@Override
	public String toString() {
		return "QueryInfo [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", startIndex=" + startIndex + "]";
	}
	
	
}
