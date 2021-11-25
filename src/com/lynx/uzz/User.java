package com.lynx.uzz;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author cheng
 * @Date 2020/12/10
 */
public class User {

    // 234
    private static Logger log = LoggerFactory.getLogger(User.class);

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            List<User> list = new ArrayList<>();
            list.add(null);

            Map<Long, String> map = list.stream().filter(s -> s.getName() != null)
                    .collect(Collectors.toMap(User::getId, User::getName, (k1, k2) -> k1));
            System.out.println(map);
        } catch (Exception e) {
            System.out.println("e:"+e);
            System.out.println("Message:"+e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            // master
        }
    }
}

