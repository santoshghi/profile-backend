## User Hits the Login Endpoint
## Request:
- POST http://localhost:8081/api/auth/login
  {
  "username": "admin",
  "password": "admin123"
  }
- Spring sees path: /auth/login (context path /api is ignored in Spring Security path matching)

## Spring Security Configuration Check
- SecurityFilterChain is applied
- /auth/** is permitted → request allowed without token
- Request goes to your AuthController

## Controller Calls AuthService
- String token = authService.authenticate(loginRequest);
- AuthService uses AuthenticationManager
- Wraps username/password in a UsernamePasswordAuthenticationToken

## AuthenticationManager Starts Authentication
- Delegates to DaoAuthenticationProvider
- Calls your CustomUserDetailsService.loadUserByUsername(username)
# Cases:
- User exists: returns UserDetails with username, password (hashed), roles
- User does not exist: throws UsernameNotFoundException → 401 Unauthorized returned

## Authentication Successful
- Authentication object contains:
  principal → username
  credentials → null
  authorities → appUser roles (e.g., ROLE_ADMIN)
  authenticated → true
- AuthService generates JWT token using JwtService

## JWT Token Generation
- Token payload includes:
  {
  "sub": "admin",
  "roles": ["ROLE_ADMIN"],
  "iat": 1710000000,
  "exp": 1710086400
  }
- Token signed with secret key
- Returned in response:
  {
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }

### When Next API Trigger
## JWT Filter Intercepts Requests
- Filter checks Authorization header
- Skips login endpoint (/auth/**)
- Extracts token and validates signature and expiration
# Cases:
- Valid token:
    - Extract username from JWT
    - Load appUser details from DB
    - Create Authentication object and store in SecurityContextHolder → request allowed
- Expired/invalid token:
    - Return 401 Unauthorized
- No token:
    - Return 403 Forbidden for protected endpoints

## Controller Execution
- If authentication successful and roles are correct: request executes
# Example role-based endpoint:
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/project")
  public ResponseEntity<> getPersonalProject() {
  return null;
  }
- If appUser lacks role → 403 Forbidden

