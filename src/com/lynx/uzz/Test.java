package com.lynx.uzz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
public class Test {

    public static void main(String[] args) {
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
