import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import pages.PracticeFormPage
import utils.TestDataUtil

'Inisiasi page object'
def form = new PracticeFormPage()

'Open browser and navigate to Practice Form Page'
form.open()

'Isi fields mandatory \nData is driven from test data'
form.fillMandatoryFields(
	firstName,
	lastName,
	gender,
	mobile,
	email
)

'Submit form'
form.submit()

//'Verifikasi success modal tampil & data yang di-submit sesuai dengan yang di-input'
if (TestDataUtil.isPositive(expectSuccess)) {
	
	form.verifySuccessModalVisible()
	form.verifySubmittedData(firstName, lastName, gender, mobile)

} else {	
	
//	'Verifikasi success modal tidak tampil \ndan invalid field validation'
	WebUI.verifyElementNotPresent(
		findTestObject('PracticeForm/txt_ModalTitle'),
		5
	)
	
	// Mobile validation only when expected invalid
    if (expectMobileInvalid?.toString()?.toBoolean()) {
        boolean isMobileValid = form.isMobileValid()
        assert isMobileValid == false
    }

    // Email validation only when expected invalid
    //if (expectEmailInvalid?.toString()?.toBoolean()) {
    //    boolean isEmailValid = form.isEmailValid()
    //    assert isEmailValid == false
    //}
	
	if (expectEmailInvalid?.toString()?.toBoolean()) {
		// Assert test data memang invalid format
		assert TestDataUtil.isValidEmailFormat(email) == false
	}
	
}

'Close browser'
form.close()
