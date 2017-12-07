package com.jiguang.demo.user.sharding.algorithm;

import io.shardingjdbc.core.api.algorithm.sharding.ShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.hint.HintShardingAlgorithm;

import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author liups
 * @create 2017/11/29
 */
public class CustomDsHintShardingAlgorithm extends AbstractDsCustomShardingAlgorithm implements HintShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ShardingValue shardingValue) {
        return super.doSharding(availableTargetNames,Collections.singletonList(shardingValue));
    }

}
