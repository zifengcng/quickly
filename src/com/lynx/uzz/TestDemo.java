package com.lynx.uzz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wubaocheng1
 * @date 2023/4/13 17:14
 */
public class TestDemo {

    @Test
    public void testExpression() {
        String[] expression = new String[]{"(", "A", "&&", "B", ")", "||", "(", "C", "&&", "D", ")"};
        String[] suffixExpression = convertToSuffixExpression(expression);
        System.out.println(JSON.toJSONString(suffixExpression));
    }

    /**
     * 中缀表达式转为后缀表达式
     */
    private String[] convertToSuffixExpression(String[] expression) {
        int size = expression.length;
        String[] res = new String[size];
        int cur = 0;

        Deque<String> stack = new ArrayDeque<>();

        for (String ele : expression) {
            if (StringUtils.equals(ele, "(")) {
                stack.push(ele);
            } else if (StringUtils.equals(ele, ")")) {
                while (!StringUtils.equals(stack.peek(), "(")) {
                    res[cur++] = stack.pop();
                }
                // 移除"("
                stack.pop();
            } else if (isOperator(ele)) {
                // 操作符
                while (!stack.isEmpty() && getOrder(stack.peek()) >= getOrder(ele)) {
                    res[cur++] = stack.pop();
                }
                stack.push(ele);
            } else {
                // 普通元素
                res[cur++] = ele;
            }
        }
        while (!stack.isEmpty()) {
            res[cur++] = stack.pop();
        }
        return res;
    }

    /**
     * 获取优先级
     */
    private int getOrder(String ele) {
        if (StringUtils.equalsAny(ele, "&&", "||")) {
            return 1;
        }
        return 0;
    }

    /**
     * 判断是否为操作符
     */
    private boolean isOperator(String ele) {
        return StringUtils.equalsAny(ele, "&&", "||");
    }

    @Test
    public void testSplit() {
        String str = "( A && B ) || ( C && D )";
        String[] split = str.split(" ");
        System.out.println(JSON.toJSONString(split));
    }

    public static void main(String[] args) {
        String str = "C1,3C;C1,家电;C1,时尚;C1,商超;C1,汽车;C1,跨境;C1,平台;C1,生态服务;职位,采销岗;职位,运营岗;职位,分析师岗;职位,营销岗;大促,大促;财务,损益;交易,交易;流量,来源;流量,搜索;流量,推荐;用户,PLUS;用户,品牌会员;用户,粉丝;营销,优惠券;营销,秒杀;营销,预售;供应链,供应链;服务,服务;体验,体验;实时,秒级;实时,分钟级;实时,十分钟;离线,T+1;离线,T+2;";
        String[] split = str.split(";");
        Map<String, List<String>> map = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split(",");
            map.computeIfAbsent(split1[0], k -> new ArrayList<>()).add(split1[1]);
        }
        JSONArray jsonArray = new JSONArray();
        map.forEach((k, v) -> {
            JSONObject object = new JSONObject();
            object.put("label", k);

            JSONArray children = new JSONArray();
            for (String c : v) {
                JSONObject obj = new JSONObject();
                obj.put("label", c);
                obj.put("children", new JSONArray());
                children.add(obj);
            }
            object.put("children", children);
            jsonArray.add(object);
        });
        System.out.println(jsonArray);

        long diffTime = -1000;
        int diffDay = (int) (diffTime / (24 * 60 * 60 * 1000) + 1);
        List<Integer> remindDays = Arrays.asList(30, 25, 20, 15, 10, 5, 4, 3, 2, 1);
        remindDays.contains(diffDay);

        String regex = "(?<=\\{\\{)(.+?)(?=}})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(
            "java.lang.RuntimeException: com.jd.oa.common.exception.BusinessException: {{com.jd.oa.common.exception.BusinessException: 流程中心不存在[anbaidong&]或此人已离职!!}}\n");
        if (matcher.find()) {
            System.out.println(matcher.group());
        }

        List<Integer> nums = Arrays.asList(1, 2, 1);
        List<Integer> collect = nums.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);

    }

    public static void testSort() {
        List<Integer> l1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            l1.add(i);
        }
        l1.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int sort1 = 0;
                int sort2 = 0;
                if (o1 == 10) {
                    sort1 = 2;
                } else if (o1 == 5) {
                    sort1 = 1;
                }
                if (o2 == 10) {
                    sort1 = 2;
                } else if (o2 == 5) {
                    sort1 = 1;
                }
                return sort2 - sort1;
//                int sort1 = 0;
//                int sort2 = 0;
//                if (o1 == 5) {
//                    sort1 = 1;
//                }
//                if (o2 == 5) {
//                    sort2 = 1;
//                }
//                return sort2 - sort1;
            }
        });
        System.out.println(l1);
    }

    public static void testApp() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("/usr/local/dev/resp"));
        while (br.ready()) {
            String line = br.readLine();
            sb.append(line);
        }
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        Map<String, Integer> count = new HashMap<>();
        for (Object o : jsonArray) {
            JSONObject object = JSON.parseObject(o.toString());
            JSONObject app = object.getJSONObject("app");
            if (Objects.isNull(app)) {
                continue;
            }
            String appName = app.getString("appName");
            Integer integer = count.get(appName);
            if (Objects.isNull(integer)) {
                count.put(appName, 1);
            } else {
                count.put(appName, integer + 1);
            }
        }

        List<Num> nums = new ArrayList<>();
        count.forEach((name, c) -> {
            Num num = new Num(name, c);
            nums.add(num);
        });
        nums.sort(Comparator.comparing(Num::getCount).reversed());
        System.out.println(JSON.toJSONString(nums));
    }

    static class Num {

        String name;
        int count;

        public Num(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

}
