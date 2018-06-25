package com.springbootdemo.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * jackjson一些转换方法
 *
 */
public class JsonUtils {

  private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
  public static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) {
    String result = "{\"result\":true,\"loginName\":\"xxx\",\"rootId\":\"xxxx\"}";


    Map<String, String> userInfo =
        JsonUtils.toObject(result, new TypeReference<Map<String, String>>() {});

    System.out.println(userInfo);

  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <T> T toCollection(Class<? extends Collection> collectionClass,
      Class<?> elementClasses, String json) {
    // JavaType javaType =
    // mapper.getTypeFactory().constructCollectionType(collectionClass,
    // elementClasses);

    CollectionType listType =
        mapper.getTypeFactory().constructCollectionType(collectionClass, elementClasses);

    try {
      return (T) mapper.readValue(json, listType);
    } catch (JsonParseException e) {
      logger.error("JsonParseException: ", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException: ", e);
    } catch (IOException e) {
      logger.error("IOException: ", e);
    }
    return null;
  }

  /**
   * jackjson把json字符串转换为Java对象的实现方法
   *
   *
   * @param <T> 转换为的java对象
   * @param json json字符串
   * @param typeReference jackjson自定义的类型
   * @return 返回Java对象
   */
  public static <T> T toObject(String json, TypeReference<T> typeReference) {
    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    try {
      return mapper.readValue(json, typeReference);
    } catch (JsonParseException e) {
      logger.error("JsonParseException: ", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException: ", e);
    } catch (IOException e) {
      logger.error("IOException: ", e);
    }
    return null;
  }

  /**
   * json转换为java对象
   *
   * @param <T> 要转换的对象
   * @param json 字符串
   * @param valueType 对象的class
   * @return 返回对象
   */
  public static <T> T toObject(String json, Class<T> valueType) {
    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    try {
      return mapper.readValue(json, valueType);
    } catch (JsonParseException e) {
      logger.error("JsonParseException: ", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException: ", e);
    } catch (IOException e) {
      logger.error("IOException: ", e);
    }
    return null;
  }

  /**
   * java对象转换为json字符串
   *
   * @param object Java对象
   * @return 返回字符串
   */
  public static String toJson(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonGenerationException e) {
      logger.error("JsonGenerationException: ", e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException: ", e);
    } catch (IOException e) {
      logger.error("IOException: ", e);
    }
    return null;
  }

}
