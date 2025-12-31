package com.hospital.booking.dto;

public class AdminStatsDTO {
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;
    private long pendingAppointments;
    private long approvedAppointments;

    public AdminStatsDTO(long totalDoctors, long totalPatients, long totalAppointments, long pendingAppointments, long approvedAppointments) {
        this.totalDoctors = totalDoctors;
        this.totalPatients = totalPatients;
        this.totalAppointments = totalAppointments;
        this.pendingAppointments = pendingAppointments;
        this.approvedAppointments = approvedAppointments;
    }

    public long getTotalDoctors() { return totalDoctors; }
    public void setTotalDoctors(long totalDoctors) { this.totalDoctors = totalDoctors; }
    public long getTotalPatients() { return totalPatients; }
    public void setTotalPatients(long totalPatients) { this.totalPatients = totalPatients; }
    public long getTotalAppointments() { return totalAppointments; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }
    public long getPendingAppointments() { return pendingAppointments; }
    public void setPendingAppointments(long pendingAppointments) { this.pendingAppointments = pendingAppointments; }
    public long getApprovedAppointments() { return approvedAppointments; }
    public void setApprovedAppointments(long approvedAppointments) { this.approvedAppointments = approvedAppointments; }
}
