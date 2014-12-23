package org.nmrao.soapuiext.impl

import com.eviware.soapui.model.iface.Request
import com.eviware.soapui.model.support.InterfaceListenerAdapter
import org.apache.log4j.Logger
import org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelperUtil.getInstance

class InterfaceListener extends InterfaceListenerAdapter {

    private Logger log = getLogger(InterfaceListener.class)

    @Override
    public void requestAdded( Request request ) {
        SoapUIGroovyScriptEngineHelper scriptEngineHelper = getInstance('TEST_CASE_AFTER_RUN',null,null,log)
        scriptEngineHelper.scriptEngine.setVariable('request', request)
        log.info('Setting request and log variables')
        scriptEngineHelper.run()
    }

}
