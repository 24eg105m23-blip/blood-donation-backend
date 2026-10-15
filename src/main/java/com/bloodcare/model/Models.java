package com.bloodcare.model;

public final class Models {
    private Models() {}
    public enum Role { DONOR, REQUESTER, ADMIN }
    public enum BloodGroup { A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG, O_POS, O_NEG }
    public enum Urgency { NORMAL, URGENT, EMERGENCY }
    public enum RequestStatus { PENDING, APPROVED, REJECTED, FULFILLED, CANCELLED }
}
