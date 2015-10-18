package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.support.ProjectRunListenerAdapter
import com.eviware.soapui.model.testsuite.ProjectRunContext
import com.eviware.soapui.model.testsuite.ProjectRunner
import com.eviware.soapui.model.testsuite.TestSuite
import com.eviware.soapui.model.testsuite.TestSuiteRunner
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance

class ProjectRunListener extends ProjectRunListenerAdapter {

    private final static Logger log = getLogger(ProjectRunListener)

    @Override
    void afterTestSuite(ProjectRunner projectRunner, ProjectRunContext runContext, TestSuiteRunner testSuiteRunner) {
        def scriptEngineHelper = getInstance('PROJECT_AFTER_TEST_SUITE',runContext,null,log)
        scriptEngineHelper.scriptEngine.setVariable('testSuiteRunner', testSuiteRunner)
        scriptEngineHelper.scriptEngine.setVariable('projectRunner', projectRunner)
        log.info('Setting testSuiteRunner, projectRunner, context(project run context), and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void beforeTestSuite(ProjectRunner projectRunner, ProjectRunContext runContext, TestSuite testSuite) {
        def scriptEngineHelper = getInstance('PROJECT_BEFORE_TEST_SUITE',runContext,null,log)
        scriptEngineHelper.scriptEngine.setVariable('projectRunner',projectRunner)
        scriptEngineHelper.scriptEngine.setVariable('testSuite', testSuite)
        log.info('Setting projectRunner, testSuite, context(project run context), and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void beforeRun(ProjectRunner projectRunner, ProjectRunContext runContext) {
        def scriptEngineHelper = getInstance('PROJECT_BEFORE_RUN',runContext,null,log)
        scriptEngineHelper.scriptEngine.setVariable('projectRunner',projectRunner)
        log.info('Setting projectRunner, context(project run context), and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRun(ProjectRunner projectRunner, ProjectRunContext runContext) {
        def scriptEngineHelper = getInstance('PROJECT_AFTER_RUN',runContext,null,log)
        scriptEngineHelper.scriptEngine.setVariable('projectRunner',projectRunner)
        log.info('Setting projectRunner, context(project run context), and log variables')
        scriptEngineHelper.run()
    }
}
