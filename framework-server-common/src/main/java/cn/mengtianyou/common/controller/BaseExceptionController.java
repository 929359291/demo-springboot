package cn.mengtianyou.common.controller;

import cn.mengtianyou.common.messages.ErrorMessage;
import cn.mengtianyou.common.constants.ApplicationConstant;
import cn.mengtianyou.common.constants.CustomHttpStatus;
import cn.mengtianyou.common.messages.AppMessageServiceType;
import cn.mengtianyou.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liups
 * @create 2017/12/14
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BaseExceptionController extends AbstractErrorController {

    //TODO
    private String sysCode = "common";

    private final ErrorProperties errorProperties;

    @Autowired
    ApplicationConstant applicationConstant;

    public BaseExceptionController(ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

        org.springframework.http.HttpStatus status = getStatus(request);
        CustomHttpStatus errorCode = CustomHttpStatus.fromHttpStatus(status.value());
        ErrorMessage error = new ErrorMessage(errorCode.getCode(),sysCode,errorCode.getMessage(), AppMessageServiceType.U.name(),status.getReasonPhrase(),null);
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView(JsonUtils.getObjectMapper());
        view.setAttributesMap(JsonUtils.object2Map(error));
        mav.setView(view);
        return mav;

    }


    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> error(HttpServletRequest request) {
        org.springframework.http.HttpStatus status = getStatus(request);
        CustomHttpStatus errorCode = CustomHttpStatus.fromHttpStatus(status.value());
        ErrorMessage error = new ErrorMessage(errorCode.getCode(),sysCode,errorCode.getMessage(), AppMessageServiceType.U.name(),status.getReasonPhrase(),null);
        return new ResponseEntity<>(JsonUtils.object2Json(error), status);
    }

}

