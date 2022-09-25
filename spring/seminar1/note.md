## SQL 기본 문법(mysql 기준)

``` sql
create table operating_system
( # snake
    id      bigint primary key auto_increment,
    # id는 숫자(bigint), 식별자(primary key), 알아서 id값 증가
    os_name varchar(31) not null,
    # varchar() : 문자열 크기 유동적
    # varchar(31) : 32글자부터는 깨짐
    # null이 될 수 없음
    # default 값 넣어줄 수도 있음
    price   bigint      not null default 0,
    `desc`  varchar(31) not null
    # desc는 예약어
);

create table survey_response
(
    id                  bigint primary key auto_increment,
    operating_system_id bigint      not null,
    # RDB이므로 id만 들고있어도 나중에 가져올 수 있음!
    spring_exp          int         not null,
    rdb_exp             int         not null,
    programming_exp     int         not null,
    major               varchar(31) not null,
    grade               varchar(31) not null,
    `timestamp`         datetime    not null,
    # timestamp은 시간의 어떤 지점, datetime은 locale 등 들고 있음
    # 수정할땐 보통 datetime
    backend_reason      varchar(100),
    waffle_reason       varchar(100),
    something_to_say    varchar(100)
);

create table users
(
    id     bigint primary key auto_increment,
    `name` varchar(255) not null,
    age    bigint       not null,
    gender varchar(31)  not null
    # lectures: List<User> 같은건 안됨!
);

create table lecture
(
    instructor_id bigint,
    user_id bigint
);

# 테이블 삭제
drop table operating_system;

# 테이블 업데이트
alter table operating_system
    modify column `desc` varchar(233) null;

# use seminar; # 데이터베이스 선택 가능
# 데이터 추가
INSERT INTO operating_system (os_name, price, `desc`)
VALUES ('windows', 10900, '213');
INSERT INTO operating_system (os_name, price)
VALUES ('windows', 10900);

# 에러. 원래 null이던 항목은 어떻게 함?
# alter table operating_system modify column `desc` varchar(233) not null;
# 디폴트값 명시해주면? 에러(원래 null이던 항목은?)
# alter table operating_system modify column `desc` varchar(233) not null default '';
# 항목 null 없게 수정 후
alter table operating_system
    modify column `desc` varchar(233) not null default '';
INSERT INTO operating_system (os_name, price)
VALUES ('windows', 10900);
# 가능
# 위에서 default ''가 없었다면 에러남!

# 특정 항목 삭제 : DELETE FROM 테이블이름 where 조건
DELETE
FROM operating_system
where id = 1;

# 업데이트 : UPDATE 테이블이름 set 바꿀거 where 조건
UPDATE operating_system
set os_name = 'asd'
where price = 2234;
```