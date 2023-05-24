package ma.GestionDesDocuments.state;

import ma.GestionDesDocuments.dto.AuthorDto;

import java.util.List;

public class LogConvertToPage<T> {

    private int pageIndex;
    private int totalPages;
    private int pageSize;
    private List<T> dto;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getLogConverterDtos() {
        return dto ;
    }

    public void setLogConverterDtos(List<T> dto) {
        this.dto = dto;
    }

}
