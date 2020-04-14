package com.service.rent.RentServiceServer.entity.enums;

public enum OverviewStatus {
    /**
     * by renter
     */
    REQUESTED,

    /**
     * by owner
     */
    AGREED,

    /**
     * Finished
     */
    CLOSED,

    /**
     * By renter
     */
    CANCELED,

    /**
     * By owner
     */
    REJECTED;
}
