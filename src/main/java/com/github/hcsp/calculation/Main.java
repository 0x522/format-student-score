package com.github.hcsp.calculation;

import java.util.stream.Stream;

public class Main {
    /**
     * 输入学生姓名和分数，当： 90<=score<=100分时，返回"学生X的分数是Y，评级是A"；
     *
     * <p>60<=score<=89时，返回"学生X的分数是Y，评级是B"；
     *
     * <p>0<=score<=59时，返回"学生X的分数是Y，评级是C"；
     *
     * <p>score为其他值时，返回"非法输入"。
     *
     * @param student 学生的姓名
     * @param score 学生的分数
     * @return 结果字符串
     */
    public static String formatStudentScore(String student, int score) {
        return Stream.of(Level.values()).filter(level -> level.test(score)).findFirst().get().format(student, score);
    }

    enum Level {
        A {
            @Override
            boolean test(int score) {
                return score >= 90 && score <= 100;
            }

        }, B {
            @Override
            boolean test(int score) {
                return score >= 60 && score <= 89;
            }
        }, C {
            @Override
            boolean test(int score) {
                return score >= 0 && score <= 59;
            }
        }, ILLEGAL {
            @Override
            boolean test(int score) {
                return score < 0 || score > 100;
            }

            @Override
            String format(String student, int score) {
                return "非法输入";
            }
        };

        abstract boolean test(int score);

        String format(String student, int score) {
            return String.format("学生%s的分数是%d，评级是%s", student, score, name());
        }
    }

    public static void main(String[] args) {
        System.out.println(formatStudentScore("张三", -1));
        System.out.println(formatStudentScore("张三", 0));
        System.out.println(formatStudentScore("张三", 60));
        System.out.println(formatStudentScore("张三", 900));
    }
}
