package cn.aurthur.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 用于包含父子关系的 List 和树之间的转换
 *
 * @author aurthur
 * @since 1.0.0
 */
public class TreeUtil {

    /**
     * @param list         含有父子关系的 JSONArray 数据，一般从数据库内取出
     * @param id           数据的 ID 表识
     * @param pid          数据的父 ID 表识
     * @param childNodeKey 子节点的 Key 名称
     * @return
     */
    public static JSONArray listToTree(JSONArray list, String id, String pid, String childNodeKey) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        // 将数组转为Object的形式，key为数组中的id
        for (Object value : list) {
            JSONObject json = (JSONObject) value;
            hash.put(json.getString(id), json);
        }
        // 遍历结果集
        for (Object o : list) {
            // 单条记录
            JSONObject aVal = (JSONObject) o;
            // 在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            // 如果记录的pid存在，则说明它有父节点，将它添加到孩子节点的集合中
            if (hashVP != null) {
                // 检查是否有child属性
                if (hashVP.get(childNodeKey) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(childNodeKey);
                    ch.add(aVal);
                    hashVP.put(childNodeKey, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(childNodeKey, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }
}