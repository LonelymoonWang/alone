package com.github.alone.common.core.domain;

import cn.hutool.http.HttpStatus;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 20:02
 * @Description: 返回封装
 **********************************/
public class ResultInfo {

    public static final String RESP_CODE = "respCode";
    public static final String RESP_DESC = "respDesc";
    public static final String RESP_DATA = "respData";
    public static final String SUCCESS_DESC = "操作成功";
    public static final String ERROR_DESC = "操作失败";

    private static Map<String, Object> common(int respCode, String respDesc, Map<String, Object> respData){
        Map<String, Object> result = Maps.newHashMap();
        result.put(RESP_CODE, respCode);
        result.put(RESP_DESC,respDesc);
        if (MapUtils.isNotEmpty(respData)){
            result.put(RESP_DATA,respData);
        }
        return result;
    }

    public static Map<String, Object> success(int respCode,String respDesc){
        return common(respCode,respDesc,null);
    }
    public static Map<String, Object> success(String respDesc,Map<String, Object> respData){
        return common(HttpStatus.HTTP_OK,respDesc,respData);
    }

    public static Map<String, Object> success(String respDesc){
        return success(respDesc,null);
    }
    public static Map<String, Object> success(Map<String, Object> respData){
        return success(SUCCESS_DESC,respData);
    }
    public static Map<String, Object> success(){
        return success(SUCCESS_DESC);
    }

    public static Map<String, Object> error(int respCode,String respDesc){
        return common(respCode,respDesc,null);
    }
    public static Map<String, Object> error(String respDesc,Map<String, Object> respData){
        return common(HttpStatus.HTTP_INTERNAL_ERROR,respDesc,respData);
    }

    public static Map<String, Object> error(String respDesc){
        return error(respDesc,null);
    }
    public static Map<String, Object> error(Map<String, Object> respData){
        return error(ERROR_DESC,respData);
    }
    public static Map<String, Object> error(){
        return error(ERROR_DESC);
    }

}
