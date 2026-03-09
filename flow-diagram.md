
##  UsernamePasswordAuthenticationFilter
```
            Client
              │
              │  POST /login
              │  (username + password)
              ▼
      UsernamePasswordAuthenticationFilter
              │
              │  Extract credentials
              ▼
      UsernamePasswordAuthenticationToken
              │
              ▼
      AuthenticationManager
              │
              ▼
      AuthenticationProvider
              │
              ▼
      UserDetailsService
              │
              ▼
            Database

```


## Authentication Flow (Login → JWT Generation)
-  ```
   ┌──────────────────────┐
   │        Client        │
   │  (React / Postman)   │
   └──────────┬───────────┘
              │
              │ POST /auth/login
              │ username + password
              ▼
   ┌──────────────────────────────┐
   │        Auth Controller       │
   │   (/auth/login endpoint)     │
   └──────────┬───────────────────┘
              │
              │ call
              ▼
   ┌──────────────────────────────┐
   │     AuthenticationManager    │
   └──────────┬───────────────────┘
              │
              │ uses
              ▼
   ┌──────────────────────────────┐
   │       UserDetailsService     │
   │   loadUserByUsername()       │
   └──────────┬───────────────────┘
              │
              │ fetch user
              ▼
   ┌──────────────────────────────┐
   │          Database            │
   │  (User, Password, Roles)     │
   └──────────┬───────────────────┘
              │
              │ verify password
              ▼
   ┌──────────────────────────────┐
   │ PasswordEncoder.matches()    │
   └──────────┬───────────────────┘
              │
              │ if valid
              ▼
   ┌──────────────────────────────┐
   │          JwtService          │
   │       generateToken()        │
   └──────────┬───────────────────┘
              │
              │ return JWT
              ▼
   ┌──────────────────────────────┐
   │           Client             │
   │  Stores JWT (localStorage)   │
   └──────────────────────────────┘
   
   ┌──────────────────────┐
   │        Client        │
   │  (React / Postman)   │
   └──────────┬───────────┘
              │
              │ POST /auth/login
              │ username + password
              ▼
   ┌──────────────────────────────┐
   │        Auth Controller       │
   │   (/auth/login endpoint)     │
   └──────────┬───────────────────┘
              │
              │ call
              ▼
   ┌──────────────────────────────┐
   │     AuthenticationManager    │
   └──────────┬───────────────────┘
              │
              │ uses
              ▼
   ┌──────────────────────────────┐
   │       UserDetailsService     │
   │   loadUserByUsername()       │
   └──────────┬───────────────────┘
              │
              │ fetch user
              ▼
   ┌──────────────────────────────┐
   │          Database            │
   │  (User, Password, Roles)     │
   └──────────┬───────────────────┘
              │
              │ verify password
              ▼
   ┌──────────────────────────────┐
   │ PasswordEncoder.matches()    │
   └──────────┬───────────────────┘
              │
              │ if valid
              ▼
   ┌──────────────────────────────┐
   │          JwtService          │
   │       generateToken()        │
   └──────────┬───────────────────┘
              │
              │ return JWT
              ▼
   ┌──────────────────────────────┐
   │           Client             │
   │  Stores JWT (localStorage)   │
   └──────────────────────────────┘
   ```

## Authorization Flow (Subsequent Requests)
```
   ┌──────────────────────┐
   │        Client        │
   │  Sends Request + JWT │
   └──────────┬───────────┘
              │
              │ Authorization: Bearer token
              ▼
   ┌──────────────────────────────┐
   │      Spring Security         │
   │        Filter Chain          │
   └──────────┬───────────────────┘
              │
              ▼
   ┌──────────────────────────────┐
   │         JWT Filter           │
   │ (OncePerRequestFilter)       │
   └──────────┬───────────────────┘
              │
              │ 1. Read Authorization Header
              │
              ▼
   ┌──────────────────────────────┐
   │ Extract Token from Header    │
   │ "Bearer eyJhbGciOi..."       │
   └──────────┬───────────────────┘
              │
              │ 2. Validate Token
              ▼
   ┌──────────────────────────────┐
   │         JwtService           │
   │  - extractUsername()         │
   │  - validateToken()           │
   └──────────┬───────────────────┘
              │
              │ 3. Load user
              ▼
   ┌──────────────────────────────┐
   │       UserDetailsService     │
   │     loadUserByUsername()     │
   └──────────┬───────────────────┘
              │
              │
              ▼
   ┌──────────────────────────────┐
   │ Create Authentication Object │
   │ UsernamePasswordAuthToken    │
   └──────────┬───────────────────┘
              │
              │
              ▼
   ┌──────────────────────────────┐
   │ SecurityContextHolder        │
   │ setAuthentication()          │
   └──────────┬───────────────────┘
              │
              │
              ▼
   ┌──────────────────────────────┐
   │ Spring Security Authorization│
   │ Check Roles / Permissions    │
   └──────────┬───────────────────┘
              │
              ▼
   ┌──────────────────────────────┐
   │         Controller           │
   │      /api/test executed      │
   └──────────────────────────────┘

```