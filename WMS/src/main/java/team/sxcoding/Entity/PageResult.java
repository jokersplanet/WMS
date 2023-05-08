package team.sxcoding.Entity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long pages; // 总页数

    private List<T> data; // 当前页数据


    @Override
    public String toString() {
        return "pages" + pages +
                "data" + data;
    }
}