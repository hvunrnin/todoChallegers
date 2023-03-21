create table MEMBER
(
    USER_ID VARCHAR(255) NOT NULL,
    USER_PW VARCHAR(256) DEFAULT NULL,
    USER_RPW VARCHAR(256) DEFAULT NULL,
    USER_NAME VARCHAR(255) NOT NULL,
    USER_MESSAGE VARCHAR(255) DEFAULT NULL,
    USER_AUTH VARCHAR(255) NOT NULL,
    APPEND_DATE DATETIME DEFAULT NULL,
    UPDATE_DATE DATETIME DEFAULT NULL,
    PRIMARY KEY (USER_ID)
)

--GOALS를 먼저 삽입하고 TODOS삽입

create table GOALS(
ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
USER_ID VARCHAR(255) NOT NULL,
ORDER_NUM INT NOT NULL,
CATEGORY VARCHAR(10) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID)
)

create table TODOS(
ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
USER_ID VARCHAR(15) NOT NULL,
GOAL_ID INT NOT NULL,
DATE DATE NOT NULL,
ORDER_NUM INT NOT NULL,
CONTENT VARCHAR(15) NOT NULL,
START_DATE DATE,
END_DATE DATE,
IS_REPEAT_MON INT NOT NULL DEFAULT 0,
IS_REPEAT_TUE INT NOT NULL DEFAULT 0,
IS_REPEAT_WED INT NOT NULL DEFAULT 0,
IS_REPEAT_THU INT NOT NULL DEFAULT 0,
IS_REPEAT_FRI INT NOT NULL DEFAULT 0,
IS_REPEAT_SAT INT NOT NULL DEFAULT 0,
IS_REPEAT_SUN INT NOT NULL DEFAULT 0,
IS_CHECKED INT NOT NULL DEFAULT 0,
REPEAT_MONTHLY VARCHAR(90) DEFAULT NULL,

PRIMARY KEY (ID),
FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID),
FOREIGN KEY (GOAL_ID) REFERENCES GOALS (ID)
)

create table CHALLENGE
(
USER_ID VARCHAR(15) NOT NULL,
ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
TITLE VARCHAR(30),
START_DATE DATE,
END_DATE DATE,
MEMO VARCHAR(100),
PRIMARY KEY (TITLE)
);

insert into goals values (0,'cindy',0,''),(1,'cindy',1,'할일')