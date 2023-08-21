package com.lynx.uzz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wubaocheng1
 * @date 2023/4/13 17:14
 */
public class Test {

    public static void main(String[] args) {
        int i1 = "getWarningTip".compareTo("getWarningTip_20220902");
        System.out.println(i1 >= 0);

        try {
            testApp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() <= 3001) {
            sb.append("wubaocheng1,");
        }
        System.out.println(sb.substring(0, sb.length() - 1));

        String postTypeC1C2Code = String.format("%s_%s", "organization1Code", null);
        System.out.println(postTypeC1C2Code);

        int i = Integer.parseInt("01");
        System.out.println(i);
        System.out.println(i);

        testSort();
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
