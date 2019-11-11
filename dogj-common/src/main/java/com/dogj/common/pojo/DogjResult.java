package com.dogj.common.pojo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

public class DogjResult implements Serializable {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status;
    private String msg;
    private Object data;

    public static DogjResult build(Integer status, String msg, Object data) {
        return new DogjResult(status, msg, data);
    }

    public static DogjResult ok(Object data) {
        return new DogjResult(data);
    }

    public static DogjResult ok() {
        return new DogjResult(null);
    }

    public DogjResult() {

    }

    public static DogjResult build(Integer status, String msg) {
        return new DogjResult(status, msg, null);
    }

    public DogjResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public DogjResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //将json结果转换为DogjResult对象
    public static DogjResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, DogjResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    //Object为空的时候
    public static DogjResult format(String json) {
        try {
            return MAPPER.readValue(json, DogjResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DogjResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
