package cn.mengtianyou.common.utils;


import cn.mengtianyou.common.helper.ApplicationContextHelper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Json 工具类
 * @author liups
 * @create 2017/12/19
 */
public class JsonUtils {

//    public static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

//    /**
//     * 初始化ObjectMapper
//     * @return
//     */
//    private static ObjectMapper createObjectMapper() {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
//
////        objectMapper.registerModule(new Hibernate4Module().enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING));
//        objectMapper.registerModule(new JavaTimeModule());
//
//        return objectMapper;
//    }
    /**
     * 此类spring自动配置了
     * {@link org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration}
     */
    public static ObjectMapper getObjectMapper(){
        //从bean中获取ObjectMapper
        return ApplicationContextHelper.context.getBean(ObjectMapper.class);
    }

    public static String object2Json(Object o) {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createGenerator(sw);
            getObjectMapper().writeValue(gen, o);
        } catch (IOException e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        } finally {
            if (null != gen) {
                try {
                    gen.close();
                } catch (IOException e) {
                    throw new RuntimeException("不能序列化对象为Json", e);
                }
            }
        }
        return sw.toString();
    }

    public static Map<String, Object> object2Map(Object o) {
        return getObjectMapper().convertValue(o,Map.class);
    }


    /**
     * 将 json 字段串转换为 对象.
     *
     * @param json  字符串
     * @param clazz 需要转换为的类
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     * 将 json 流转换为 对象.
     *
     * @param json  字符串
     * @param clazz 需要转换为的类
     * @return
     */
    public static <T> T json2Object(InputStream json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     * 将 json 流转换为 对象.
     *
     * @param json  字符串
     * @param clazz 需要转换为的类
     * @return
     */
    public static <T> T json2Object(Reader json, Class<T> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + json, e);
        }
    }

    /**
     *   将 json 字段串转换为 List.
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> json2List(String json,Class<T> clazz) throws IOException {
        JavaType type = getObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz);

        List<T> list = getObjectMapper().readValue(json, type);
        return list;
    }


    /**
     *  将 json 字段串转换为 数据.
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T>  T[] json2Array(String json,Class<T[]> clazz) throws IOException {
        return getObjectMapper().readValue(json, clazz);

    }

    public static <T> T node2Object(JsonNode jsonNode, Class<T> clazz) {
        try {
            T t = getObjectMapper().treeToValue(jsonNode, clazz);
            return t;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("将 Json 转换为对象时异常,数据是:" + jsonNode.toString(), e);
        }
    }

    public static JsonNode object2Node(Object o) {
        try {
            if(o == null) {
                return getObjectMapper().createObjectNode();
            } else {
                return getObjectMapper().convertValue(o, JsonNode.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为Json", e);
        }
    }

}
