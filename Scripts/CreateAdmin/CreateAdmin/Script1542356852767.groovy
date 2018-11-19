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


WebUI.callTestCase(findTestCase('CreateAdmin/AdminLogin'), [('url') : 'https://www.phptravels.net/admin', ('username') : 'admin@phptravels.com'
        , ('password') : 'demoadmin'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : '#ACCOUNTS']))

WebUI.click(findTestObject('CommonObjects/a_link', [('link') : 'https://www.phptravels.net/admin/accounts/admins/']))

WebUI.click(findTestObject('CommonObjects/button_type', [('type') : 'submit']))


def dbdata = findTestData('DB/Admin_details')

def dbobject = findTestData('DB/Object_repo_admin')

int i
for(i=1; i<= dbobject.getColumnNumbers(); i++)
{
	
	WebUI.setText(findTestObject('CommonObjects/textbox_name', [('name') : dbobject.getValue(i,1)]), dbdata.getValue(i,1))
}

WebUI.click(findTestObject('CreateAdmin/dropdown_input',[('value') : 's2id_autogen1']))
	
WebUI.waitForElementClickable(findTestObject('CreateAdmin/dropdown_search', [('value') : dbdata.getValue(i,1)]), 10)
	
WebUI.click(findTestObject('CreateAdmin/dropdown_search', [('value') : dbdata.getValue(i++,1)]))
	
WebUI.delay(3)
	
WebUI.click(findTestObject('CreateAdmin/div_checkbox_newsletter', [('value') : dbdata.getValue(i,1)]))
	
WebUI.selectOptionByValue(findTestObject('CreateAdmin/dropdown_select', [('name') : 'status']), 'yes', false)

def dbcheckbox =findTestData('DB/Checkbox_Admin')

for(int x=1; x<=dbcheckbox.getRowNumbers(); x++)
{
	for(int y=1; y<=dbcheckbox.getColumnNumbers(); y++)
	{
		WebUI.click(findTestObject('CommonObjects/checkbox_value', [('value') : dbcheckbox.getValue(y,x)]))
	}
}

WebUI.click(findTestObject('CommonObjects/submit_button'))

WebUI.verifyElementNotPresent(findTestObject('CommonObjects/verify_alert'), 1)
   

int j=1
		
String fname = dbdata.getValue(j++,1)
	
String lname = dbdata.getValue(j++,1)
	
String fullname = fname +' '+ lname
	
String username = dbdata.getValue(j++,1)
	
String password = dbdata.getValue(j++,1)
	
WebUI.verifyElementPresent(findTestObject('CommonObjects/verify_admin', [('value') : fname]), 10)
	
WebUI.click(findTestObject('CommonObjects/Logout'))
	

WebUI.setText(findTestObject('CommonObjects/textbox_type', [('type') : 'text']), username)
	
WebUI.setText(findTestObject('CommonObjects/textbox_type', [('type') : 'password']), password)
	
WebUI.click(findTestObject('CommonObjects/button_type', [('type') : 'submit']))
	
WebUI.verifyElementText(findTestObject('AdminLogin/span_text', [('text') : fullname]), fullname)
	
WebUI.click(findTestObject('CommonObjects/Logout'))

WebUI.closeBrowser()

