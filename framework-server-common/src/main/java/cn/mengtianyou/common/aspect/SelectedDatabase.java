package cn.mengtianyou.common.aspect;

import java.util.Collections;
import java.util.List;

/**
 * 用于选择数据库的本地线程变量，会传递到别的微服务，由sharding使用
 * @author liups
 * @create 2017/12/4
 */
public class SelectedDatabase {
    private static ThreadLocal<SelectedDatabase> currentCustomDs = new ThreadLocal<>();
    private List<String> requestDatabases;
    private List<String> queryDatabases;

    private SelectedDatabase() {
    }

    public static SelectedDatabase newInstance(String requestDatabase){
        SelectedDatabase selectedDatabase = new SelectedDatabase();
        selectedDatabase.setRequestDatabases(Collections.singletonList(requestDatabase));
        currentCustomDs.set(selectedDatabase);
        return selectedDatabase;
    }

    public static SelectedDatabase newInstance(List<String> requestDatabases){
        SelectedDatabase selectedDatabase = new SelectedDatabase();
        selectedDatabase.setRequestDatabases(requestDatabases);
        currentCustomDs.set(selectedDatabase);
        return selectedDatabase;
    }

    public static SelectedDatabase getCurrentInstance(){
        SelectedDatabase selectedDatabase = currentCustomDs.get();
        return selectedDatabase;
    }


    public static void clearCurrentInstance(){
        currentCustomDs.remove();
    }

    public static void clearQueryDatabases(){
        currentCustomDs.get().setQueryDatabases(null);
    }

    public List<String> getRequestDatabases() {
        return requestDatabases;
    }

    public void setRequestDatabases(List<String> requestDatabases) {
        this.requestDatabases = requestDatabases;
    }

    public List<String> getQueryDatabases() {
        return queryDatabases;
    }

    public void setQueryDatabases(List<String> queryDatabases) {
        this.queryDatabases = queryDatabases;
    }
}
