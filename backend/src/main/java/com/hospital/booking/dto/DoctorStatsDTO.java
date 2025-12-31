package com.hospital.booking.dto;

public class DoctorStatsDTO {
    private long totalAppointments;
    private long pendingAppointments;
    private long approvedAppointments;
    private long cancelledAppointments;
    private long uniquePatients;

    public DoctorStatsDTO(long totalAppointments, long pendingAppointments, long approvedAppointments, long cancelledAppointments, long uniquePatients) {
        this.totalAppointments = totalAppointments;
        this.pendingAppointments = pendingAppointments;
        this.approvedAppointments = approvedAppointments;
        this.cancelledAppointments = cancelledAppointments;
        this.uniquePatients = uniquePatients;
    }

    public long getTotalAppointments() { return totalAppointments; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }
    public long getPendingAppointments() { return pendingAppointments; }
    public void setPendingAppointments(long pendingAppointments) { this.pendingAppointments = pendingAppointments; }
    public long getApprovedAppointments() { return approvedAppointments; }
    public void setApprovedAppointments(long approvedAppointments) { this.approvedAppointments = approvedAppointments; }
    public long getCancelledAppointments() { return cancelledAppointments; }
    public void setCancelledAppointments(long cancelledAppointments) { this.cancelledAppointments = cancelledAppointments; }
    public long getUniquePatients() { return uniquePatients; }
    public void setUniquePatients(long uniquePatients) { this.uniquePatients = uniquePatients; }
}
