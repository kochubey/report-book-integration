<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="no" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="xExchID"/>
    <xsl:param name="xExchTS"/>


    <xsl:template match="/">
{
    "status": {
        "uuid": "<xsl:value-of select="$xExchID"/>",
        "code": 1000,
        "result": "Сообщение получено",
        "description": "Запрос был принят на обработку.",
        "created": "<xsl:value-of select="$xExchTS"/>"
        }
}
    </xsl:template>
</xsl:stylesheet>
