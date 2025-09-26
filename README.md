# 👩🏻‍💻 Spring Plus 과제
> **개발 기간 : 2025.09.10 ~ 2025.09.23**

---

## 💁‍♀ 프로젝트 소개
> - QueryDSL을 활용한 동적 쿼리 작성의 패턴과 기술을 활용합니다. <br>
> - JPA를 사용하여 쿼리를 최적화함으로써 속도를 개선하고 리소스를 효율적으로 사용하는 기술을 활용합니다.

---

## 🛠️ 기술 스택
- **언어:** Java
- **프레임워크:** Spring Boot, Spring Data JPA, QueryDSL
- **데이터베이스:** MySQL, H2
- **인프라:** AWS EC2, AWS RDS

---

## ✅ 필수 기능

### Lv 1-1. 코드 개선 퀴드 - @Transactional의 이해
- 할일 저장 기능 API 호출 시 에러 해결

### Lv 1-2. 코드 추가 퀴즈 - JWT의 이해
- User 정보 nickname 컬럼 추가 후 API 수정

### Lv 1-3. 코드 개선 퀴즈 -  JPA의 이해
- JPQL을 사용한 할일 검색 기능 구현
 
### Lv 1-4. 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해
- 실패한 테스트 코드 수정

### Lv 1-5. 코드 개선 퀴즈 - AOP의 이해
- 잘못 동작하는 AOP 코드 수정

### Lv 2-6. JPA Cascade
- JPA cascade를 활용한 할일 생성시 담당자 자동 등록 기능 구현

### Lv 2-7. N+1
- getComments() API 호출 시 N+1문제 발생 이슈 해결

### Lv 2-8. QueryDSL
- JPQL로 작성된 findByWithUser 메서드 QueryDSL로 변경(N+1 문제 해결 적용)

### Lv 2-9. Spring Security
- Spring Security를 도입하여 토큰 인증 방식 구현

---

## 💡 도전 기능

### Lv 3-10. QueryDSL 을 사용하여 검색 기능 만들기
- Projections를 활용한 일정 검색 기능 구현(쿼리 최적화)
- 페이징 처리

### Lv 3-11. Transaction 심화
- @Transaction의 옵션을 활용한 매니저 등록 요청 로그 구현(등록 실패해도 로그 저장)

### Lv 3-12. AWS 활용
#### Lv 3-12-1. EC2
- AWS EC2를 활용하여 health check API 구현 </br>
  <img width="1251" height="413" alt="Image" src="https://github.com/user-attachments/assets/fb5e5884-7c3f-4b48-9f4c-fad17bd98f6a" /> </br>
  <img width="922" height="379" alt="Image" src="https://github.com/user-attachments/assets/8844fbd0-36d7-40ae-b31d-7b6d8f2f68f3" /> </br>

#### Lv 3-12-2. RDS
- RDS애 데이터베이스를 구축하고 EC2 어플리케이션 연결 </br>
  <img width="691" height="302" alt="Image" src="https://github.com/user-attachments/assets/204faf48-1691-4339-8cbe-e482859cf622" /> </br>
  <img width="1094" height="432" alt="Image" src="https://github.com/user-attachments/assets/d6cfa29b-9bc4-4f9a-b5eb-37515a8911ea" />

### Lv 3-13. 대용량 데이터 처리
- JDBC Bulk Insert를 활용한 대용량 데이터 처리 구현

#### 🔎 조회 성능 개선 과정
- **유저 검색 속도 개선**을 위해 다양한 인덱스 전략을 적용하고, 실행 시간을 비교하였습니다.

#### ⚡️ 성능 비교 결과
| 단계 | 데이터 조건             | 적용 방식 | 실행 계획 (type) | 실행 시간 (ms) |
|------|-------------------------|-----------|------------------|----------------|
| 1    | 500만 건 (초기 상태)    | 인덱스 미적용 (기본) | ALL (풀 스캔)   | 1203 |
| 2    | 500만 건 (초기 상태)    | 기본 인덱스 적용     | ref             | 63   |
| 3    | 500만 건 (100만 건 중복 update 후) | 인덱스 미적용 (기본) | ALL (풀 스캔)   | 3254 |
| 4    | 500만 건 (100만 건 중복 update 후) | 기본 인덱스 적용     | ref             | 2385 |
| 5    | 500만 건 (100만 건 중복 update 후) | 커버링 인덱스 적용 (id, email, nickname) | ref (커버링) | 1896 |

---

### 📌 분석

- **초기 500만 건 기준**
  - 인덱스 미적용 시 풀스캔으로 1203ms 소요
  - 인덱스 적용 시 `ref` 방식으로 바뀌며 63ms까지 대폭 개선
- **중복 데이터 update 후**
  - 인덱스 미적용 시 3254ms
  - 인덱스 적용 시 2385ms → 여전히 성능 개선 효과 존재
  - 커버링 인덱스 적용 시 **1896ms**로 최종 개선
  - 최종적으로 **"약 41.7%"** 조회 성능 개선

---

## 📃 대용량 데이터 처리 조회 성능 개선 회고
- 링크: [문서 확인](https://scarlet-lime-d5d.notion.site/276883b537e28097950ac1878fb2919e?source=copy_link)

