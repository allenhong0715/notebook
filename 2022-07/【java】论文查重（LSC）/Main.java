package com.example;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_SET;

/**
 * author: ${USER}
 * date: ${DATE}
 * description:
 */
@Slf4j
public class Main {

    // 预加载查重库
    public static List<Sentence> preLoad() throws IOException {
        File[] files = new File("/Users/hong/IdeaProjects/practice/lcs/src/main/resources/lib").listFiles();
        Map<String,List<String>> filename2subSentence = new HashMap<>();

        for (File file : files) {
            // 处理文章内容。去掉每行前后空格，去掉空行
            List<String> sections = Files.readAllLines(file.toPath());
            sections = sections.stream().map(s->s.trim()).collect(Collectors.toList());
            sections.removeIf(s -> s == null || s.isEmpty());

            List<String> subSentence = new ArrayList<>();

            for (String section : sections) {
                // 通过标点符号分割每个子句
                subSentence.addAll(Arrays.asList(section.split("[，。；！？]")));
            }

            // 生成map
            filename2subSentence.put(file.getName().substring(0,file.getName().lastIndexOf(".")),subSentence);

        }

        log.info("preLoad finish");

        List<Sentence> res = new ArrayList<>();

        // 组装返回list，其中Sentence对象包含每个子句和对应的文章。
        filename2subSentence.forEach((k,v) ->{
            v.forEach(s->res.add(new Sentence(s,k)));
        });

        return res;

    }

    // 生成倒排索引，这里以4个字符为窗口，获取子句的倒排索引，将相同倒排索引的子句加到map同一个key的value中
    public static Map<String, List<Sentence>> buildInvertIndex(List<Sentence> sentences) {
        Map<String,List<Sentence>> res = new HashMap<>();

        for (Sentence sentence : sentences) {
            if (sentence.text.length() < 4) {
                continue;
            }
            for (int i = 0; i < sentence.text.length() - 4; i++) {
                res.computeIfAbsent(sentence.text.substring(i,i+4),k->new ArrayList<>()).add(sentence);
            }
        }

        log.info("build invertIndex finish");
        return res;
    }

    // 读取需要查重的文件
    public static List<String> readInput() throws IOException {
        List<String> res = new ArrayList<>();

        File file = new File("/Users/hong/IdeaProjects/practice/lcs/src/main/resources/check/1.txt");
        // 处理文章内容。去掉每行前后空格，去掉空行
        List<String> sections = Files.readAllLines(file.toPath());
        sections = sections.stream().map(s -> s.trim()).collect(Collectors.toList());
        sections.removeIf(s -> s == null || s.isEmpty());

        for (String section : sections) {
            res.addAll(Arrays.asList(section.split("[，。；！？]")));
        }

        log.info("readInput finish");
        return res;

    }

    // 对比，查重
    public static List<Set<Sentence>> compare(List<String> sentences, Map<String, List<Sentence>> invertIndex) {
        List<Set<Sentence>> res = new ArrayList<>();
        for (String sentence : sentences) {

            if (sentence.length() < 7) {
                // 插入空set，保证返回结果的list长度与调用参数的sentences list长度相等。
                res.add(EMPTY_SET);
                continue;
            }

            Set<Sentence> set = new HashSet<>();
            for (int i = 0; i < sentence.length() - 4; i++) {
                for (Sentence s : invertIndex.getOrDefault(sentence.substring(i, i + 4), new ArrayList<>())) {
                    if (lcs(sentence, s.text) >= 7) {
                        set.add(s);
                    }
                }
            }
            res.add(set);
        }
        return res;
    }

    // 最长公共序列查询（LCS）
    // 动态规划实现
    public static int lcs(String s1, String s2) {
//        第一种实现：
//        int pre[] = new int[s1.length()];
//        int cur[] = new int[s1.length()];
//
//        for (int i = 0; i < s2.length(); i++) {
//            for (int j = 0; j < s1.length(); j++) {
//                if (s1.charAt(j) == s2.charAt(i)) {
//                    cur[j] = j == 0 ? 1 : pre[j-1] + 1;
//                } else {
//                    cur[j] = Math.max(j == 0 ? 0 : cur[j-1],pre[j]);
//                }
//            }
//            pre = cur;
//            cur = new int[s1.length()];
//        }
//        return pre[pre.length - 1];
        // 第二中实现：
        int[][] dp = new int[s1.length()][s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                } else {
                    dp[i][j] = Math.max(i == 0 ? 0 : dp[i-1][j],j == 0 ? 0 : dp[i][j-1]);
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }

    public static void main(String[] args) throws IOException {
        List<Sentence> sentences = preLoad();
        Map<String,List<Sentence>> invertIndex = buildInvertIndex(sentences);

        List<String> input = readInput();

        List<Set<Sentence>> result = compare(input, invertIndex);

        for (int i = 0; i < result.size(); i++) {

            Set<Sentence> sentenceSet = result.get(i);

            if (!sentenceSet.isEmpty()) {
                for (Sentence sentence : sentenceSet) {
                    log.info("文中【{}】与库中【{}】的【{}】相似", input.get(i),sentence.article,sentence.text);
                }
            }

        }


    }

    @AllArgsConstructor
    public static class Sentence {

        String text;

        String article;
    }
}