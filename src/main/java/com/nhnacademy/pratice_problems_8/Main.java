package com.nhnacademy.pratice_problems_8;


/*
연습 8: String vs StringBuilder 성능 비교
목표: String 연결과 StringBuilder의 성능 차이를 직접 측정하고 비교하세요.

요구사항:

두 가지 방법으로 1000개의 숫자를 문자열로 연결
실행 시간 측정 및 비교
메모리 사용량 비교 (선택 사항)
public class PerformanceComparison {
    public static void main(String[] args) {
        int iterations = 1000;

        // TODO: 방법 A - String 연결 (비효율적)
        // 시작 시간 측정
        // for 루프로 문자열 연결
        // 종료 시간 측정
        // 소요 시간 출력

        // TODO: 방법 B - StringBuilder (효율적)
        // 시작 시간 측정
        // for 루프로 StringBuilder 사용
        // toString() 호출
        // 종료 시간 측정
        // 소요 시간 출력

        // TODO: 성능 차이 출력
    }
}
예상 출력:

=== 방법 A: String 연결 ===
소요 시간: 45ms
결과 길이: 3893

=== 방법 B: StringBuilder ===
소요 시간: 0ms
결과 길이: 3893

=== 성능 비교 ===
StringBuilder가 약 500.0배 빠릅니다.
시간 복잡도: String O(n²) vs StringBuilder O(n)
 */
public class Main {
    public static void main(String[] args) {
        int iterations = 1000;

        long startA = System.nanoTime();
        String resultA = "";
        for(int i = 0; i < iterations; i++){
            resultA += i +",";
        }
        long endA = System.nanoTime();
        long timeA = (endA - startA ) / 1_000_000;

        System.out.println("=== 방법 A: String 연결 ===");
        System.out.println("소요 시간: " + timeA + "ms");
        System.out.println("결과 길이: " + resultA.length());
        System.out.println();

        // 방법 B: StringBuilder (효율적)
        long startB = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(i).append(",");
        }
        String resultB = sb.toString();
        long endB = System.nanoTime();
        long timeB = (endB - startB) / 1_000_000;

        System.out.println("=== 방법 B: StringBuilder ===");
        System.out.println("소요 시간: " + timeB + "ms");
        System.out.println("결과 길이: " + resultB.length());
        System.out.println();

        // 성능 차이
        double speedup = (double) timeA / timeB;
        System.out.println("=== 성능 비교 ===");
        System.out.printf("StringBuilder가 약 %.1f배 빠릅니다.%n", speedup);
        System.out.println("시간 복잡도: String O(n²) vs StringBuilder O(n)");
    }
}
