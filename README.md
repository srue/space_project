# 앤스페이스 백엔드 기술과제

## 사용기술
- Spring Boot
- Spring Batch
- Spring Schedular

## 서버설정
 - 자바설치
 - 시간 타임존 한국 시간으로 변경
 - 빌드 스크립트 작성
 - alias 추가
 	* space_build : git pull & build & deploy
 	* space_log : tail -f log

## 설명
- Batch Server 가 Spring Boot를 사용하다 하여 Spring Boot 선택
- 하나의 잡으로 간단하게 개발하기 위하여 Spring Schedular를 사용
- RestTemplate 사용하여 API 호출
- JSON 으로 변환하여 result 값 가져옴.
- 기본 12씩 가져오는걸 확인 
- 다른 validation 생략
- DB는 jpa 로 구현
