# Step 10: Save/Load String & File

[← 이전: Step 10](step_10.md) | [목차](00.overview.md) | [다음: Step 12 →](step_12.md)

---

**사전 지식**:
- try-catch, 입력 검증
- 기본 String 메서드

**학습 목표**:
- String 클래스 주요 메서드 활용
- 문자열 조작 및 파싱
- FileWriter를 사용한 파일 쓰기
- BufferedReader를 사용한 파일 읽기
- 데이터 직렬화/역직렬화 기초

**핵심 내용**:
- **문자열**: `split()`, `join()`, `substring()`, `replace()`, `trim()`, `contains()`, `startsWith()`, `endsWith()`
- **StringBuilder**: 효율적인 문자열 연결
- **String formatting**: `String.format()`, `printf()`
- `FileWriter`, `BufferedWriter`
- `FileReader`, `BufferedReader`
- `try-with-resources` 구문: `try (Writer w = ...) { }`
- CSV 형식: `title,hours,done`
- `String.split(",")` 파싱
- `File.exists()` 파일 존재 확인

**실습 과제**:

**Part 1: 문자열 조작**
1. String 주요 메서드 실습 (split, substring, replace 등)
2. StringBuilder를 활용한 효율적인 문자열 연결
3. String.format()을 사용한 포맷팅
4. CSV 데이터 파싱 연습

**Part 2: 파일 저장/로드**
1. TODO 목록을 CSV 파일로 저장 (확장된 필드 포함)
2. 프로그램 시작 시 파일에서 데이터 로드
3. 파일 없으면 빈 리스트로 시작
4. 파일 포맷: `id,title,category,priority,dueDate,hours,done,createdAt`

**산출물**:
- [ ] String 메서드 활용 연습 완료
- [ ] StringBuilder로 문자열 연결 구현
- [ ] String.format() 활용
- [ ] saveToFile() 메서드
- [ ] loadFromFile() 메서드
- [ ] 프로그램 재시작 시 데이터 유지

**참고 자료**:

