package org.nmrao.soapuiext.impl
import com.eviware.soapui.model.iface.Request
import com.eviware.soapui.model.iface.Response
import com.eviware.soapui.model.iface.SubmitContext
import org.apache.log4j.Logger

import static org.apache.log4j.Logger.getLogger
import static org.nmrao.soapuiext.SoapUIGroovyScriptEngineHelper.getInstance
/**
 * Created by nmrao on 10/18/15.
 */
class RequestFilter implements com.eviware.soapui.impl.wsdl.submit.RequestFilter {

    private final static Logger log = getLogger(RequestFilter)
    @Override
    void filterRequest(SubmitContext context, Request request) {
        def scriptEngineHelper = getInstance('RF_FILTER_REQUEST', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('request', request)
        log.info('Setting submitContext, request, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRequest(SubmitContext context, Request request) {
        def scriptEngineHelper = getInstance('RF_AFTER_REQUEST', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('request', request)
        log.info('Setting submitContext, request, and log variables')
        scriptEngineHelper.run()
    }

    @Override
    void afterRequest(SubmitContext context, Response response) {
        def scriptEngineHelper = getInstance('RF_AFTER_REQUEST_RESPONSE', context, null, log)
        scriptEngineHelper.scriptEngine.setVariable('response', response)
        log.info('Setting submitContext, request, and log variables')
        scriptEngineHelper.run()
    }
}
