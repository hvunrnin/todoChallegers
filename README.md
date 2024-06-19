# 할 일을 도전적으로 해결하자, Todo Challengers

Todo-mate와 같이 오늘 할 일을 관리할 수 있는 서비스입니다. 챌린지를 등록해 목표 달성을 위해 해야할 일을 계획해보세요!
## Getting Started
### Prerequisites

- Java 17 설치
- IntelliJ 또는 Eclipse 설치
- H2 Database 1.4.200 버전 설치
  - `General error: "The write format 1 is smaller than the supported format 2
[2.0.206/5]" [50000-202] HY000/50000` 오류가 발생한다면, 다시 설치한 이후 ~/test.mv.db 파일을 삭제합니다.

### Installing

1. cmd에서 원하는 directory로 이동 후 아래의 명령어를 입력합니다.
```
  git clone https://github.com/RumosZin/todoChallegers.git
```
2. H2 콘솔을 실행합니다.
  - H2/bin 경로로 이동하고 Window 사용자는 `h2.bat`, Mac 사용자는 `chmod 755 h2.sh` `./h2.sh` 명령어를 통해 H2 콘솔을 실행합니다.
  - H2 콘솔의 JDBC URL에 최초 한 번 `jdbc:h2:~/test`를 실행합니다.
  - `C:\Users\{User} 경로에 `test.mv.db` 파일이 생겼는지 확인합니다.
  - H2 콘솔 재접속 시 JDBC URL을 `jdbc:h2:tcp://localhost/~/test`로 변경한 뒤 접근합니다.

3. 기본으로 제공하는 MEMBER table을 삭제합니다.
```
drop table MEMBER;
```

4. `todoChallengers\sql\ddl.sql`에 있는 create table SQL문을 이용해 **MEMBER / GOALS / TODOS / CHALLENGE** table을 차례로 생성합니다.

  
## Running the Todo Challengers

H2 콘솔이 실행 중이고, H2 Database에 MEMBER / GOALS / TODOS / CHALLENGE table이 생성된 상태여야 합니다.

1. IntelliJ 혹은 Eclipse IDE로 Todo Challengers 프로젝트를 open 합니다.
2. LoginSpringApplication.java를 실행합니다.
3. `localhost:8080`으로 접속합니다.

![image](https://github.com/RumosZin/todoChallegers/assets/81238093/2d01dbe1-9f52-4393-98c3-fef7d492ef63)

## Features of the Todo Challengers
### 할 일 한눈에 보기

- Todo-mate와 같이 왼쪽에는 캘린더, 오른쪽에는 Feed로 오늘 할 일을 한눈에 확인할 수 있습니다.
- 왼쪽 위에 있는 D-day로 챌린지에 도전한 목록을 확인할 수 있습니다.
- 할 일을 내일로 미루거나, 수정할 수 있습니다.

![image](https://github.com/RumosZin/todoChallegers/assets/81238093/b2fdcfa1-6836-4021-879a-50b7c97bd8d3)

### 목표, 간편 입력 설정하기

- Feed에 나타나는 목표들을 등록할 수 있습니다.
- 간편 입력으로 매일/매주/매월 주기적으로 반복하는 일들을 등록할 수 있습니다.

![image](https://github.com/RumosZin/todoChallegers/assets/81238093/53a0dd3b-926e-479f-8bad-5daa20a3b804)

### 챌린지 설정하기

- 주요한 목표에 대해서 챌린지를 설정할 수 있습니다.
- '할 일 한눈에 보기'에서 챌린지를 위해 남은 날짜를 확인할 수 있습니다.

![image](https://github.com/RumosZin/todoChallegers/assets/81238093/413833fb-6ad7-45ff-8a87-80b1b2e18bfe)