> 📚 분류 기준: [Java Basic](https://kizoo.gitlab.io/archive/javabasic/)

| 분류                                                                                   | 주제                          | 링크                                                                                |
| -------------------------------------------------------------------------------------- | ----------------------------- | ----------------------------------------------------------------------------------- |
| [Numbers & Strings](https://kizoo.gitlab.io/archive/javabasic/#numbers--strings)       | String 클래스                 | [Oracle: Strings](https://docs.oracle.com/javase/tutorial/java/data/strings.html)   |
| [Numbers & Strings](https://kizoo.gitlab.io/archive/javabasic/#numbers--strings)       | String 메서드 가이드          | [Baeldung: String Guide](https://www.baeldung.com/java-string)                      |
| [Numbers & Strings](https://kizoo.gitlab.io/archive/javabasic/#numbers--strings)       | StringBuilder                 | [StringBuilder vs StringBuffer](https://www.baeldung.com/java-string-builder-string-buffer) |
| [Numbers & Strings](https://kizoo.gitlab.io/archive/javabasic/#numbers--strings)       | String Formatting             | [Formatting with printf and format](https://www.baeldung.com/java-printstream-printf) |
| [Essential Classes](https://kizoo.gitlab.io/archive/javabasic/#essential-java-classes) | Basic I/O                     | [Oracle: I/O](https://docs.oracle.com/javase/tutorial/essential/io/index.html)      |
| [Essential Classes](https://kizoo.gitlab.io/archive/javabasic/#essential-java-classes) | Apache Commons IO (FileUtils) | [FileUtils Guide](https://www.baeldung.com/apache-commons-io)                       |
| [Essential Classes](https://kizoo.gitlab.io/archive/javabasic/#essential-java-classes) | Javanotes Ch.11               | [Input/Output Streams, Files](https://math.hws.edu/javanotes/c11/index.html)        |

---

## 학습 자료

### Part 1: 문자열 조작

<details>
<summary><strong>📘 String 클래스의 특징</strong></summary>

String은 Java에서 가장 자주 사용되는 클래스로, 다른 클래스들과 구별되는 여러 특별한 특징을 가지고 있습니다.

#### 1. Built-in 클래스 (java.lang 패키지)

**import 없이 사용 가능**:
```java
// String은 java.lang 패키지에 포함
// import 문 없이 바로 사용 가능
String message = "Hello World";  // OK

// 참고: 다른 패키지는 import 필요
import java.util.ArrayList;  // 필요
import java.time.LocalDate;  // 필요
```

**java.lang 패키지의 주요 클래스**:
- `String`: 문자열
- `System`: 시스템 리소스 접근 (System.out, System.in)
- `Math`: 수학 함수
- `Integer`, `Double`, `Boolean`: 래퍼 클래스
- `Object`: 모든 클래스의 최상위 부모

#### 2. final 클래스 - 상속 불가

**String 클래스 정의**:
```java
public final class String {
    // ...
}
```

**final의 의미**:
```java
// 불가능: String을 상속할 수 없음
public class MyString extends String {  // 컴파일 에러!
    // ...
}
```

**왜 final인가?**:
- **보안**: String은 보안에 민감한 데이터(비밀번호, URL 등)를 다루므로 변조 방지
- **성능**: 불변성이 보장되어 String Pool에서 재사용 가능
- **스레드 안전성**: 상속으로 인한 동시성 문제 방지

#### 3. 불변성 (Immutable) - 수정 불가

**불변 객체의 의미**:
```java
String str = "Hello";
str.toUpperCase();  // "HELLO"를 반환하지만 str은 변하지 않음!

System.out.println(str);  // "Hello" (변경되지 않음)

// 새로운 String 객체가 생성됨
String upper = str.toUpperCase();
System.out.println(upper);  // "HELLO"
```

**내부 구조 (char 배열이 final)**:
```java
public final class String {
    private final char[] value;  // final - 배열 참조 변경 불가

    // setter 메서드 없음 - 값 변경 불가
}
```

**불변성의 장점**:
- **String Pool**: 같은 문자열 리터럴 재사용 → 메모리 절약
- **스레드 안전**: 여러 스레드가 동시에 접근해도 안전
- **해시코드 캐싱**: `hashCode()`를 한 번만 계산하여 HashMap 등에서 성능 향상
- **보안**: 비밀번호, 네트워크 연결 정보 등 변조 방지

**불변성의 단점**:
- **메모리 낭비**: 문자열 수정 시마다 새 객체 생성
- **성능 저하**: 반복적인 연결 작업 시 비효율적 (StringBuilder 사용 권장)

#### 4. String Pool (문자열 풀)

**String Pool이란?**:
- JVM Heap 메모리의 특별한 영역
- 동일한 문자열 리터럴은 하나의 객체만 생성하여 재사용
- 메모리 절약 및 성능 최적화

**리터럴 vs new 연산자**:
```java
// 방법 1: 리터럴 (String Pool 사용)
String str1 = "Hello";
String str2 = "Hello";
System.out.println(str1 == str2);  // true (같은 객체)

// 방법 2: new 연산자 (Heap에 새 객체 생성)
String str3 = new String("Hello");
String str4 = new String("Hello");
System.out.println(str3 == str4);  // false (다른 객체)

// 내용 비교는 equals() 사용
System.out.println(str3.equals(str4));  // true
```

**String Pool 메모리 구조**:
```
Heap 메모리
┌─────────────────────────────────┐
│  String Pool                    │
│  ┌───────────────┐              │
│  │ "Hello"       │ ← str1, str2 │
│  └───────────────┘              │
│                                 │
│  일반 Heap 영역                  │
│  ┌───────────────┐              │
│  │ "Hello" (복사) │ ← str3      │
│  └───────────────┘              │
│  ┌───────────────┐              │
│  │ "Hello" (복사) │ ← str4      │
│  └───────────────┘              │
└─────────────────────────────────┘
```

**intern() 메서드**:
```java
String str1 = new String("Hello");  // Heap에 생성
String str2 = str1.intern();        // String Pool로 이동

String str3 = "Hello";              // String Pool 참조
System.out.println(str2 == str3);   // true (같은 Pool 객체)
```

#### 5. + 연산자 오버로딩과 성능 오버헤드

**Java에서 유일한 연산자 오버로딩**:
```java
// Java는 기본적으로 연산자 오버로딩을 지원하지 않지만
// String의 + 연산자는 예외적으로 지원

String result = "Hello" + " " + "World";  // 문자열 연결
int sum = 10 + 20;  // 정수 덧셈 (다른 의미)
```

**+ 연산자의 내부 동작 (컴파일 시)**:
```java
// 작성한 코드
String result = "Hello" + " " + "World";

// 컴파일러가 변환한 코드 (Java 8 이하)
String result = new StringBuilder()
    .append("Hello")
    .append(" ")
    .append("World")
    .toString();
```

**반복문에서의 성능 문제**:
```java
// 비효율적인 코드 (각 반복마다 새 StringBuilder 생성)
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;  // 컴파일러가 매번 새 StringBuilder로 변환
}

// 수동 최적화 (성능 500배 향상)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

**성능 오버헤드 분석**:
```java
// 반복 1000번 + 연산 성능 측정
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;
    // 매 반복마다:
    // 1. 새 StringBuilder 생성
    // 2. result 복사
    // 3. i 추가
    // 4. toString() 호출하여 새 String 생성
    // 5. 이전 result 객체는 GC 대상
}
// 결과: 약 500,000개의 임시 객체 생성
// 시간 복잡도: O(n²)
```

**컴파일 타임 최적화 (리터럴 연결)**:
```java
// 컴파일 타임에 자동으로 하나의 문자열로 결합
String message = "Hello" + " " + "World";
// 컴파일 결과: String message = "Hello World";

// 하지만 변수가 포함되면 런타임 연결
String name = "Java";
String message = "Hello " + name;  // 런타임에 StringBuilder 사용
```

#### 6. == vs equals()

**== 연산자 (참조 비교)**:
```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");

System.out.println(str1 == str2);   // true (같은 Pool 객체)
System.out.println(str1 == str3);   // false (다른 객체)
```

**equals() 메서드 (내용 비교)**:
```java
System.out.println(str1.equals(str2));  // true
System.out.println(str1.equals(str3));  // true
System.out.println("Hello".equals(str1));  // true

// 실무 권장: null-safe 비교
String input = getUserInput();  // null일 수 있음
if ("ADMIN".equals(input)) {  // null이어도 안전
    // ...
}
```

#### 요약: String 사용 가이드

| 상황                     | 권장 방법              | 이유                    |
| ------------------------ | ---------------------- | ----------------------- |
| 문자열 생성              | `"literal"`            | String Pool 활용        |
| 문자열 비교              | `equals()`             | 내용 비교               |
| 반복적 문자열 연결       | `StringBuilder`        | O(n) vs O(n²) 성능      |
| 간단한 한 줄 연결        | `+` 연산자             | 가독성 (컴파일러 최적화) |
| 포맷팅이 중요한 경우     | `String.format()`      | 명확한 형식 지정        |
| 보안 정보 저장 (비밀번호) | `char[]` 배열          | 불변성으로 메모리에 남음 |

</details>

<details>
<summary><strong>📘 String 클래스 주요 메서드</strong></summary>

String은 Java에서 가장 자주 사용되는 클래스입니다. 문자열은 불변(immutable)이므로, 모든 메서드는 새로운 String을 반환합니다.

**1. split() - 문자열 분리**:
```java
String csv = "Java,Python,JavaScript";
String[] languages = csv.split(",");
// 결과: ["Java", "Python", "JavaScript"]

// 실무 예시: CSV 파싱
String line = "홍길동,30,서울";
String[] parts = line.split(",");
String name = parts[0];    // "홍길동"
int age = Integer.parseInt(parts[1]);  // 30
String city = parts[2];    // "서울"
```

**2. join() - 문자열 결합**:
```java
String[] words = {"Hello", "World", "Java"};
String result = String.join(" ", words);
// 결과: "Hello World Java"

// 실무 예시: CSV 생성
String csv = String.join(",", "홍길동", "30", "서울");
// 결과: "홍길동,30,서울"
```

**3. substring() - 부분 문자열 추출**:
```java
String str = "Hello World";
String sub1 = str.substring(0, 5);   // "Hello" (0부터 5 이전까지)
String sub2 = str.substring(6);      // "World" (6부터 끝까지)

// 실무 예시: 날짜 파싱
String date = "2024-01-15";
String year = date.substring(0, 4);   // "2024"
String month = date.substring(5, 7);  // "01"
String day = date.substring(8, 10);   // "15"
```

**4. replace() - 문자열 치환**:
```java
String str = "Hello World";
String replaced = str.replace("World", "Java");
// 결과: "Hello Java"

// 실무 예시: 데이터 정제
String dirty = "010-1234-5678";
String clean = dirty.replace("-", "");  // "01012345678"
```

**5. trim() - 양쪽 공백 제거**:
```java
String str = "   Hello World   ";
String trimmed = str.trim();
// 결과: "Hello World"

// 실무 예시: 사용자 입력 정제
String input = reader.readLine().trim();  // 공백 제거
```

**6. contains() - 문자열 포함 여부**:
```java
String str = "Hello World";
boolean hasHello = str.contains("Hello");  // true
boolean hasJava = str.contains("Java");    // false

// 실무 예시: 검색 기능
if (todo.getTitle().contains(keyword)) {
    // 검색 결과에 포함
}
```

**7. startsWith() / endsWith() - 시작/끝 문자열 확인**:
```java
String filename = "report.pdf";
boolean isPdf = filename.endsWith(".pdf");  // true

String url = "https://example.com";
boolean isHttps = url.startsWith("https");  // true

// 실무 예시: 파일 확장자 검사
if (filename.endsWith(".csv")) {
    loadFromCsv(filename);
}
```

**String 메서드 체이닝**:
```java
String result = "  Hello World  "
    .trim()                    // "Hello World"
    .replace(" ", "_")         // "Hello_World"
    .toLowerCase();            // "hello_world"
```

</details>

<details>
<summary><strong>📘 StringBuilder - 효율적인 문자열 연결</strong></summary>

**String vs StringBuilder**:

| 특성               | String (불변)         | StringBuilder (가변)         |
| ------------------ | --------------------- | ---------------------------- |
| 문자열 수정        | 새 객체 생성          | 기존 객체 수정               |
| 성능 (반복 연결)   | 느림 (O(n²))          | 빠름 (O(n))                  |
| 메모리 사용        | 많음 (객체 여러 개)   | 적음 (객체 하나)             |
| 스레드 안전성      | 안전 (불변)           | 불안전 (동기화 필요시 StringBuffer) |
| 사용 시점          | 간단한 연결           | 반복적 연결, 동적 문자열 생성 |

**비효율적인 String 연결 (피해야 할 패턴)**:
```java
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i + ",";  // 매번 새 String 객체 생성! (O(n²))
}
// 1000번 반복 시 약 500,000개의 임시 String 객체 생성
```

**효율적인 StringBuilder 사용**:
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i).append(",");  // 기존 객체 수정 (O(n))
}
String result = sb.toString();
```

**StringBuilder 주요 메서드**:
```java
StringBuilder sb = new StringBuilder();

// 1. append() - 끝에 추가
sb.append("Hello");
sb.append(" ").append("World");  // 체이닝 가능
sb.append(123);                   // 숫자도 가능

// 2. insert() - 특정 위치에 삽입
sb.insert(0, "Start: ");  // 맨 앞에 삽입

// 3. delete() - 범위 삭제
sb.delete(0, 7);  // 0부터 7 이전까지 삭제

// 4. reverse() - 뒤집기
sb.reverse();

// 5. toString() - String으로 변환
String result = sb.toString();
```

**실무 예시 1: CSV 생성**:
```java
public String toCsvLine(Todo todo) {
    StringBuilder sb = new StringBuilder();
    sb.append(todo.getId()).append(",")
      .append(todo.getTitle()).append(",")
      .append(todo.getCategory()).append(",")
      .append(todo.getPriority()).append(",")
      .append(todo.getDueDate()).append(",")
      .append(todo.getHours()).append(",")
      .append(todo.isDone()).append(",")
      .append(todo.getCreatedAt());
    return sb.toString();
}
```

**실무 예시 2: 동적 SQL 쿼리 생성**:
```java
public String buildQuery(String title, Category category) {
    StringBuilder query = new StringBuilder("SELECT * FROM todos WHERE 1=1");

    if (title != null) {
        query.append(" AND title LIKE '%").append(title).append("%'");
    }

    if (category != null) {
        query.append(" AND category = '").append(category).append("'");
    }

    return query.toString();
}
```

**실무 예시 3: 대용량 문자열 생성**:
```java
public String generateReport(List<Todo> todos) {
    StringBuilder report = new StringBuilder();
    report.append("=== TODO 보고서 ===\n");
    report.append("총 건수: ").append(todos.size()).append("\n\n");

    for (Todo todo : todos) {
        report.append("제목: ").append(todo.getTitle()).append("\n")
              .append("시간: ").append(todo.getHours()).append("h\n")
              .append("완료: ").append(todo.isDone() ? "O" : "X").append("\n")
              .append("---\n");
    }

    return report.toString();
}
```

**성능 비교 (1000번 연결)**:
```java
// String: 약 500ms (O(n²))
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;
}

// StringBuilder: 약 1ms (O(n))
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

**언제 StringBuilder를 사용해야 할까?**:
- ✅ 반복문 안에서 문자열 연결
- ✅ 동적으로 문자열 생성 (조건에 따라 추가)
- ✅ 대용량 문자열 생성
- ❌ 간단한 한 줄 연결 (`"Hello" + " " + "World"` 괜찮음)
- ❌ 컴파일 타임 상수 (`"Hello" + "World"` → 컴파일러가 자동 최적화)

</details>

<details>
<summary><strong>📘 String Formatting (printf, String.format)</strong></summary>

**printf()와 String.format()**:
- 둘 다 같은 형식 지정자를 사용
- `printf()`: 출력과 동시에 포맷팅
- `String.format()`: 포맷팅된 String 반환

**주요 형식 지정자**:
| 지정자 | 설명           | 예시                                    | 출력            |
| ------ | -------------- | --------------------------------------- | --------------- |
| `%s`   | 문자열         | `String.format("%s", "Hello")`          | "Hello"         |
| `%d`   | 정수           | `String.format("%d", 123)`              | "123"           |
| `%f`   | 실수           | `String.format("%f", 3.14)`             | "3.140000"      |
| `%.2f` | 소수점 2자리   | `String.format("%.2f", 3.14159)`        | "3.14"          |
| `%5d`  | 5자리 정수     | `String.format("%5d", 42)`              | "   42"         |
| `%05d` | 0으로 채우기   | `String.format("%05d", 42)`             | "00042"         |
| `%-5s` | 왼쪽 정렬      | `String.format("%-5s", "Hi")`           | "Hi   "         |
| `%n`   | 줄바꿈         | `String.format("Line1%nLine2")`         | "Line1\nLine2"  |

**기본 사용법**:
```java
// printf() - 즉시 출력
System.out.printf("이름: %s, 나이: %d\n", "홍길동", 30);
// 출력: 이름: 홍길동, 나이: 30

// String.format() - 문자열 생성
String msg = String.format("이름: %s, 나이: %d", "홍길동", 30);
System.out.println(msg);
```

**실무 예시 1: 표 형식 출력**:
```java
public void printTodoList(List<Todo> todos) {
    System.out.println("┌──────┬────────────────────┬──────┬────────┐");
    System.out.println("│  ID  │       제목         │ 시간 │  완료  │");
    System.out.println("├──────┼────────────────────┼──────┼────────┤");

    for (Todo todo : todos) {
        System.out.printf("│ %4d │ %-18s │ %4dh │   %s   │%n",
            todo.getId(),
            truncate(todo.getTitle(), 18),
            todo.getHours(),
            todo.isDone() ? "O" : "X"
        );
    }

    System.out.println("└──────┴────────────────────┴──────┴────────┘");
}

// 출력 예시:
// ┌──────┬────────────────────┬──────┬────────┐
// │  ID  │       제목         │ 시간 │  완료  │
// ├──────┼────────────────────┼──────┼────────┤
// │    1 │ Java 공부          │    3h │   X   │
// │    2 │ 운동하기           │    1h │   O   │
// └──────┴────────────────────┴──────┴────────┘
```

**실무 예시 2: 금액 포맷팅**:
```java
double price = 1234567.89;

System.out.printf("가격: %,.2f원\n", price);
// 출력: 가격: 1,234,567.89원

String formatted = String.format("총액: %,d원", (int) price);
// 결과: "총액: 1,234,567원"
```

**실무 예시 3: 날짜/시간 포맷팅**:
```java
import java.time.LocalDateTime;

LocalDateTime now = LocalDateTime.now();

System.out.printf("현재 시각: %tF %tT\n", now, now);
// 출력: 현재 시각: 2024-01-15 14:30:45

// 더 나은 방법: DateTimeFormatter 사용
String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
```

**실무 예시 4: 진행률 표시**:
```java
public void printProgress(int current, int total) {
    double percent = (double) current / total * 100;
    System.out.printf("진행률: %5.1f%% (%d/%d)\n", percent, current, total);
}

// 출력:
// 진행률:  35.0% (35/100)
// 진행률:  72.5% (145/200)
```

**실무 예시 5: 로그 메시지**:
```java
public void logTodoAction(String action, Todo todo) {
    String timestamp = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );

    String message = String.format("[%s] %s - ID: %d, 제목: %s",
        timestamp,
        action,
        todo.getId(),
        todo.getTitle()
    );

    System.out.println(message);
}

// 출력: [2024-01-15 14:30:45] 등록 - ID: 5, 제목: Java 공부
```

**복잡한 포맷팅 예시**:
```java
public String formatTodoReport(List<Todo> todos) {
    int total = todos.size();
    int completed = (int) todos.stream().filter(Todo::isDone).count();
    double completionRate = total > 0 ? (double) completed / total * 100 : 0;

    return String.format(
        """
        ╔════════════════════════════╗
        ║     TODO 현황 보고서       ║
        ╠════════════════════════════╣
        ║ 총 개수   : %10d건 ║
        ║ 완료      : %10d건 ║
        ║ 미완료    : %10d건 ║
        ║ 완료율    : %9.1f%%  ║
        ╚════════════════════════════╝
        """,
        total,
        completed,
        total - completed,
        completionRate
    );
}
```

**printf vs StringBuilder vs String concatenation**:
```java
// 간단한 경우: + 연산자
String simple = "이름: " + name + ", 나이: " + age;

// 포맷이 중요한 경우: printf/String.format
String formatted = String.format("가격: %,d원", price);

// 반복적 연결: StringBuilder
StringBuilder sb = new StringBuilder();
for (Todo todo : todos) {
    sb.append(todo.toString()).append("\n");
}
```

</details>

### Part 2: 파일 저장/로드

<details>
<summary><strong>📘 Java I/O 개요</strong></summary>

**I/O Stream 종류**:
| Stream 타입          | 용도            | 주요 클래스                           |
| -------------------- | --------------- | ------------------------------------- |
| **Byte Stream**      | 바이너리 데이터 | `FileInputStream`, `FileOutputStream` |
| **Character Stream** | 텍스트 데이터   | `FileReader`, `FileWriter`            |
| **Buffered Stream**  | 성능 최적화     | `BufferedReader`, `BufferedWriter`    |

**권장 조합 (텍스트 파일)**:
```java
// 읽기
BufferedReader reader = new BufferedReader(new FileReader("file.txt"));

// 쓰기
BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
```

**try-with-resources (Java 7+)**:
```java
// 자동으로 리소스 닫기 (close() 호출 불필요)
try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"))) {
    writer.write("내용");
}  // 자동으로 writer.close() 호출됨
```

</details>

<details>
<summary><strong>📘 파일 저장 (FileWriter)</strong></summary>

**CSV 형식으로 저장**:
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public void saveToFile(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Todo todo : todoList) {
            // CSV 형식: title,hours,done
            String line = todo.getTitle() + "," +
                          todo.getHours() + "," +
                          todo.isDone();
            writer.write(line);
            writer.newLine();  // 줄바꿈
        }
        log.info("파일 저장 완료: {}", filename);
    } catch (IOException e) {
        log.error("파일 저장 실패: {}", e.getMessage());
    }
}
```

**저장 결과 (todos.csv)**:
```
Java 공부,3,false
운동하기,1,true
독서,2,false
```

**FileWriter 옵션**:
```java
// 덮어쓰기 (기본)
new FileWriter("file.txt")

// 이어쓰기 (append)
new FileWriter("file.txt", true)
```

</details>

<details>
<summary><strong>📘 파일 읽기 (BufferedReader)</strong></summary>

**CSV 파일에서 로드**:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public void loadFromFile(String filename) {
    File file = new File(filename);

    // 파일 존재 확인
    if (!file.exists()) {
        log.info("파일이 없습니다. 빈 리스트로 시작합니다.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;

        // 한 줄씩 읽기
        while ((line = reader.readLine()) != null) {
            // CSV 파싱
            String[] parts = line.split(",");

            String title = parts[0];
            int hours = Integer.parseInt(parts[1]);
            boolean done = Boolean.parseBoolean(parts[2]);

            todoList.add(new Todo(title, hours, done));
        }

        log.info("파일 로드 완료: {} 건", todoList.size());

    } catch (IOException e) {
        log.error("파일 읽기 실패: {}", e.getMessage());
    }
}
```

**readLine() 동작**:
```java
// 한 줄씩 읽음, 파일 끝에 도달하면 null 반환
while ((line = reader.readLine()) != null) {
    // 처리
}
```

</details>

<details>
<summary><strong>📘 String.split() 파싱</strong></summary>

**split() 메서드**:
```java
String line = "Java 공부,3,false";
String[] parts = line.split(",");

// 결과:
// parts[0] = "Java 공부"
// parts[1] = "3"
// parts[2] = "false"
```

**주의: 특수문자 이스케이프**:
```java
// split은 정규표현식을 사용
"a.b.c".split(".")     // 빈 배열! (. = 모든 문자)
"a.b.c".split("\\.")   // ["a", "b", "c"] 정상

// 안전한 방법
"a|b|c".split("\\|")   // 파이프(|) 분리
```

**CSV 파싱 시 주의점**:
```java
// 쉼표가 데이터에 포함된 경우
"Java, Spring 공부,3,false"  // 잘못 파싱됨!

// 해결책 1: 다른 구분자 사용 (탭, 파이프 등)
// 해결책 2: CSV 라이브러리 사용 (OpenCSV 등)
```

</details>

<details>
<summary><strong>📘 프로그램 시작/종료 시 자동 로드/저장</strong></summary>

**Main 클래스 통합**:
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static final String FILENAME = "todos.csv";

    public static void main(String[] args) throws IOException {
        TodoService service = new TodoService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 프로그램 시작 시 파일에서 로드
        service.loadFromFile(FILENAME);

        while (true) {
            printMenu();
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    // 등록
                    break;
                case "2":
                    // 조회
                    break;
                case "0":
                    // 종료 시 파일 저장
                    service.saveToFile(FILENAME);
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
```

**파일 구조**:
```
project/
├── src/main/java/...
├── todos.csv           ← 데이터 파일 (프로젝트 루트)
└── pom.xml
```

</details>

<details>
<summary><strong>📘 FileUtils 활용 (실무 권장)</strong></summary>

**Apache Commons IO** 라이브러리의 `FileUtils`를 사용하면 파일 I/O를 간결하게 처리할 수 있습니다.

**의존성 추가 (pom.xml)**:
```xml
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
</dependency>
```

**BufferedReader/Writer vs FileUtils 비교**:
| 방식                            | 코드량 | 예외 처리 | 권장          |
| ------------------------------- | ------ | --------- | ------------- |
| BufferedReader/Writer 직접 사용 | 많음   | 직접 처리 | 원리 이해용   |
| **FileUtils** (Commons IO)      | 한 줄  | 내부 처리 | **실무 권장** |

**FileUtils 주요 메서드**:
```java
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

// 파일 전체를 List<String>으로 읽기
List<String> lines = FileUtils.readLines(
    new File("todos.csv"),
    StandardCharsets.UTF_8
);

// List<String>을 파일에 쓰기
FileUtils.writeLines(
    new File("todos.csv"),
    StandardCharsets.UTF_8.name(),
    lines
);

// 파일 존재 여부 확인 (디렉토리 자동 생성)
FileUtils.touch(new File("todos.csv"));
```

**실무 권장 코드 - 저장**:
```java
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public void saveToFile(String filename) {
    List<String> lines = new ArrayList<>();

    for (Todo todo : todoList) {
        String line = todo.getTitle() + "," +
                      todo.getHours() + "," +
                      todo.isDone();
        lines.add(line);
    }

    try {
        FileUtils.writeLines(
            new File(filename),
            StandardCharsets.UTF_8.name(),
            lines
        );
        log.info("파일 저장 완료: {}", filename);
    } catch (IOException e) {
        log.error("파일 저장 실패: {}", e.getMessage());
    }
}
```

**실무 권장 코드 - 로드**:
```java
public void loadFromFile(String filename) {
    File file = new File(filename);

    if (!file.exists()) {
        log.info("파일이 없습니다. 빈 리스트로 시작합니다.");
        return;
    }

    try {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] parts = line.split(",");
            String title = parts[0];
            int hours = Integer.parseInt(parts[1]);
            boolean done = Boolean.parseBoolean(parts[2]);

            todoList.add(new Todo(title, hours, done));
        }

        log.info("파일 로드 완료: {} 건", todoList.size());
    } catch (IOException e) {
        log.error("파일 읽기 실패: {}", e.getMessage());
    }
}
```

**활용 이유**:
- **간결함**: `try-with-resources` 없이 한 줄로 처리
- **리소스 관리**: 스트림 자동 닫힘 처리
- **검증된 코드**: 실무에서 널리 사용되는 라이브러리
- **바퀴 재발명 금지**: 직접 구현 대신 검증된 도구 사용

**학습 접근법**:
1. **먼저**: BufferedReader/Writer로 원리 이해
2. **이후**: FileUtils로 실무 코드 작성

</details>

---

## 연습 문제

### Part 1: 문자열 조작 연습

### 연습 1: CSV 데이터 파싱

**목표**: `split()` 메서드를 사용하여 CSV 형식의 사용자 데이터를 파싱하는 코드를 작성하세요.

**요구사항**:
- CSV 문자열을 쉼표로 분리
- 각 필드를 적절한 타입으로 변환
- 결과 출력

```java
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
```

**예상 출력**:
```
이름: 홍길동, 나이: 30, 도시: 서울, 활성: true
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class CsvParserExercise {
    public static void main(String[] args) {
        String csvLine = "홍길동,30,서울,true";

        // CSV 파싱
        String[] parts = csvLine.split(",");

        // 각 필드 추출 및 타입 변환
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        String city = parts[2];
        boolean isActive = Boolean.parseBoolean(parts[3]);

        // 결과 출력
        System.out.printf("이름: %s, 나이: %d, 도시: %s, 활성: %b%n",
            name, age, city, isActive);
    }
}
```

**출력**:
```
이름: 홍길동, 나이: 30, 도시: 서울, 활성: true
```

</details>

---

### 연습 2: 날짜 문자열 파싱

**목표**: `substring()` 메서드를 사용하여 날짜 문자열에서 연도, 월, 일을 추출하세요.

**요구사항**:
- ISO 8601 형식의 날짜 문자열 파싱
- 연도, 월, 일을 각각 추출
- 포맷팅하여 출력

```java
public class DateParserExercise {
    public static void main(String[] args) {
        String isoDate = "2024-01-15";

        // TODO: substring()을 사용하여 연도, 월, 일을 추출하세요

        // TODO: "2024년 1월 15일" 형식으로 출력하세요
    }
}
```

**예상 출력**:
```
2024년 1월 15일
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class DateParserExercise {
    public static void main(String[] args) {
        String isoDate = "2024-01-15";

        // substring으로 각 부분 추출
        String year = isoDate.substring(0, 4);
        String month = isoDate.substring(5, 7);
        String day = isoDate.substring(8, 10);

        // 앞의 0 제거 (01 -> 1)
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);

        // 포맷팅하여 출력
        System.out.printf("%s년 %d월 %d일%n", year, monthInt, dayInt);
    }
}
```

**출력**:
```
2024년 1월 15일
```

</details>

---

### 연습 3: 전화번호 정제

**목표**: `replace()` 메서드를 사용하여 다양한 형식의 전화번호를 표준 형식으로 정제하세요.

**요구사항**:
- 하이픈, 공백, 괄호 제거
- 숫자만 남기기
- 표준 형식으로 변환 (010-XXXX-XXXX)

```java
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
```

**예상 출력**:
```
010-1234-5678 -> 010-1234-5678
010 1234 5678 -> 010-1234-5678
(010) 1234-5678 -> 010-1234-5678
010.1234.5678 -> 010-1234-5678
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class PhoneNumberNormalizer {
    public static void main(String[] args) {
        String[] phones = {
            "010-1234-5678",
            "010 1234 5678",
            "(010) 1234-5678",
            "010.1234.5678"
        };

        for (String phone : phones) {
            // 모든 특수문자와 공백 제거
            String cleaned = phone.replace("-", "")
                                  .replace(" ", "")
                                  .replace("(", "")
                                  .replace(")", "")
                                  .replace(".", "");

            // 표준 형식으로 변환
            String formatted = cleaned.substring(0, 3) + "-" +
                             cleaned.substring(3, 7) + "-" +
                             cleaned.substring(7, 11);

            System.out.println(phone + " -> " + formatted);
        }
    }
}
```

**출력**:
```
010-1234-5678 -> 010-1234-5678
010 1234 5678 -> 010-1234-5678
(010) 1234-5678 -> 010-1234-5678
010.1234.5678 -> 010-1234-5678
```

</details>

---

### 연습 4: 사용자 입력 검증

**목표**: `trim()`, `contains()`, `startsWith()`, `endsWith()`를 사용하여 사용자 입력을 검증하세요.

**요구사항**:
- 공백 제거
- 키워드 포함 여부 확인
- URL 프로토콜 검증
- 파일 확장자 검증

```java
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
```

**예상 출력**:
```
정제된 제목: 'Java 공부'
✓ Java 관련 항목입니다.
✓ 보안 연결(HTTPS)입니다.
✓ PDF 파일입니다.
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class InputValidator {
    public static void main(String[] args) {
        String todoTitle = "  Java 공부  ";
        String url = "https://example.com/api";
        String filename = "report.pdf";

        // 공백 제거
        String cleanedTitle = todoTitle.trim();
        System.out.println("정제된 제목: '" + cleanedTitle + "'");

        // 키워드 포함 여부
        if (cleanedTitle.contains("Java")) {
            System.out.println("✓ Java 관련 항목입니다.");
        }

        // URL 프로토콜 검증
        if (url.startsWith("https")) {
            System.out.println("✓ 보안 연결(HTTPS)입니다.");
        } else if (url.startsWith("http")) {
            System.out.println("⚠ 비보안 연결(HTTP)입니다.");
        }

        // 파일 확장자 검증
        if (filename.endsWith(".pdf")) {
            System.out.println("✓ PDF 파일입니다.");
        } else if (filename.endsWith(".csv")) {
            System.out.println("✓ CSV 파일입니다.");
        } else {
            System.out.println("⚠ 알 수 없는 파일 형식입니다.");
        }
    }
}
```

**출력**:
```
정제된 제목: 'Java 공부'
✓ Java 관련 항목입니다.
✓ 보안 연결(HTTPS)입니다.
✓ PDF 파일입니다.
```

</details>

---

### 연습 5: 메서드 체이닝

**목표**: 메서드 체이닝을 사용하여 문자열을 여러 단계로 변환하세요.

**요구사항**:
- 앞뒤 공백 제거
- 공백을 하이픈으로 변경
- 소문자로 변환
- URL slug 형식 생성

```java
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
```

**예상 출력**:
```
원본: '  Hello World Java Programming  '
Slug: hello-world-java-programming

  Java Tutorial   -> java-tutorial
Spring Boot Guide -> spring-boot-guide
  Clean Code Practices   -> clean-code-practices
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class UrlSlugGenerator {
    public static void main(String[] args) {
        String title = "  Hello World Java Programming  ";

        // 메서드 체이닝으로 URL slug 생성
        String slug = title.trim()
                          .replace(" ", "-")
                          .toLowerCase();

        System.out.println("원본: '" + title + "'");
        System.out.println("Slug: " + slug);
        System.out.println();

        // 여러 제목 테스트
        String[] titles = {
            "  Java Tutorial  ",
            "Spring Boot Guide",
            "  Clean Code Practices  "
        };

        for (String t : titles) {
            String s = t.trim().replace(" ", "-").toLowerCase();
            System.out.println(t + " -> " + s);
        }
    }
}
```

**출력**:
```
원본: '  Hello World Java Programming  '
Slug: hello-world-java-programming

  Java Tutorial   -> java-tutorial
Spring Boot Guide -> spring-boot-guide
  Clean Code Practices   -> clean-code-practices
```

</details>

---

### 연습 6: StringBuilder로 CSV 생성

**목표**: StringBuilder를 사용하여 여러 TODO 항목을 CSV 형식으로 변환하세요.

**요구사항**:
- StringBuilder 사용
- 여러 TODO를 CSV 라인으로 변환
- 헤더 포함
- 파일에 저장 가능한 형식

```java
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
```

**예상 출력**:
```
id,title,hours,done
1,Java 공부,3,false
2,운동하기,1,true
3,독서,2,false

총 3개 항목을 CSV로 변환했습니다.
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
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

        // StringBuilder로 CSV 생성
        StringBuilder csv = new StringBuilder();

        // 헤더 추가
        csv.append("id,title,hours,done").append("\n");

        // 각 TODO를 CSV 라인으로 추가
        for (Todo todo : todos) {
            csv.append(todo.id).append(",")
               .append(todo.title).append(",")
               .append(todo.hours).append(",")
               .append(todo.done)
               .append("\n");
        }

        // 결과 출력
        System.out.println(csv.toString());

        // 성능 정보
        System.out.println("총 " + todos.length + "개 항목을 CSV로 변환했습니다.");
    }
}
```

**출력**:
```
id,title,hours,done
1,Java 공부,3,false
2,운동하기,1,true
3,독서,2,false

총 3개 항목을 CSV로 변환했습니다.
```

</details>

---

### 연습 7: 조건부 메시지 생성

**목표**: StringBuilder를 사용하여 조건에 따라 동적으로 메시지를 생성하세요.

**요구사항**:
- 기본 메시지에 조건부로 내용 추가
- null 체크
- 여러 조건 조합

```java
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
```

**예상 출력**:
```
TODO: Java 공부
TODO: [STUDY] ✓
TODO: 프로젝트 마무리 [WORK] ○
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
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
        StringBuilder message = new StringBuilder("TODO: ");

        if (title != null) {
            message.append(title);
        }

        if (category != null) {
            message.append(" [").append(category).append("]");
        }

        if (done != null) {
            message.append(done ? " ✓" : " ○");
        }

        return message.toString();
    }
}
```

**출력**:
```
TODO: Java 공부
TODO: [STUDY] ✓
TODO: 프로젝트 마무리 [WORK] ○
```

</details>

---

### 연습 8: String vs StringBuilder 성능 비교

**목표**: String 연결과 StringBuilder의 성능 차이를 직접 측정하고 비교하세요.

**요구사항**:
- 두 가지 방법으로 1000개의 숫자를 문자열로 연결
- 실행 시간 측정 및 비교
- 메모리 사용량 비교 (선택 사항)

```java
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
```

**예상 출력**:
```
=== 방법 A: String 연결 ===
소요 시간: 45ms
결과 길이: 3893

=== 방법 B: StringBuilder ===
소요 시간: 0ms
결과 길이: 3893

=== 성능 비교 ===
StringBuilder가 약 500.0배 빠릅니다.
시간 복잡도: String O(n²) vs StringBuilder O(n)
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class PerformanceComparison {
    public static void main(String[] args) {
        int iterations = 1000;

        // 방법 A: String 연결 (비효율적)
        long startA = System.nanoTime();
        String resultA = "";
        for (int i = 0; i < iterations; i++) {
            resultA += i + ",";
        }
        long endA = System.nanoTime();
        long timeA = (endA - startA) / 1_000_000; // 밀리초로 변환

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
```

**출력 예시**:
```
=== 방법 A: String 연결 ===
소요 시간: 45ms
결과 길이: 3893

=== 방법 B: StringBuilder ===
소요 시간: 0ms
결과 길이: 3893

=== 성능 비교 ===
StringBuilder가 약 500.0배 빠릅니다.
시간 복잡도: String O(n²) vs StringBuilder O(n)
```

**결론**:
- String은 불변이므로 매번 새 객체 생성
- StringBuilder는 가변이므로 기존 객체 수정
- 반복문 안에서 문자열 연결은 반드시 StringBuilder 사용

</details>

---

### Part 2: 파일 저장/로드 연습

### 연습 9: TODO 목록을 CSV 파일로 저장

**목표**: BufferedWriter를 사용하여 TODO 목록을 CSV 파일로 저장하는 프로그램을 작성하세요.

**요구사항**:
- try-with-resources 사용
- 모든 TODO를 CSV 형식으로 변환
- 파일 저장 성공 메시지 출력
- IOException 예외 처리

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileSaver {
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
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "Java 공부", 3, false));
        todos.add(new Todo(2, "운동하기", 1, true));
        todos.add(new Todo(3, "독서", 2, false));

        String filename = "todos.csv";

        // TODO: try-with-resources를 사용하여 BufferedWriter 생성

        // TODO: 각 TODO를 CSV 형식으로 변환하여 파일에 쓰기
        // 형식: id,title,hours,done

        // TODO: IOException 예외 처리

        // TODO: 저장 성공 메시지 출력
    }
}
```

**예상 출력**:
```
파일 저장 완료: todos.csv (3건)
```

**파일 내용 (todos.csv)**:
```
1,Java 공부,3,false
2,운동하기,1,true
3,독서,2,false
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileSaver {
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
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1, "Java 공부", 3, false));
        todos.add(new Todo(2, "운동하기", 1, true));
        todos.add(new Todo(3, "독서", 2, false));

        String filename = "todos.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Todo todo : todos) {
                String line = todo.id + "," + todo.title + "," + todo.hours + "," + todo.done;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("파일 저장 완료: " + filename + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}
```

**출력**:
```
파일 저장 완료: todos.csv (3건)
```

</details>

---

### 연습 10: CSV 파일에서 TODO 목록 로드

**목표**: BufferedReader를 사용하여 CSV 파일에서 TODO 목록을 읽어오는 프로그램을 작성하세요.

**요구사항**:
- 파일 존재 여부 확인
- try-with-resources 사용
- CSV 파싱하여 Todo 객체 생성
- 로드된 목록 출력

```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileLoader {
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

        @Override
        public String toString() {
            return String.format("[%d] %s (%dh) - %s",
                id, title, hours, done ? "완료" : "미완료");
        }
    }

    public static void main(String[] args) {
        String filename = "todos.csv";
        List<Todo> todos = new ArrayList<>();

        // TODO: File 객체를 생성하고 파일 존재 여부 확인

        // TODO: try-with-resources로 BufferedReader 생성

        // TODO: 파일을 한 줄씩 읽으면서 CSV 파싱
        // split()으로 분리 후 Todo 객체 생성하여 리스트에 추가

        // TODO: IOException 예외 처리

        // TODO: 로드된 TODO 목록 출력
    }
}
```

**예상 출력**:
```
파일 로드 완료: todos.csv (3건)
=== TODO 목록 ===
[1] Java 공부 (3h) - 미완료
[2] 운동하기 (1h) - 완료
[3] 독서 (2h) - 미완료
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoFileLoader {
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

        @Override
        public String toString() {
            return String.format("[%d] %s (%dh) - %s",
                id, title, hours, done ? "완료" : "미완료");
        }
    }

    public static void main(String[] args) {
        String filename = "todos.csv";
        List<Todo> todos = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                int hours = Integer.parseInt(parts[2]);
                boolean done = Boolean.parseBoolean(parts[3]);

                todos.add(new Todo(id, title, hours, done));
            }

            System.out.println("파일 로드 완료: " + filename + " (" + todos.size() + "건)");
            System.out.println("=== TODO 목록 ===");
            for (Todo todo : todos) {
                System.out.println(todo);
            }
        } catch (IOException e) {
            System.err.println("파일 로드 실패: " + e.getMessage());
        }
    }
}
```

**출력**:
```
파일 로드 완료: todos.csv (3건)
=== TODO 목록 ===
[1] Java 공부 (3h) - 미완료
[2] 운동하기 (1h) - 완료
[3] 독서 (2h) - 미완료
```

</details>

---

### 연습 11: TODO 앱에 파일 저장/로드 통합

**목표**: TODO 애플리케이션에 파일 저장/로드 기능을 통합하세요.

**요구사항**:
- 프로그램 시작 시 파일에서 자동 로드
- 프로그램 종료 시 파일에 자동 저장
- 저장/로드 메서드 분리
- 파일 없을 때 빈 리스트로 시작

```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
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

    private static final String FILENAME = "todos.csv";
    private static List<Todo> todos = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        // TODO: 프로그램 시작 시 파일에서 로드

        // TODO: 간단한 메뉴 시뮬레이션
        // 샘플 TODO 추가
        todos.add(new Todo(nextId++, "새로운 할일", 2, false));

        // TODO: 프로그램 종료 시 파일에 저장
    }

    private static void loadFromFile() {
        // TODO: 파일에서 TODO 목록을 로드하는 코드 작성
        // 파일이 없으면 빈 리스트로 시작
        // nextId를 가장 큰 id + 1로 설정
    }

    private static void saveToFile() {
        // TODO: TODO 목록을 파일에 저장하는 코드 작성
    }
}
```

**예상 출력** (첫 실행):
```
파일이 없습니다. 빈 리스트로 시작합니다.
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (1건)
```

**예상 출력** (두 번째 실행):
```
파일 로드 완료: todos.csv (1건)
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (2건)
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
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

    private static final String FILENAME = "todos.csv";
    private static List<Todo> todos = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        // 프로그램 시작 시 로드
        loadFromFile();

        // 샘플 TODO 추가
        todos.add(new Todo(nextId++, "새로운 할일", 2, false));
        System.out.println("TODO 추가됨: 새로운 할일");

        // 프로그램 종료 시 저장
        saveToFile();
    }

    private static void loadFromFile() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 빈 리스트로 시작합니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                int hours = Integer.parseInt(parts[2]);
                boolean done = Boolean.parseBoolean(parts[3]);

                todos.add(new Todo(id, title, hours, done));
                maxId = Math.max(maxId, id);
            }

            nextId = maxId + 1;
            System.out.println("파일 로드 완료: " + FILENAME + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 로드 실패: " + e.getMessage());
        }
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Todo todo : todos) {
                String line = todo.id + "," + todo.title + "," + todo.hours + "," + todo.done;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("파일 저장 완료: " + FILENAME + " (" + todos.size() + "건)");
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }
}
```

**출력** (첫 실행):
```
파일이 없습니다. 빈 리스트로 시작합니다.
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (1건)
```

**출력** (두 번째 실행):
```
파일 로드 완료: todos.csv (1건)
TODO 추가됨: 새로운 할일
파일 저장 완료: todos.csv (2건)
```

</details>

---


[← 이전: Step 10](step_10.md) | [목차](00.overview.md) | [다음: Step 12 →](step_12.md)