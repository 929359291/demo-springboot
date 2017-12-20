package cn.mengtianyou.common.aspect;

import cn.mengtianyou.common.constants.HeaderDefinition;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author liups
 * @create 2017/12/19
 */
@Component
public class FeignDsRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        SelectedDatabase currentInstance = SelectedDatabase.getCurrentInstance();
        if(currentInstance != null && !CollectionUtils.isEmpty(currentInstance.getRequestDatabases())){
            //全局，用于一些公共的头
            template.header(HeaderDefinition.DS_ROUTE,
                    StringUtils.collectionToDelimitedString(currentInstance.getRequestDatabases(),HeaderDefinition.DS_ROUTE_SPLIT));
        }
    }


}
