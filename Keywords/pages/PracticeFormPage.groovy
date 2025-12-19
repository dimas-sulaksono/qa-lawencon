package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


class PracticeFormPage {

	String url = "https://demoqa.com/automation-practice-form"

	def open() {
		WebUI.openBrowser('')
		
//		if (GlobalVariable.headless == true) {
//			WebUI.navigateToUrl(url)
//		}
		
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(url)
		WebUI.waitForPageLoad(30)
	}

	def fillMandatoryFields(String firstName, String lastName, String gender, String mobile, String email) {
		WebUI.setText(findTestObject('PracticeForm/txt_FirstName'), firstName)
		WebUI.setText(findTestObject('PracticeForm/txt_LastName'), lastName)

		selectGender(gender)

		WebUI.setText(findTestObject('PracticeForm/txt_Mobile'), mobile)
		WebUI.setText(findTestObject('PracticeForm/txt_Email'), email)
	}

	def selectGender(String gender) {
		String g = (gender ?: '').trim().toLowerCase()

		if (g == 'male') {
			WebUI.click(findTestObject('PracticeForm/lbl_Gender_Male'))
		} else if (g == 'female') {
			WebUI.click(findTestObject('PracticeForm/lbl_Gender_Female'))
		} else if (g == 'other') {
			WebUI.click(findTestObject('PracticeForm/lbl_Gender_Other'))
		} else {
			KeywordUtil.logInfo("Gender not selected (value: ${gender})")
		}
	}

	def submit() {
		WebUI.scrollToElement(findTestObject('PracticeForm/btn_Submit'), 5)
		WebUI.click(findTestObject('PracticeForm/btn_Submit'))
	}

	def verifySuccessModalVisible() {
		WebUI.waitForElementVisible(findTestObject('PracticeForm/txt_ModalTitle'), 10)
		WebUI.verifyElementText(findTestObject('PracticeForm/txt_ModalTitle'), 'Thanks for submitting the form')
	}

	def verifySubmittedData(String firstName, String lastName, String gender, String mobile) {
		String expectedName = "${firstName} ${lastName}"

		WebUI.verifyTextPresent(expectedName, false, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyTextPresent(gender, false, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyTextPresent(mobile, false, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	TestObject txtMobile = findTestObject('PracticeForm/txt_Mobile')
	boolean isMobileValid() {
		def el = WebUiCommonHelper.findWebElement(txtMobile, 10)
		return WebUI.executeJavaScript(
			"return arguments[0].checkValidity();",
			Arrays.asList(el)
		) as Boolean
	}
	
	TestObject txtEmail = findTestObject('PracticeForm/txt_Email')
	boolean isEmailValid() {
		def el = WebUiCommonHelper.findWebElement(txtEmail, 10)
		return WebUI.executeJavaScript(
			"return arguments[0].checkValidity();",
			Arrays.asList(el)
		) as Boolean
	}
	

	def close() {
		WebUI.closeBrowser()
	}
}
