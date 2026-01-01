package com.hospital.booking.dto;

public class JwtResponse {
    private String token;
    private String email;
    private String role;
    private Long userId;
    private Long profileId;
    private Long id; // Keep for backward compatibility
    private String name;

    public JwtResponse(String token, String email, String role, Long userId, Long profileId, String name) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.userId = userId;
        this.profileId = profileId;
        this.id = profileId != null ? profileId : userId;
        this.name = name;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
