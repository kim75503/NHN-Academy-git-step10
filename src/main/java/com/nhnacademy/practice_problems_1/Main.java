package com.nhnacademy.practice_problems_1;

public class Main {
    public static void main(String[] args) {
        /*
        ###Part 1: 문자열 조작 연습
        ####연습 1: CSV 데이터 파싱
        목표: split() 메서드를 사용하여 CSV 형식의 사용자 데이터를 파싱하는 코드를 작성하세요.

        요구사항:

        CSV 문자열을 쉼표로 분리
        각 필드를 적절한 타입으로 변환
        결과 출력
        public class CsvParserExercise {
            public static void main(String[] args) {
                String csvLine = "홍길동,30,서울,true";

                // TODO: split()을 사용하여 csvLine을 파싱하세요

                // TODO: 각 필드를 변수에 저장하세요
                // String name = ...
                // int age = ...
                // String city = ...
                // boolean isActive = ...

                // TODO: 결과를 출력하세요
                // 이름: 홍길동, 나이: 30, 도시: 서울, 활성: true
            }
        }
        예상 출력:

        이름: 홍길동, 나이: 30, 도시: 서울, 활성: true
         */

        String csvLine = "홍길동,30,서울,true";

        String[] splitCsvLine = csvLine.split(",");

        String name = splitCsvLine[0];
        int age = Integer.parseInt(splitCsvLine[1]);
        String city = splitCsvLine[2];
        boolean isActive = Boolean.parseBoolean(splitCsvLine[3]);
                
        System.out.println("이름: "+name +", 나이: " +age+", 도시: "+city+", 활성: "+isActive);
    }
}
