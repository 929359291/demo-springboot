package cn.mengtianyou.sharding.algorithm;

import cn.mengtianyou.common.datasource.SelectedDatasource;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.routing.strategy.ShardingAlgorithm;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 用于指定数据库
 * @author liups
 * @create 2017/12/1
 */
public abstract class AbstractDsCustomShardingAlgorithm implements ShardingAlgorithm {

    protected Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        //若有指定数据库，则使用指定数据库，若没指定，则用默认数据库，若没有默认数据库，则使用全部数据库
        SelectedDatasource currentInstance = SelectedDatasource.getCurrentInstance();
        if(currentInstance == null || (CollectionUtils.isEmpty(currentInstance.getRequestDatasource())
                && CollectionUtils.isEmpty(currentInstance.getQueryDatasource()))){
            //没有指定则返回所有有效的
            return availableTargetNames;
        }else if(!CollectionUtils.isEmpty(currentInstance.getQueryDatasource())){
            //TODO 根据路由信息选出数据库
            return currentInstance.getQueryDatasource();
        }else if(!CollectionUtils.isEmpty(currentInstance.getRequestDatasource())){
            //TODO 根据路由信息选出数据库
            return currentInstance.getRequestDatasource();
        }else{
            throw new IllegalArgumentException("所选数据库中包含无效数据库");
        }
    }

}
