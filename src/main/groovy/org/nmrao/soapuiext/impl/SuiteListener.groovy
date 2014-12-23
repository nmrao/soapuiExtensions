package org.nmrao.soapuiext.impl

import com.eviware.soapui.model.support.TestSuiteListenerAdapter
import com.eviware.soapui.model.testsuite.TestStep
import org.apache.log4j.Logger
import org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper

import static org.apache.log4j.Category.getInstance
import static org.apache.log4j.Logger.getLogger

class SuiteListener extends TestSuiteListenerAdapter {

    private Logger log = getLogger(SuiteListener.class)
    @Override
    void testStepAdded(TestStep testStep, int index) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_SUITE_TEST_STEP_ADDED',null,null,log)
        scriptEngineHelper.scriptEngine.setVariable('testStep',testStep)
        scriptEngineHelper.scriptEngine.setVariable('index',index)
        log.info('Setting testStep, index, and log variables')
        scriptEngineHelper.run()
    }

}
