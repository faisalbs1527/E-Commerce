package com.example.ecommerce.model.authentication

data class CustomerInfo(
    val AllowCustomersToRemoveAssociations: Boolean,
    val AllowCustomersToSetTimeZone: Boolean,
    val AllowUsersToChangeUsernames: Boolean,
    val AssociatedExternalAuthRecords: List<Any>,
    val AvailableCountries: List<Any>,
    val AvailableStates: List<Any>,
    val AvailableTimeZones: List<AvailableTimeZone>,
    val CheckUsernameAvailabilityEnabled: Boolean,
    val City: Any,
    val CityEnabled: Boolean,
    val CityRequired: Boolean,
    val Company: Any,
    val CompanyEnabled: Boolean,
    val CompanyRequired: Boolean,
    val CountryEnabled: Boolean,
    val CountryId: Int,
    val CountryRequired: Boolean,
    val County: Any,
    val CountyEnabled: Boolean,
    val CountyRequired: Boolean,
    val CustomProperties: CustomProperties,
    val CustomerAttributes: List<Any>,
    val DateOfBirthDay: Any,
    val DateOfBirthEnabled: Boolean,
    val DateOfBirthMonth: Any,
    val DateOfBirthRequired: Boolean,
    val DateOfBirthYear: Any,
    val DisplayVatNumber: Boolean,
    val Email: String,
    val EmailToRevalidate: Any,
    val Fax: Any,
    val FaxEnabled: Boolean,
    val FaxRequired: Boolean,
    val FirstName: String,
    val FirstNameEnabled: Boolean,
    val FirstNameRequired: Boolean,
    val GdprConsents: List<Any>,
    val Gender: String,
    val GenderEnabled: Boolean,
    val LastName: String,
    val LastNameEnabled: Boolean,
    val LastNameRequired: Boolean,
    val Newsletter: Boolean,
    val NewsletterEnabled: Boolean,
    val NumberOfExternalAuthenticationProviders: Int,
    val Phone: Any,
    val PhoneEnabled: Boolean,
    val PhoneRequired: Boolean,
    val Signature: Any,
    val SignatureEnabled: Boolean,
    val StateProvinceEnabled: Boolean,
    val StateProvinceId: Int,
    val StateProvinceRequired: Boolean,
    val StreetAddress: Any,
    val StreetAddress2: Any,
    val StreetAddress2Enabled: Boolean,
    val StreetAddress2Required: Boolean,
    val StreetAddressEnabled: Boolean,
    val StreetAddressRequired: Boolean,
    val TimeZoneId: Any,
    val Username: String,
    val UsernamesEnabled: Boolean,
    val VatNumber: Any,
    val VatNumberStatusNote: String,
    val ZipPostalCode: Any,
    val ZipPostalCodeEnabled: Boolean,
    val ZipPostalCodeRequired: Boolean
)