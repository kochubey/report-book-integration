<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="cfg.openapi.auth.token"/>

    <xsl:param name="exchangeId"/>
    <xsl:param name="currentDateTime"/>
    <xsl:param name="systemName"/>
    <xsl:param name="reportType"/>

    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:ns="urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiMessageConsumerService/types/1.0">
            <soapenv:Body>
                <ns:GetMessageRequest>
                    <ns:Message>
                        <tns:AuthRequest xmlns:tns="urn://x-artefacts-gnivc-ru/ais3/kkt/AuthService/types/1.0">
                            <tns:AuthAppInfo>
                                <tns:MasterToken><xsl:value-of select="$cfg.openapi.auth.token"/></tns:MasterToken>
                            </tns:AuthAppInfo>
                        </tns:AuthRequest>
                    </ns:Message>
                </ns:GetMessageRequest>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>
