import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testdata.InternalData as InternalData

/*------Login as Admin-----*/

WebUI.callTestCase(findTestCase('CreateAdmin/AdminLogin'), [('url') : 'https://www.phptravels.net/admin'], FailureHandling.STOP_ON_FAILURE)

/*------Navigate to Tours---------*/

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : '#Tours']))

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : 'https://www.phptravels.net/admin/tours/add']))

/*------Establish Internal Data Connection-------------*/

InternalData data = findTestData('InternalData/TourDetails')

/*-----Filling up form using data from Internal Data------*/
int x = 0

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'tourname']), data.internallyGetValue(x++, 0))

WebUI.switchToFrame(findTestObject('TourCreation/iframe_pane'), 5)

WebUI.setText(findTestObject('TourCreation/iframe_text'), data.internallyGetValue(x++, 0))

WebUI.switchToDefaultContent()

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'maxadult']), data.internallyGetValue(x++, 0))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'adultprice']), data.internallyGetValue(x++, 0))

WebUI.click(findTestObject('CommonObjects/span_id', [('id') : 'childbtn']))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'maxchild']), data.internallyGetValue(x++, 0))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'childprice']), data.internallyGetValue(x++, 0))

WebUI.delay(3)

WebUI.scrollToElement(findTestObject('CommonObjects/span_id', [('id') : 'infantbtn']), 10)

WebUI.click(findTestObject('CommonObjects/span_id', [('id') : 'infantbtn']))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'maxinfant']), '2')

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'infantprice']), '200')

WebUI.selectOptionByValue(findTestObject('SupplierCreation/select_assign', [('name') : 'tourstars']), data.internallyGetValue(x++, 
        0), false)

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'tourdays']), data.internallyGetValue(x++, 0))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'tournights']), data.internallyGetValue(x++, 0))

WebUI.selectOptionByValue(findTestObject('SupplierCreation/select_assign', [('name') : 'tourtype']), data.internallyGetValue(x++, 
        0), false)

WebUI.selectOptionByValue(findTestObject('CommonObjects/select_id', [('value') : 'isfeatured']), data.internallyGetValue(x++, 0), 
    false)

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'ffrom']), data.internallyGetValue(x++, 0))

WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : 'fto']), data.internallyGetValue(x++, 0))

WebUI.click(findTestObject('CommonObjects/textbox_name', [('name') : 'fto']))

WebUI.click(findTestObject('CreateAdmin/dropdown_input', [('value') : 's2id_locationlist1']))

WebUI.setText(findTestObject('TourCreation/input_location'), data.internallyGetValue(x, 0))

WebUI.click(findTestObject('TourCreation/select_location', [('value') : data.internallyGetValue(x, 0)]))

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : '#INCLUSIONS']))

for (int i = 203; i < 245; i++) {
    if (((((i == 203) || (i == 210)) || (i == 211)) || (i == 213)) || (i == 220)) {
    } else {
        WebUI.click(findTestObject('CommonObjects/checkbox', [('value') : i]))
    }
}

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : '#EXCLUSIONS']))

for (int j = 250; j < 265; j++) {
    if (((((((j == 253) || (j == 254)) || (j == 255)) || (j == 256)) || (j == 257)) || (j == 258)) || (j == 259)) {
    } else {
        WebUI.click(findTestObject('CommonObjects/checkbox', [('value') : j]))
    }
}

/*----Submit the form and verify no validation box is displayed------*/

WebUI.click(findTestObject('TourCreation/button_Submit'))

WebUI.verifyElementNotPresent(findTestObject('CreateAdmin/alert_validation'), 1)

/*----Verify the tour is created and Logout------*/

WebUI.delay(3)

WebUI.verifyElementText(findTestObject('TourCreation/verify_tour', [('value') : data.internallyGetValue(0, 0)]), data.internallyGetValue(
        0, 0))

WebUI.click(findTestObject('CommonObjects/Logout'))

WebUI.closeBrowser()
