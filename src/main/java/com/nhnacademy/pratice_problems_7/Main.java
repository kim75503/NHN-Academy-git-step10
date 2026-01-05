package com.nhnacademy.pratice_problems_7;

/*
연습 7: 조건부 메시지 생성
목표: StringBuilder를 사용하여 조건에 따라 동적으로 메시지를 생성하세요.

요구사항:

기본 메시지에 조건부로 내용 추가
null 체크
여러 조건 조합
public class MessageBuilder {
    public static void main(String[] args) {
        // 테스트 케이스 1: 제목만 있는 경우
        String msg1 = buildMessage("Java 공부", null, null);
        System.out.println(msg1);

        // 테스트 케이스 2: 카테고리와 완료 여부만 있는 경우
        String msg2 = buildMessage(null, "STUDY", true);
        System.out.println(msg2);

        // 테스트 케이스 3: 모든 정보가 있는 경우
        String msg3 = buildMessage("프로젝트 마무리", "WORK", false);
        System.out.println(msg3);
    }

    public static String buildMessage(String title, String category, Boolean done) {
        // TODO: StringBuilder를 사용하여 동적으로 메시지를 생성하세요
        // 기본: "TODO: "로 시작
        // title이 있으면: 제목 추가
        // category가 있으면: " [카테고리명]" 추가
        // done이 있으면: done=true면 " ✓", false면 " ○" 추가

        return null;  // TODO: 완성된 메시지 반환
    }
}
예상 출력:

TODO: Java 공부
TODO: [STUDY] ✓
TODO: 프로젝트 마무리 [WORK] ○
 */
public class Main {
    public static void main(String[] args) {
        // 테스트 케이스 1: 제목만 있는 경우
        String msg1 = buildMessage("Java 공부", null, null);
        System.out.println(msg1);

        // 테스트 케이스 2: 카테고리와 완료 여부만 있는 경우
        String msg2 = buildMessage(null, "STUDY", true);
        System.out.println(msg2);

        // 테스트 케이스 3: 모든 정보가 있는 경우
        String msg3 = buildMessage("프로젝트 마무리", "WORK", false);
        System.out.println(msg3);
    }

     public static String buildMessage(String title, String category, Boolean done) {
      StringBuilder message = new StringBuilder("TODO: ");
      if(title != null){
        message.append(title);
      }
    if(category != null){
        message.append(" [").append(category).append("]");
      }
    
    if(done != null){
        message.append(done ? " ✓" : " ○");
      }

        return message.toString();  // TODO: 완성된 메시지 반환
    }
}
