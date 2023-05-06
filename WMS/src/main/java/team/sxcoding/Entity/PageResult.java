<<<<<<< HEAD
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
                "data" + data ;
    }
=======
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
                "data" + data ;
    }
>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
}