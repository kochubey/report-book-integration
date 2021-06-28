<!--<?xml version="1.0" encoding="UTF-8" ?>-->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="dataType"/>
    <xsl:param name="initiator"/>
    <xsl:param name="sessionId"/>
    <xsl:param name="uuid"/>
    <xsl:param name="code"/>
    <xsl:param name="result"/>
    <xsl:param name="description"/>
    <xsl:param name="messageType" select="'xml'"/>

    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                          xmlns:ns="urn://x-artefacts-kom3-tech/datumnode/services/datatype/1.0">
            <soapenv:Body>
                <ns:exchange>
                    <xsl:attribute name="initiator">
                        <xsl:value-of select="$initiator"/>
                    </xsl:attribute>
                    <xsl:attribute name="dataType">
                        <xsl:value-of select="$dataType"/>
                    </xsl:attribute>
                    <xsl:attribute name="sessionId">
                        <xsl:value-of select="$sessionId"/>
                    </xsl:attribute>
                    <xsl:attribute name="uuid">
                        <xsl:value-of select="$uuid"/>
                    </xsl:attribute>
                    <ns:message>
                        <xsl:attribute name="type">
                            <xsl:value-of select="$messageType"/>
                        </xsl:attribute>
                        <ns:status>
                            <ns:code>
                                <xsl:value-of select="$code"/>
                            </ns:code>
                            <ns:result>
                                <xsl:value-of select="$result"/>
                            </ns:result>
                            <ns:description>
                                <xsl:value-of select="$description"/>
                            </ns:description>
                        </ns:status>
                    </ns:message>
                </ns:exchange>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>

