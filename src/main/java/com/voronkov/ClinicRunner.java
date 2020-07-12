package com.voronkov;

import com.voronkov.exceptions.NotFoundException;

/**
 * ClinicRunner
 * @author Sanek
 * @since 05.07.2020
 */
public class ClinicRunner {

    public static void main(String[] arg) throws NotFoundException {
        final Clinic clinic = new Clinic();

        clinic.getOperation();
    }
}
