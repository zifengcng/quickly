package com.lynx.uzz;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author cheng
 * @Date 2020/11/9
 */
public class XMLReader {

    private Set<String> tables;

    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = format.format(new Date());
        System.out.println("开始，" + start);
        xmlReader.init();
        xmlReader.read();
        String end = format.format(new Date());
        System.out.println("结束，" + end);
    }

    private void read() {
        System.out.println("开始读取文件。。。");
        BufferedWriter writer = null;
        try {
            File file = new File("D:\\workspace\\idea-workspace\\zht\\java-wxapp-platform\\wxapp-pianpian\\src\\main\\resources\\mappers");
            File[] files = file.listFiles();
            SAXReader saxReader = new SAXReader();

            // 输出文件
            File outputFile = new File("D:\\文件\\开发文档\\电商\\微服务\\商品\\sql.txt");
            writer = new BufferedWriter(new FileWriter(outputFile));

            for (File f : files) {
                List<String> output = new ArrayList<>();
                if (f.isDirectory()) {
                    File[] subFiles = f.listFiles();
                    for (File sub : subFiles) {
                        output.addAll(readFile(saxReader, sub));
                    }
                } else {
                    output.addAll(readFile(saxReader, f));
                }
                writeFile(output, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("读取文件结束。");
    }

    private void writeFile(List<String> output, BufferedWriter writer) throws IOException {
        if (output.size() == 0) {
            return;
        }
        System.out.println("写入文件" + output.size() + "行");
        for (String line : output) {
            writer.newLine();
            writer.write(line);
        }
    }

    private List<String> readFile(SAXReader saxReader, File sub) throws DocumentException {
        List<String> res = new ArrayList<>();
        Document doc = saxReader.read(sub);
        // 文件名
        String name = doc.getName();
//        System.out.println(name);

        List<String> list = new ArrayList<>();
        int count = 1;

        Element rootEle = doc.getRootElement();
        List<Element> elements = rootEle.elements();
        for (Element ele : elements) {
            // id
//            Attribute attr = ele.attribute("id");
//            String id = attr.getValue();
//            System.out.println(id);
            // xml
            String xml = ele.asXML();
//            System.out.println(xml);

            StringBuilder sb = new StringBuilder();

            List content = ele.content();
            readContent(sb, content);

            if (sb.length() > 0) {
                list.add("\t(" + count++ + ") tables: " + sb.toString());
                list.add(xml);
                list.add("\n");
            }
        }

        if (list.size() > 0) {
            res.add("文件》》" + name);
            res.add("内容》》");
            res.addAll(list);
            res.add("\n");
        }

        return res;
    }

    private void readContent(StringBuilder sb, List content) {
        for (Object line : content) {
            if (line instanceof DefaultText) {
                String[] words = ((DefaultText) line).getText().split(" ");
                for (String word : words) {
                    if (tables.contains(word)) {
                        sb.append(word).append(",");
                    }
                }
            } else if (line instanceof DefaultElement) {
                readContent(sb, ((DefaultElement) line).content());
            } else if (line instanceof DefaultCDATA) {
                // CDATA 忽略
            } else {
                // 注释
            }
        }
    }

    private void init() {
        System.out.println("初始化。。。");
        String[] strs = new String[]{
                "pp_card_discount_spu",
                "pp_card_discount_spu_replica",
                "pp_category",
                "pp_category_spec",
                "pp_category_tenant",
                "pp_change_price_config",
                "pp_change_price_item",
                "pp_ensure",
                "pp_ensure_spu",
                "pp_ensure_spu_replica",
                "pp_floor_advert",
                "pp_floor_banner",
                "pp_floor_category",
                "pp_floor_grid",
                "pp_floor_layout",
                "pp_floor_layout_luckbag",
                "pp_floor_notice",
                "pp_floor_store",
                "pp_layout_category",
                "pp_layout_goods",
                "pp_layout_store",
                "pp_revoke_record",
                "pp_sku",
                "pp_sku_import",
                "pp_sku_replica",
                "pp_sku_spec",
                "pp_spu",
                "pp_spu_audit_log",
                "pp_spu_distribute",
                "pp_spu_explosive",
                "pp_spu_ext",
                "pp_spu_preference",
                "pp_spu_replica",
                "pp_spu_reserve_order",
                "pp_spu_takeout_category",
                "pp_spu_takeout_category_replica"
        };
        tables = new HashSet<>(Arrays.asList(strs));
    }

    private void buildTrie(String[] tables) {
        int len = tables.length;
        for (int i = 0; i < len; i++) {
            String tab = tables[i];
            char[] chars = tab.toCharArray();
            Trie cur = new Trie();
            for (char c : chars) {
                if (cur.contains(c)) {
                    cur = cur.get(c);
                } else {
                    cur = cur.put(c);
                }
            }
            cur.isLeaf = true;
        }
    }

    public class Trie {
        char val;
        boolean isLeaf;
        Map<Character, Trie> map;

        public boolean contains(char val) {
            return map.containsKey(val);
        }

        public Trie get(char val) {
            return map.get(val);
        }

        public Trie put(char val) {
            Trie trie = new Trie(val);
            map.put(val, trie);
            return trie;
        }

        public Trie() {
            this.map = new HashMap<>();
        }

        public Trie(char val) {
            this.val = val;
            this.map = new HashMap<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            List<String> res = new ArrayList<>();
            dfs(this, sb, res);
            return res.toString();
        }

        private void dfs(Trie root, StringBuilder sb, List<String> res) {
            if (root.map.size() == 0) {
                res.add(sb.toString());
                return;
            }
            root.map.forEach((k, v) -> {
                sb.append(k);
                dfs(v, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            });
        }
    }
}
