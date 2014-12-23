import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep
log.info 'In Test Suite Test Step Added'
if (testStep instanceof WsdlTestRequestStep) {
	testStep.setPropertyValue('Username','admin')
	testStep.setPropertyValue('Password','admin')
	testStep.httpRequest.wssPasswordType = 'Password Text'
}