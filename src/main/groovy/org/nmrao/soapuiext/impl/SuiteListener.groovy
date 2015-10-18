package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.support.TestSuiteListenerAdapter
import com.eviware.soapui.model.testsuite.TestStep
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance

class SuiteListener extends TestSuiteListenerAdapter {

    private final static Logger log = getLogger(SuiteListener)
    @Override
    void testStepAdded(TestStep testStep, int index) {
        def scriptEngineHelper = getInstance('TEST_SUITE_TEST_STEP_ADDED',null,null,log)
        scriptEngineHelper.scriptEngine.setVariable('testStep',testStep)
        scriptEngineHelper.scriptEngine.setVariable('index',index)
        log.info('Setting testStep, index, and log variables')
        scriptEngineHelper.run()
    }

}
