<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="xMessageSpace"/>
    <xsl:param name="xRequestId"/>
    <xsl:param name="xResponseId"/>


    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:ns="urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiMessageConsumerService/types/1.0">
            <soapenv:Body>
                <ns:PostResponse xmlns:ns="urn://x-artefacts-kom3-tech/datumnode/services/VsExchange/1.0">
                    <ns:Status>
                        <xsl:attribute name="xMessageSpace"><xsl:value-of select="$xMessageSpace"/></xsl:attribute>
                        <xsl:attribute name="xRequestId"><xsl:value-of select="$xRequestId"/></xsl:attribute>
                        <xsl:attribute name="xResponseId"><xsl:value-of select="$xResponseId"/></xsl:attribute>
                        <ns:Code>1000</ns:Code>
                        <ns:Result>Сообщение получено</ns:Result>
                        <ns:Description>Запрос был принят на обработку. </ns:Description>
                    </ns:Status>
                </ns:PostResponse>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>
