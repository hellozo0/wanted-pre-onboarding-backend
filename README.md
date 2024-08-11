# wanted-pre-onboarding-backend

## 서비스 개요
- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

## 필수 기술요건
- ORM 사용하여 구현.
- RDBMS 사용 (SQLite, PostgreSQL,MySql 등).


<details>
<summary> 요구사항 및 개발 시 참조사항</summary>
<div markdown="1">

## 요구사항

1. 채용공고를 등록합니다.
2. 채용공고를 수정합니다.
3. 채용공고를 삭제합니다.
4. 채용공고 목록을 가져옵니다.
5. 채용 상세 페이지를 가져옵니다.
6. 사용자는 채용공고에 지원합니다(선택사항 및 가산점요소).

## 개발 시 참조하세요!

- 위 예시 데이터는 구현의 편의를 위해 제공되는 정보이며, 요구사항(의도)을 만족시킨다면 다른 형태의 요청 및 리스폰스를 사용하여도 좋습니다.
- 필요한 모델: 회사, 사용자, 채용공고, 지원내역(선택사항)
  (이외 추가 모델정의는 자유입니다.)
- 위 제공된 필드명은 예시이며, 임의로 생성 가능합니다.
- 회사, 사용자 등록 절차는 생략합니다.
  (DB에 임의로 생성하여 진행)
- 로그인 등 사용자 인증절차(토큰 등)는 생략합니다.
- Frontend 요소(html, css, js 등)는 개발 범위에 제외됩니다.
  (구현시 불이익은 없지만, 평가에 이점 또한 없습니다.)
- 명시되지 않은 조건또한 자유롭게 개발 가능합니다.

</div>
</details>

## 💻 API 명세서

[API 명세서 보러가기](https://hellozo0.notion.site/Wanted-API-22872ce2888d4de9865c39adb50c37bd?pvs=4)

## 💻 기술 스택

- Java 17
- Spring Boot 3.3.2
- Spring Boot Data JPA
- MySQL
- Git

<details>
<summary> 🌞 application.yml  --->  git에는 올리지 않았습니다!🌞</summary>
<div markdown="1">

```
spring:
servlet:
multipart:
maxFileSize: 10MB # 파일 하나의 최대 크기
maxRequestSize: 10MB  # 한 번에 최대 업로드 가능 용량

datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/wanted?characterEncoding=UTF-8&serverTimezone=Asia/Seoul
username: # 각자것으로 채우기
password: # 각자것으로 채우기

jpa:
show-sql: true
hibernate:
ddl-auto: update 
properties:
hibernate:
globally_quoted_identifiers: true
show_sql: true
format_sql: true

springdoc:
packages-to-scan: com.wanted_pre
default-consumes-media-type: application/json;charset=UTF-8
swagger-ui:
tags-sorter: alpha
operations-sorter: alpha
api-docs:
path: /api-docs/json
groups:
enabled: true
cache:
disabled: true

```
</div>
</details>


## 🔨 Project Structure
```
├─📁 main
│  ├─📁 java
│  │  └─📁 com
│  │      └─📁 wanted_pre
│  │          └─📁 wanted_pre_onboarding_backend
│  │              ├─📁 common
│  │              ├─📁 config
│  │              ├─📁 controller
│  │              │  └─📁 apply
│  │              │      └─📁 dto
│  │              │  └─📁 enterprise   
│  │              │  └─📁 recruitment  
│  │              │      └─📁 dto
│  │              ├─📁 domain
│  │              │  └─📁 apply
│  │              │  └─📁 enterprise   
│  │              │  └─📁 recruitment
│  │              │  └─📁 user  
│  │              ├─📁 repository
│  │              ├─📁 service
│  │              │  └─📁 apply
│  │              │  └─📁 enterprise   
│  │              │  └─📁 recruitment
│  │              │  └─📁 user 
│  └─📁 resources
│      ├─static
│      └─templates
└─📁 test
```

## 🛠️ ERD
![스크린샷 2024-08-11 오후 11.32.29.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fyp%2Fcz9835td3tn151zs4j6zxj6m0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_i4sLvH%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-08-11%20%EC%98%A4%ED%9B%84%2011.32.29.png)

## 🌳 Branch

🌱 git branch 전략

`main branch` : 배포 단위 branch

`feat branch` : 주요 개발 branch, main merge 전 거치는 branch

`docs branch`: 문서 수정 branch

- 할 일 issue 등록 후 issue 번호로 branch 생성 후 작업
    - ex) feat/#`issue num`
- 해당 branch 작업 완료 후 PR 보내기
    - 항상 local에서 충돌 해결 후 → remote에 올리기
    - reviewer에 서로 tag후 code-review
    - review반영 후, 본인이 merge.

<br>

## 🌱 Commit Convention
### Type

`[feat]` : 새로운 기능이 추가됐어요.

`[update]`: 기능이 업데이트 됐어요.

`[fix]`: 버그가 수정됐어요.

`[refactor]`: 버그 수정이나 기능 추가가 없는 코드 변경 ( 코드 구조 변경 등의 리팩토링 )

`[test]`: 테스트 추가 또는 이전 테스트 수정

`[dosc]`: README, wiki 문서를 수정했어요.

`[chore]`: 기타 사항이에요. (주석 추가 등등)

<br>
