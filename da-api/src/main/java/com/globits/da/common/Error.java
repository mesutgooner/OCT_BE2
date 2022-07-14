package com.globits.da.common;

public enum Error {
    CODE_IS_NULL("CODE_E01","Code is mandatory"),
    CODE_HAS_SPACE("CODE_E02","Space in code is not allowed"),
    CODE_LENGTH_INVALID("CODE_E03","Code only accepts more than 6 and less than 10 characters"),
    CODE_EXISTED("CODE_E04","Code existed"),
    NAME_IS_NULL("NAME_E01","Name is mandatory"),
    EMAIL_IS_NULL("EMAIL_E01", "Email is required"),
    EMAIL_INVALID_FORMAT("EMAIL_E02", "Not an email"),
    PHONE_IS_NULL("PHONE_E01", "Phone is mandatory"),
    PHONE_LENGTH_INVALID("PHONE_E02", "Phone length should be more than 9 and less than 11"),
    PHONE_NAN("PHONE_E03","Phone only accepts numbers"),
    AGE_IS_NULL("AGE_E01","Age is mandatory"),
    AGE_IS_INVALID("AGE_E02", "Age only accepts natural numbers"),
    ID_IS_NULL("ID_E01", "ID is mandatory"),
    WARD_IS_NULL("WARD_E01", "Ward is mandatory"),
    WARD_NOT_EXIST("WARD_E02", "Ward does not exist"),
    WARD_DISTRICT_INVALID("WARD_E03", "Ward does not belong to district"),
    DISTRICT_IS_NULL("DISTRICT_E01", "District is mandatory"),
    DISTRICT_NOT_EXIST("DISTRICT_E02", "District does not exist"),
    DISTRICT_CITY_INVALID("DISTRICT_E03", "District does not belong to province"),
    CITY_IS_NULL("CITY_E01", "City is mandatory"),
    PROVINCE_NOT_EXIST("CITY_E02", "Province does not exist"),
    CERTIFICATE_IS_NULL("CERT_E01", "Certificate is required"),
    CERTIFICATE_NOT_EXIST("CERT_E02", "Certificate does not exist"),
    EMPLOYEE_NOT_EXIST("EMPL_E01", "Employee does not exist"),
    CERTIFICATE_NUMBER_EXCEED("EMPL_E02", "Employee has 3 effective certificates"),
    CERTIFICATE_IS_EFFECTIVE("EMPL_E03", "Certificate is still effective"),
    ;

    private final String code;
    private final String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
