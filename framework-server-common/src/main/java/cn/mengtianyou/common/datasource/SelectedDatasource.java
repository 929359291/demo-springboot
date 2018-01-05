package cn.mengtianyou.common.datasource;

import java.util.Collections;
import java.util.List;

/**
 * 用于选择数据库的本地线程变量，会传递到别的微服务，由sharding使用
 * @author liups
 * @create 2017/12/4
 */
public class SelectedDatasource {
    private static ThreadLocal<SelectedDatasource> currentCustomDs = new ThreadLocal<>();
    private List<String> requestDatasource;
    private List<String> queryDatasource;

    private SelectedDatasource() {
    }

    public static SelectedDatasource newInstance(String requestDatabase){
        SelectedDatasource selectedDatasource = new SelectedDatasource();
        selectedDatasource.setRequestDatasource(Collections.singletonList(requestDatabase));
        currentCustomDs.set(selectedDatasource);
        return selectedDatasource;
    }

    public static void setInstance(SelectedDatasource selectedDatasource){
        if(selectedDatasource == null){
            return;
        }
        currentCustomDs.set(selectedDatasource);
    }

    public static SelectedDatasource newInstance(List<String> requestDatabases){
        SelectedDatasource selectedDatasource = new SelectedDatasource();
        selectedDatasource.setRequestDatasource(requestDatabases);
        currentCustomDs.set(selectedDatasource);
        return selectedDatasource;
    }

    public static SelectedDatasource getCurrentInstance(){
        SelectedDatasource selectedDatasource = currentCustomDs.get();
        return selectedDatasource;
    }


    public static void clearCurrentInstance(){
        currentCustomDs.remove();
    }

    public static void clearQueryDatasource(){
        currentCustomDs.get().setQueryDatasource(null);
    }

    public List<String> getRequestDatasource() {
        return requestDatasource;
    }

    public void setRequestDatasource(List<String> requestDatasource) {
        this.requestDatasource = requestDatasource;
    }

    public List<String> getQueryDatasource() {
        return queryDatasource;
    }

    public void setQueryDatasource(List<String> queryDatasource) {
        this.queryDatasource = queryDatasource;
    }
}
