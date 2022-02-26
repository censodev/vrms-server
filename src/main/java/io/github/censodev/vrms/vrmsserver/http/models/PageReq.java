package io.github.censodev.vrms.vrmsserver.http.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {
    private Integer page = 0;
    private Integer size = 10;
    private String sortedBy;
    private SortType sort;

    public enum SortType {
        ASC,
        DESC,
    }

    public Pageable toPageable() {
        if (sort == null || sortedBy == null) {
            return PageRequest.of(page, size);
        }
        var mSort = sort.equals(PageReq.SortType.ASC)
                ? Sort.by(sortedBy).ascending()
                : Sort.by(sortedBy).descending();
        return PageRequest.of(page, size, mSort);
    }
}
