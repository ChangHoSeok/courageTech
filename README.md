# CourageTech

## 개요
CourageTech는 WebApplication으로 전자정부표준 프레임워크 및 개인이 개발한 공통모듈(이하 'courage-common')의 기반환경에서 개발된 프로그램 입니다.

기업용 또는 업무용 홈페이지에 주로 사용되는 기능들을(또는 기반 환경) 제공하고 있습니다. <br />
제공되는 주요 기능으로는 사용자관리, 권한관리, 부서관리, 메뉴관리, 게시판 관리 기능 등 다양한 관리기능을 제공하며, 상세한 내용은 아래 기능목록에서 확인해보시기 바랍니다.

## 기능목록
- 회원가입 및 관리
- 마이페이지
- 공통 게시판 관리
- 공통 게시판 권한관리
- 프로그램  관리
- 메뉴 관리
- 권한 관리
- 부서 관리
- 사용자 관리
- 사용자 권한 관리
- 공통코드 관리
- Push 메시지
- 로그인 현황

## 개발환경

- Java 6
- Tomcat 6
- Maven 3.2.5
- SQLite
- 표준프레임워크 (egovframework) Ver. 2.6.0

## 프로젝트 구성
base-egovframework
- 표준프레임워크의 공통모듈로 구성된 프로젝트 (2.6.0)
- 제공되는 공통 모듈을 커스텀하여 재구성
- 공통모듈 소스로 구성되어 있으며 Controller는 추상화(Abstract) 클래스로 제공

courage-common
- 공통으로 사용되는 유틸리티로 구성된 프로젝트
- 표준프레임워크와 종속관계
- 커스텀테그, 파일저장관리, 

courage-tech
- WebApplication의 실 구현체가 존재하는 프로젝트
- base-egovframework, courage-common와 종속관계

## 개발환경 설정
- src 하위에 존재하는 3개의 프로젝트를 maven 프로젝트로 import
- src/main/resource/egovframework/egovProps/global.properties의 DB URL 변경 <br />
Globals.Url 속성값을 SQLite db 파일위치로 변경


- src/main/resource/kr/pe/courage/courageProp/system.properties의 설정 변경 <br />
주석의 설명을 보고 환경에 맞도록 설정