package cn.mengtianyou.sharding.aspect;

import cn.mengtianyou.sharding.algorithm.CustomDsHintShardingAlgorithm;
import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 分库路由切面
 * @author liups
 * @create 2017/11/29
 */
@Aspect
@Component
@Order(-5)
public class DatabaseAspect {

    //TODO 切面
    @Pointcut("execution(* cn.mengtianyou..dao..*.*(..))")
    private void databasePointcut(){}//定义一个切入点

    /**
     *  这个方法设置了只进行分库路由，不会触发分表路由，若要进行分表路由，需另外实现
     *  具体路由算法实现参考 {@link CustomDsHintShardingAlgorithm} , 需要配置此算法为默认数据库算法
     * @param pjp
     * @throws Throwable
     */
    @Before("databasePointcut()")
    public void baseDatabaseSelect(JoinPoint pjp) throws Throwable{
        //用于多个查询重入，有就加，没有就不加，确保肯定有，但如果有了再加报错
        if(!HintManagerHolder.isUseShardingHint()){
            HintManager instance = HintManager.getInstance();
            instance.setDatabaseShardingValue("");
        }
    }

}
