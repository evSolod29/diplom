<#macro authorize ifAnyGranted>
    <#assign authorized = false>
    <#if SPRING_SECURITY_CONTEXT??>a</#if>
        <#list SPRING_SECURITY_CONTEXT.authentication.authorities as authority>
            <#if authority == ifAnyGranted>
                
                <#assign authorized = true>
            
        </#list>
    </#if>
    
</#macro>