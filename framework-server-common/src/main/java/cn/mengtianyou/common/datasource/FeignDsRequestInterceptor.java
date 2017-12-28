package cn.mengtianyou.common.datasource;

import cn.mengtianyou.common.constants.HeaderDefinition;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Feign客户端调用服务会将数据库连接池信息放在头信息里面传递给下个微服务
 * @author liups
 * @create 2017/12/19
 */
@Component
public class FeignDsRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        SelectedDatasource currentInstance = SelectedDatasource.getCurrentInstance();
        if(currentInstance != null && !CollectionUtils.isEmpty(currentInstance.getRequestDatasource())){
            //全局，用于一些公共的头
            template.header(HeaderDefinition.DS_ROUTE,
                    StringUtils.collectionToDelimitedString(currentInstance.getRequestDatasource(),HeaderDefinition.DS_ROUTE_SPLIT));
        }
    }


}
