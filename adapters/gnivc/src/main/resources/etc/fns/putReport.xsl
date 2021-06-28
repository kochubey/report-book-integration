<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>


    <xsl:param name="xFileName"/>
    <xsl:param name="xFileSize"/>
    <xsl:param name="xFileContent"/>

    <xsl:param name="xCurrentDateTime"/>

    <xsl:param name="xExchID"/>
    <xsl:param name="xExchTS"/>

    <xsl:param name="xReportType"/>
    <xsl:param name="xReqDateParam"/>


    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:ns0="urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiAsyncMessageConsumerService/types/1.0"
                          xmlns:ns="urn://x-artefacts-gnivc-ru/ExampleSystem/ExampleSubsystem/OspmService/root/312-67">
            <soapenv:Body>
                <ns0:SendMessageRequest>
                    <ns0:Message>
                        <ns:putReportRequest>
                            <xsl:attribute name="requestTS"><xsl:value-of select="$xCurrentDateTime"/></xsl:attribute>
                            <xsl:attribute name="messageId"><xsl:value-of select="$xExchID"/></xsl:attribute>
                            <xsl:attribute name="systemName">AIS_OPN</xsl:attribute>
                            <xsl:attribute name="fileSize"><xsl:value-of select="$xFileSize"/></xsl:attribute>
                            <ns:reportType><xsl:value-of select="$xReportType"/></ns:reportType>
                            <ns:fileName><xsl:value-of select="$xFileName"/></ns:fileName>
                            <ns:fileContent><xsl:value-of select="./filePartContent"/></ns:fileContent>
                        </ns:putReportRequest>
                    </ns0:Message>
                </ns0:SendMessageRequest>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>
