/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 84859
 * @field name of input
 * @label label của input - nhãn
 */
public class GetParam {
    //kiểm tra xem input có phải trống hay ko ? trả về lỗi trong Attribute
    //thêm chức năng hiển thị defaultvalue
    public static String getStringParam(HttpServletRequest req, String field,String label, String defaultValue){
        String result = req.getParameter(field);
        if(result == null || result.trim().isEmpty()){
            //nếu ko có gì thì xem giá trị mặc định
            if(defaultValue == null){
                req.setAttribute(label + "Error", label + " is required");
                return null;
            }
            return defaultValue;
        }
        return result;
        
    }
    //để object để hạn chế lỗi NullPointer
    public static Object getClientAttribute(HttpServletRequest request, String field, Object defaultValue) {
        Object value = request.getAttribute(field);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public static Object getClientParams(HttpServletRequest request, String field, Object defaultValue) {
        Object value = request.getParameter(field);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}
