package com.jiguang.demo.user.sharding.algorithm;

import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.Collection;

/**
 * @author liups
 * @create 2017/11/30
 */
public class CustomDsComplexKeysShardingAlgorithm extends AbstractDsCustomShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        return super.doSharding(availableTargetNames,shardingValues);
    }
}
