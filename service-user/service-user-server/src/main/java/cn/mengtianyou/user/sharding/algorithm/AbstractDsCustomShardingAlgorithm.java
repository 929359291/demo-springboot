package cn.mengtianyou.user.sharding.algorithm;

import cn.mengtianyou.common.aspect.SelectedDatabase;
import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.routing.strategy.ShardingAlgorithm;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用于指定数据库
 * @author liups
 * @create 2017/12/1
 */
public abstract class AbstractDsCustomShardingAlgorithm implements ShardingAlgorithm {

    protected Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        //若有指定数据库，则使用指定数据库，若没指定，则用默认数据库，若没有默认数据库，则使用全部数据库
        SelectedDatabase currentInstance = SelectedDatabase.getCurrentInstance();
        if(currentInstance == null || (CollectionUtils.isEmpty(currentInstance.getRequestDatabases())
                && CollectionUtils.isEmpty(currentInstance.getQueryDatabases()))){
            //TODO 支持正则或指定默认
            List<String> result = new ArrayList();
            for(String each : availableTargetNames){
                if(!each.endsWith("base")){
                    result.add(each);
                }
            }
            return result;
        }else if(!CollectionUtils.isEmpty(currentInstance.getQueryDatabases())){
            return currentInstance.getQueryDatabases();
        }else if(!CollectionUtils.isEmpty(currentInstance.getRequestDatabases())){
            return currentInstance.getRequestDatabases();
        }else{
            throw new IllegalArgumentException("所选数据库中包含无效数据库");
        }
    }

}
