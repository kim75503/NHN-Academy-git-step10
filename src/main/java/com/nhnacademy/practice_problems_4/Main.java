package com.nhnacademy.practice_problems_4;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습 4: 사용자 입력 검증
        ####목표: trim(), contains(), startsWith(), endsWith()를 사용하여 사용자 입력을 검증하세요.

        요구사항:

        공백 제거
        키워드 포함 여부 확인
        URL 프로토콜 검증
        파일 확장자 검증
        public class InputValidator {
            public static void main(String[] args) {
                String todoTitle = "  Java 공부  ";
                String url = "https://example.com/api";
                String filename = "report.pdf";

                // TODO: todoTitle의 앞뒤 공백을 제거하세요

                // TODO: "Java"라는 키워드가 포함되어 있는지 확인하세요

                // TODO: URL이 https로 시작하는지 확인하세요

                // TODO: 파일이 .pdf 확장자로 끝나는지 확인하세요

                // TODO: 검증 결과를 출력하세요
            }
        }
        예상 출력:

        정제된 제목: 'Java 공부'
        ✓ Java 관련 항목입니다.
        ✓ 보안 연결(HTTPS)입니다.
        ✓ PDF 파일입니다.
         */

        String todoTitle = "  Java 공부  ";
        String url = "https://example.com/api";
        String filename = "report.pdf";

        System.out.println("정제된 제목: " + todoTitle.trim());

        if(todoTitle.trim().contains("Java")){
            System.out.println("✓ Java 관련 항목입니다.");
        }else 
            System.out.println("Java 관련 항목이 아닙니다.");
        
        if(url.startsWith("https")){
            System.out.println("✓ 보안 연결(HTTPS)입니다.");
        }else
             System.out.println("비보안 연결입니다.");
        
        if(filename.endsWith(".pdf")){
            System.out.println("✓ PDF 파일입니다.");
        }else
            System.out.println("PDF파일이 아닙니다.");
    }
}
