package com.nhnacademy.pratice_problems_6;

/*연습 6: StringBuilder로 CSV 생성
목표: StringBuilder를 사용하여 여러 TODO 항목을 CSV 형식으로 변환하세요.

요구사항:

StringBuilder 사용
여러 TODO를 CSV 라인으로 변환
헤더 포함
파일에 저장 가능한 형식

public class TodoCsvGenerator {
    static class Todo {
        int id;
        String title;
        int hours;
        boolean done;

        Todo(int id, String title, int hours, boolean done) {
            this.id = id;
            this.title = title;
            this.hours = hours;
            this.done = done;
        }
    }

    public static void main(String[] args) {
        Todo[] todos = {
            new Todo(1, "Java 공부", 3, false),
            new Todo(2, "운동하기", 1, true),
            new Todo(3, "독서", 2, false)
        };

        // TODO: StringBuilder를 사용하여 CSV 형식으로 변환하세요
        // 헤더: id,title,hours,done

        // TODO: 결과를 출력하세요
    }
}
예상 출력:

id,title,hours,done
1,Java 공부,3,false
2,운동하기,1,true
3,독서,2,false

총 3개 항목을 CSV로 변환했습니다.
 */
public class Main {
    public static void main(String[] args) {
        Todo[] todos = {
            new Todo(1, "Java 공부", 3, false),
            new Todo(2, "운동하기", 1, true),
            new Todo(3, "독서", 2, false)
        };

        StringBuilder csv = new StringBuilder();
        
        csv.append("id,title,hours,done").append("\n");

        for(Todo todo : todos){
            csv.append(todo.id).append(",").append(todo.title).append(",")
            .append(todo.hours).append(",").append(todo.done).append("\n");

        }

        System.out.println(csv.toString());

        System.out.println("총 " + todos.length+"개 항목을 CSV로 변환했습니다.");
        
    }
}


class Todo {
    int id;
    String title;
    int hours;
    boolean done;

    Todo(int id, String title, int hours, boolean done) {
        this.id = id;
        this.title = title;
        this.hours = hours;
        this.done = done;
    }
}
