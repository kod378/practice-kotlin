# 🛠 토이 프로젝트: 멀티 모듈 백엔드와 JWT & SSE

이 프로젝트는 **패스트캠퍼스 시그니처 백엔드 Path 초격차 패키지 Online** 강의를 참고하여 작성되었습니다.

## 📝 프로젝트 주요 기능

- **멀티 모듈 설정**
- **JWT 설정**
- **SSE (Server-Sent-Events)**

---

### ⚙️ 멀티 모듈 설정

프로젝트는 총 4개의 모듈로 구성되어 있습니다:
1. **DB 모듈**
2. **Common 모듈**
3. **User-API 모듈**
4. **Admin-API 모듈**

배포 시 `user-api.jar`와 `admin-api.jar`를 각각 독립적으로 배포할 수 있습니다.  
자세한 내용은 각 모듈의 `build.gradle.kts` 파일을 참고하세요.

---

### 🔑 JWT 설정

토큰을 생성하고 검증하는 로직은 일반적인 방식과 비슷하므로, 여기서는 간단히 설명합니다. **User-API**와 **Admin-API**의 JWT 처리 방식에는 차이가 있습니다:

#### **User-API (Spring Security 미사용)**

**User-API**에서는 Spring Security를 사용하지 않고, 직접 구현한 **인터셉터**를 통해 JWT를 검증합니다.

- 인터셉터: `user-api.src.main.kotlin.org.delivery.api.interceptor.AuthorizationInterceptor`

#### **Admin-API (Spring Security 사용)**

**Admin-API**에서는 Spring Security의 **필터**를 통해 JWT를 검증합니다.

- 필터: `admin-api.src.main.kotlin.org.delivery.storeadmin.filter.JwtAuthFilter`

---

### 🔄 SSE (Server-Sent-Events)

**RabbitMQ**를 활용한 비동기 처리와 SSE 연결을 관리하며, `sseConnectionPool`을 통해 여러 클라이언트의 연결을 유지합니다.

- 커넥션풀: `admin-api.src.main.kotlin.org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection`
- MQ설정: `user-api.src.main.kotlin.org.delivery.api.config.rabbitmq.RabbitMqConfig`

---

그리고 vue 프레임워크로 프론트화면도 일단 구성해두었습니다.

Store화면: https://github.com/kod378/practice-kotlin-vue-admin

User화면: https://github.com/kod378/practice-kotlin-vue-user
