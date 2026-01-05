package com.nhnacademy.pratice_problems_2;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습 2: 날짜 문자열 파싱
        ####목표: substring() 메서드를 사용하여 날짜 문자열에서 연도, 월, 일을 추출하세요.

        요구사항:

        ISO 8601 형식의 날짜 문자열 파싱
        연도, 월, 일을 각각 추출
        포맷팅하여 출력
        public class DateParserExercise {
            public static void main(String[] args) {
                String isoDate = "2024-01-15";

                // TODO: substring()을 사용하여 연도, 월, 일을 추출하세요

                // TODO: "2024년 1월 15일" 형식으로 출력하세요
            }
        }
        예상 출력:

        2024년 1월 15일
         */

        String isoDate ="2024-01-15";

        System.out.println(isoDate.substring(0,4)+"년 " +isoDate.substring(5,7)+ "월 " + isoDate.substring(8)+"일");
    }
}
