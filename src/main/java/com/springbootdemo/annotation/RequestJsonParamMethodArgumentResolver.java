package com.springbootdemo.annotation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.springbootdemo.core.entity.MapWapper;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;

/**
 * 解析请求参数json字符串.
 *
 */
public class RequestJsonParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver
    implements WebArgumentResolver {

  private ObjectMapper mapper = new ObjectMapper();

  public RequestJsonParamMethodArgumentResolver() {
    super(null);
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {

    if (parameter.hasParameterAnnotation(RequestJsonParam.class)) {
      return true;
    }
    return false;

  }

  @Override
  protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
    RequestJsonParam annotation = parameter.getParameterAnnotation(RequestJsonParam.class);
    return new RequestJsonParamNamedValueInfo(annotation);

  }

  @Override
  protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest)
      throws Exception {
    // 请求中的参数
    String[] paramValues = webRequest.getParameterValues(name);
    // 方法中参数的类型
    Class<?> paramType = parameter.getParameterType();

    if (paramValues == null) {
      return null;
    }

    if (paramValues.length == 1) {
      String text = paramValues[0];
      Type type = parameter.getGenericParameterType();

      if (MapWapper.class.isAssignableFrom(paramType)) {
        MapWapper<?, ?> jsonMap = (MapWapper<?, ?>) paramType.newInstance();

        MapType mapType = (MapType) getJavaType(HashMap.class);

        if (type instanceof ParameterizedType) {

          mapType = mapper.getTypeFactory().constructMapType(HashMap.class,
              (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0],
              (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1]);

        }
        jsonMap.setInnerMap(mapper.readValue(text, mapType));
        return jsonMap;
      }

      JavaType javaType = getJavaType(paramType);

      if (Collection.class.isAssignableFrom(paramType)) {
        javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,
            ((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]));
      }

      return mapper.readValue(paramValues[0], javaType);
    }

    throw new UnsupportedOperationException("too many request json parameter '" + name
        + "' for method parameter type [" + paramType + "], only support one json parameter");
  }

  protected JavaType getJavaType(Class<?> clazz) {

    return mapper.constructType(clazz);

  }

  @Override
  protected void handleMissingValue(String paramName, MethodParameter parameter)
      throws ServletException {
    String paramType = parameter.getParameterType().getName();
    throw new ServletRequestBindingException("Missing request json parameter '" + paramName
        + "' for method parameter type [" + paramType + "]");
  }

  private class RequestJsonParamNamedValueInfo extends NamedValueInfo {

    private RequestJsonParamNamedValueInfo() {
      super("", false, null);
    }

    private RequestJsonParamNamedValueInfo(RequestJsonParam annotation) {
      super(annotation.value(), annotation.required(), null);
    }
  }

  /**
   * spring 3.1之前
   */
  @Override
  public Object resolveArgument(MethodParameter parameter, NativeWebRequest request)
      throws Exception {
    if (!supportsParameter(parameter)) {
      return WebArgumentResolver.UNRESOLVED;
    }
    return resolveArgument(parameter, null, request, null);
  }
}
