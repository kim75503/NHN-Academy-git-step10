package com.nhnacademy.practice_problems_3;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습 3: 전화번호 정제
        ####목표: replace() 메서드를 사용하여 다양한 형식의 전화번호를 표준 형식으로 정제하세요.

        요구사항:

        하이픈, 공백, 괄호 제거
        숫자만 남기기
        표준 형식으로 변환 (010-XXXX-XXXX)
        public class PhoneNumberNormalizer {
            public static void main(String[] args) {
                String[] phones = {
                    "010-1234-5678",
                    "010 1234 5678",
                    "(010) 1234-5678",
                    "010.1234.5678"
                };

                for (String phone : phones) {
                    // TODO: 전화번호를 정제하여 숫자만 남기세요

                    // TODO: "010-XXXX-XXXX" 형식으로 변환하세요

                 // TODO: 결과를 출력하세요
              }
         }
        }
        예상 출력:

        010-1234-5678 -> 010-1234-5678
        010 1234 5678 -> 010-1234-5678
        (010) 1234-5678 -> 010-1234-5678
        010.1234.5678 -> 010-1234-5678
         */
        String[] phones = {
            "010-1234-5678",
            "010 1234 5678",
            "(010) 1234-5678",
            "010.1234.5678"
        };
        String realphone, N, U,M;
        String[] phoneNum = new String[4];
        int i = 0;
        for(String phone : phones){
            realphone = phone.replace("(", " ");
            realphone = realphone.replace(")", "");
            realphone = realphone.replace("-", "");
            realphone = realphone.replace(".", "");
            realphone = realphone.replace(" ", "");
            realphone = realphone.trim();
            N = realphone.substring(0,3);
            U = realphone.substring(3,7);
            M = realphone.substring(7,11);

            phoneNum[i] = (N+"-"+U+"-"+M);
            i++;
        }
        for(int j = 0 ; j < phoneNum.length;j++){
            System.out.println(phones[j]+"  ->  "+ phoneNum[j]);
        }

    }
}
