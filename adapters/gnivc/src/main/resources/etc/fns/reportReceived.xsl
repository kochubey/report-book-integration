<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

<!--    <xs:restriction base="fnst:resultCodeType">-->
<!--        <xs:enumeration value="SUCCESS"/>-->
<!--        <xs:enumeration value="FILE_SIZE_WRONG"/>-->
<!--        <xs:enumeration value="OTHER_ERROR"/>-->
<!--    </xs:restriction>-->

    <xsl:param name="xExchID"/>
    <xsl:param name="xExchTS"/>

    <xsl:param name="xTicketId"/>

    <xsl:param name="xReportType"/>
    <xsl:param name="xReqDateParam"/>

    <xsl:param name="xCurrentDateTime"/>

    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:ns0="urn://x-artefacts-gnivc-ru/inplat/servin/OpenApiAsyncMessageConsumerService/types/1.0"
                          xmlns:ns="urn://x-artefacts-gnivc-ru/ExampleSystem/ExampleSubsystem/OspmService/root/312-67">
            <soapenv:Body>
                <ns0:SendMessageRequest>
                    <ns0:Message>
                        <ns:reportReceivedRequest>
                            <xsl:attribute name="requestTS"><xsl:value-of select="$xCurrentDateTime"/></xsl:attribute>
                            <xsl:attribute name="messageId"><xsl:value-of select="$xExchID"/></xsl:attribute>
                            <xsl:attribute name="ticketId"><xsl:value-of select="$xTicketId"/></xsl:attribute>
                            <xsl:attribute name="resultCode">SUCCESS</xsl:attribute>
<!--                            <ns:resultText/>-->
                        </ns:reportReceivedRequest>
                    </ns0:Message>
                </ns0:SendMessageRequest>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>
