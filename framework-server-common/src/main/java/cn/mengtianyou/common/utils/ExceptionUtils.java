package cn.mengtianyou.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liups
 * @create 2017/12/18
 */
public class ExceptionUtils {

    /**
     *
     * @param throwable
     * @return
     */
    public static Throwable getCause(Throwable throwable){
        if (throwable == null) {
            return null;
        }
        Throwable cause = throwable.getCause();
        if (cause == null) {
            return throwable;
        }
        return cause;
    }

    /**
     * 获取异常里面的所有包含的异常
     * @param throwable
     * @return
     */
    public static List getThrowableList(Throwable throwable) {
        List list = new ArrayList();
        while (throwable != null && list.contains(throwable) == false) {
            list.add(throwable);
            throwable = getCause(throwable);
        }
        return list;
    }

    /**
     * 获取异常链里面第一个匹配的异常种类
     * @param throwable
     * @return 第一个匹配的异常种类,没有则返回空
     */
    public static<T extends Throwable> T getCauseByType(Throwable throwable, Class<T> clazz) {
        if(clazz == null){
            return null;
        }
        Set<Throwable> set = new HashSet<>();
        while (throwable != null && set.contains(throwable) == false) {
            if(clazz.isInstance(throwable)){
                return (T) throwable;
            }
            set.add(throwable);
            throwable = getCause(throwable);
        }
        return null;
    }

}
