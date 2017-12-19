package cn.mengtianyou.common;

import cn.mengtianyou.common.constants.CustomHttpStatus;
import cn.mengtianyou.common.messages.ErrorMessage;

import java.io.Serializable;

/**
 * @author liups
 * @create 2017/12/15
 */
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 描述 : 状态码(业务定义)
     */
    private String code;

    /**
     * 描述 : 错误，有异常时这里有值
     */
    private ErrorMessage error;

    /**
     * 描述 : 结果集(泛型)，正确请求时这里有值
     */
    private T result;

    private ResponseResult(){

    }

    public static<T> ResponseResult buildResult(T result) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(CustomHttpStatus.SUCCESS.getCode());
        responseResult.setResult(result);
        return responseResult;
    }

    public static ResponseResult buildError(ErrorMessage error){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(error.getCode());
        responseResult.setError(error);
        return responseResult;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
