package util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.*;


/**
 * Created by liujianjun on 15/4/27.
 */
public class JsonUtils {

	
	public static String mapIntToJson(Map<Integer, List<Integer>> map){
		 Map<String, List<Integer>> map_ = new HashMap<String,List<Integer>>();
		 for(Integer i : map.keySet()){
			 map_.put(i + "", map.get(i));
		 }
		 return JSONObject.fromObject(map_).toString();
	}
	
    public static String mapToJson(Map<String,String> map){
    		
        return "";
    }

    /**
     * 将bean转换为json
     * @param object
     * @return
     */
    public static String beanToJson(Object object){
        return beanToJsonExclude(object, null);
    }

    /**
     * 排除bean中的某些元素然后转为json
     * @param object
     * @param exclude
     * @return
     */
    public static String beanToJsonExclude(Object object,String[] exclude){
        JsonConfig config =  new JsonConfig();
        config.setExcludes(exclude);

        if(object == null){
            return "";
        }

        return JSONObject.fromObject(object,config).toString();
    }


    /**
     * 给bena添加属性值
     * @param object
     * @param exclude
     * @return
     */
    public static String  addStringAttributeToBean(Object object,String[] exclude , Map<String,String> map){
        JsonConfig config =  new JsonConfig();
        config.setExcludes(exclude);

        if(object == null){
            return "";
        }

        JSONObject jsonObject = JSONObject.fromObject(object,config);
        if(map != null){
            jsonObject.putAll(map);
        }
        return jsonObject.toString();
    }


    /**
     * 给bena添加属性值
     * @param object
     * @param exclude
     * @return
     */
    public static String  addIntAttributeToJson(Object object,String[] exclude , Map<String,Integer> map){
        JsonConfig config =  new JsonConfig();

        if(exclude != null){
            config.setExcludes(exclude);
        }

        if(object == null){
            return "";
        }

        JSONObject jsonObject = JSONObject.fromObject(object,config);
        if(map != null){
            jsonObject.putAll(map);
        }
        return jsonObject.toString();
    }


    /**
     * 给bena添加int属性值
     * @param object
     * @param exclude
     * @return
     */
    public static String  addttributeIntToBean(Object object,String[] exclude , Map<String,Integer> map){
        JsonConfig config =  new JsonConfig();
        config.setExcludes(exclude);

        if(object == null){
            return "";
        }

        JSONObject jsonObject = JSONObject.fromObject(object,config);
        if(map != null){
            jsonObject.putAll(map);
        }

        return jsonObject.toString();
    }


    /**
     * 添加list到json
     * @param object
     * @param list
     * @return
     */
    public static String addListToJson(Object object,Map<String,List<String>> list){

        if(object == null){
            return "";
        }
        JSONObject jsonObject = JSONObject.fromObject(object.toString());

        if(list != null){
            for(String str : list.keySet()){
                jsonObject.put(str,list.get(str));
            }
        }

        return jsonObject.toString();
    }

    /**
     * 将json的数组转换为list
     * @param obj
     * @return
     */
    public static List<String> objToList(Object obj){

        List<String> list = new ArrayList<String >();
        if(obj != null){
            list = JSONArray.toList(JSONArray.fromObject(obj.toString()),String.class);
        }
        return list;
    }

    public static Map<String,String> objToMap(Object obj){
        Map<String,String> jobExtends = new HashMap<String,String>();
        if(obj != null){
            jobExtends = JSONObject.fromObject(obj.toString());
        }

        return jobExtends;
    }



    public static void main(String[] args) {

        //System.out.println(objToList("[开发,技术]"));
        //System.out.println(objToList("[\"开发\",\"技术\"]"));
        //System.out.println(objToList("[\"开发\",\"技术\"]").size());

//        Map<String,Integer> map = new HashMap<String,Integer>();
//        map.put("bb", 1);
//        System.out.println(addIntAttributeToJson("{'id':'aa'}",null,map));
//
        String list = "{'id':'aa'}";

        Map<String,List<String>> map = new HashMap<String,List<String>>();
        List<String> aa = new ArrayList<String>();
        aa.add("a1");
        aa.add("a2");
        map.put("aaaaa", aa);
        List<String> bb = new ArrayList<String>();
        bb.add("b1");
        bb.add("b2");
        map.put("bbbb", bb);

        System.out.println(addListToJson(list,map));

    }


    /**
     * 对象转换为string
     * @param obj
     * @return
     */
    public  static String  objToString(Object obj){
        if(obj == null){
           return  "";
        }
        return obj.toString();
    }

    /**
     * 对象转换为int
     * @param obj
     * @return
     */
    public static int objToInt(Object obj){
        if(obj == null){
            return 0;
        }else if(obj.toString().equals("")){
            return 0;
        }
        return Integer.parseInt(obj.toString());
    }


    /**
     * 将字符串转换为list
     * @return
     */
    public static List<String> strToList(String str,String split){
        List<String> list = null;
        if(str != null){
            String[] array = str.split(split);
            list = new ArrayList<String>(Arrays.asList(array));
        }else{
            list = new ArrayList<String>();
        }
        return list;
    }

    /**
     * 将list转为Json
     * @param list
     * @return
     */
    public static String listToJson(List<String> list){
        String str = null;
        if(list !=null && list.size()>0){
            str = JSONArray.fromObject(list).toString();
        }else {
            str = "[]";
        }
        return str;
    }
    
    /**
     * list转为字符串
     */
    public static String ListToStr(List<Integer> list,String split){
    		StringBuilder str = new StringBuilder();
    		if(list == null){
    			return str.toString();
    		}
    		for(Integer s : list){
    			str.append(s + "").append(split);
    		}
    		
    		if(str.toString().length() > 0){
    			return str.toString().substring(0, str.toString().length()-1);
    		}
    		return str.toString();
    		
    }
    
    
    /**
     * set转为字符串
     */
    public static String setToStr(Set<Integer> set,String split){
    		StringBuilder str = new StringBuilder();
    		if(set == null){
    			return str.toString();
    		}
    		for(Integer s : set){
    			str.append(s + "").append(split);
    		}
    		
    		if(str.toString().length() > 0){
    			return str.toString().substring(0, str.toString().length()-1);
    		}
    		return str.toString();
    		
    }

}
