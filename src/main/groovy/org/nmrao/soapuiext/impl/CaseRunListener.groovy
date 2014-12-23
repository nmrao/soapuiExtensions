package org.nmrao.soapuiext.impl

import com.eviware.soapui.model.support.TestRunListenerAdapter
import com.eviware.soapui.model.testsuite.TestCaseRunContext
import com.eviware.soapui.model.testsuite.TestCaseRunner
import com.eviware.soapui.model.testsuite.TestStep
import com.eviware.soapui.model.testsuite.TestStepResult
import org.apache.log4j.Logger
import org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper

import static org.apache.log4j.Category.getInstance
import static org.apache.log4j.Logger.getLogger

class CaseRunListener extends TestRunListenerAdapter {

    private Logger log = getLogger(CaseRunListener.class)

    @Override
    void beforeRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_BEFORE_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_AFTER_RUN',runContext,testRunner,log)
        log.info('Setting testRunner, context, and log variables')
        scriptEngineHelper.run()
    }

    /*@Override
    void beforeStep(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_BEFORE_STEP',runContext,testRunner,log)
        scriptEngineHelper.run()
    }*/

    @Override
    void beforeStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStep testStep) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_BEFORE_STEP',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable("testStep", testStep)
        log.info('Setting testRunner, testStep, context, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStepResult result) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_AFTER_STEP',runContext,testRunner,log)
        scriptEngineHelper.scriptEngine.setVariable("result", result)
        log.info('Setting testRunner, result, context, and log variables')
        scriptEngineHelper.run()
    }

}