package com.nhnacademy.practice_problems_5;

public class Main {
    public static void main(String[] args) {
        /*
        ####연습 5: 메서드 체이닝
        목표: 메서드 체이닝을 사용하여 문자열을 여러 단계로 변환하세요.

        요구사항:

        앞뒤 공백 제거
        공백을 하이픈으로 변경
        소문자로 변환
        URL slug 형식 생성
        public class UrlSlugGenerator {
            public static void main(String[] args) {
                String title = "  Hello World Java Programming  ";

                // TODO: 메서드 체이닝을 사용하여 URL slug를 생성하세요
                // 결과: "hello-world-java-programming"

                // TODO: 여러 제목에 대해 테스트하세요
                String[] titles = {
                    "  Java Tutorial  ",
                    "Spring Boot Guide",
                    "  Clean Code Practices  "
                };
            }
        }
        예상 출력:

        원본: '  Hello World Java Programming  '
        Slug: hello-world-java-programming

        Java Tutorial   -> java-tutorial
        Spring Boot Guide -> spring-boot-guide
        Clean Code Practices   -> clean-code-practices
         */

        String title = "  Hello World Java Programming  ";

        String[] titles = {
                    "  Java Tutorial  ",
                    "Spring Boot Guide",
                    "  Clean Code Practices  "};

        System.out.println("원본: '" + title+ "'");
        System.out.println("Slug: " + title.trim().toLowerCase().replace(" ", "-"));

        for(String t : titles){
            System.out.println(t+"  ->  "+t.trim().toLowerCase().replace(" ", "-"));
        }
    }
}
