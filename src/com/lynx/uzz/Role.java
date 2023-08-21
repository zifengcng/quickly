package com.lynx.uzz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wubaocheng1
 * @date 2023/1/18 09:34
 */
public class Role {

    private String name;

    private Boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Role{" +
            "name='" + name + '\'' +
            ", isAdmin=" + isAdmin +
            '}';
    }

    public static void main(String[] args) {
        List<Role> list = new ArrayList<>();
        Role role = new Role();
        role.setName("abc");
        role.setAdmin(false);
        list.add(role);
        role = new Role();
        role.setName("zzz");
        role.setAdmin(true);
        list.add(role);
        role = new Role();
        role.setName("abc");
        role.setAdmin(true);
        list.add(role);
        Map<String, Boolean> map = list.stream()
            .collect(Collectors.toMap(Role::getName, Role::getAdmin, (v1, v2) -> v1 | v2));
        System.out.println(map);
        map = list.stream()
            .collect(Collectors.toMap(Role::getName, Role::getAdmin, (v1, v2) -> v1));
        System.out.println(map);

        String regular = "(\\(\\))|(\\([-+*/]\\))|(\\(M\\))|(\\([-+*/]+M[-+*/]*\\))|(\\([-+*/]*M[-+*/]+\\))";
        String content = "()";
        String n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "M()";
        n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "(M)";
        n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "(+M)";
        n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "(M+)";
        n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "(M+-)";
        n = content.replaceAll(regular, "N");
        System.out.println(n);
        content = "(+)";
        n = content.replaceAll(regular, "N");
        System.out.println(n);

        Map<String, String> map1 = new HashMap<>();
        String s = map1.getOrDefault(null, "abc");
        System.out.println(s);
        System.out.println(map1.containsKey(null));

        Set<String> set = new HashSet<>();
        System.out.println(set.contains(null));
        set.add("abc");
        set.removeIf(e -> e.equals("abc"));
        System.out.println(set.stream().collect(Collectors.toList()));

        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list1.add(i + "");
        }
        System.out.println(list1);
        Collections.reverse(list1);
        System.out.println(list1);
    }
}
