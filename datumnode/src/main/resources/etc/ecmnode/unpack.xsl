<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="no" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="xExchID"/>
    <xsl:param name="xExchTS"/>
    <xsl:param name="parentId"/>
    <xsl:param name="parentUUID"/>

    <xsl:param name="fileName"/>
    <xsl:param name="fileSize"/>


    <xsl:template match="/">
{
    "file": {
        "uuid": "<xsl:value-of select="$xExchID"/>",
        "parentId": "<xsl:value-of select="$parentId"/>",
        "parentUUID": "<xsl:value-of select="$parentUUID"/>",
        "name": "<xsl:value-of select="$fileName"/>",
        "size": "<xsl:value-of select="$fileSize"/>",
        "created": "<xsl:value-of select="$xExchTS"/>"
        }
}
    </xsl:template>
</xsl:stylesheet>
