<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="" xmlns:xls="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:strip-space elements="*"/>

    <!--    "CamelHttpResponseCode": "200",-->
    <!--    "xDataMethodCategory": "urn:SendMessageRequest",-->
    <!--    "xDataMethodValue": "getReportContentPartRequest",-->

    <xsl:param name="id"/>
    <xsl:param name="uuid"/>

    <xsl:param name="CamelHttpResponseCode"/>
    <xsl:param name="xDataMethodCategory"/>
    <xsl:param name="xDataMethodValue"/>

    <xsl:param name="_action"/>

    <xsl:variable name="statusName">
        <xsl:choose>
            <xsl:when test="$xDataMethodValue='getCorrectedReport'">Запрос отчета вне расписания</xsl:when>
            <xsl:when test="$xDataMethodValue='getScheduledReport'">Запрос отчета по  расписанию</xsl:when>
            <xsl:when test="$xDataMethodValue='getTicketStatus'">Получение статуса запроса отчета</xsl:when>
            <xsl:when test="$xDataMethodValue='getReportContent'">Запрос файла отчета</xsl:when>
            <xsl:when test="$xDataMethodValue='putReport'">Отправка отчета получателю	</xsl:when>
<!--            <xsl:when test="$xDataMethodValue='&#45;&#45;'"><xsl:value-of select="$_action"/></xsl:when>-->
            <xsl:otherwise><xsl:value-of select="$_action"/></xsl:otherwise>
        </xsl:choose>
    </xsl:variable>

    <xsl:template match="/">
        {"id":<xsl:value-of select="$id"/>,"uuid":<xsl:value-of select="$uuid"/>, "action" : "<xls:value-of select="$statusName"/>"}
    </xsl:template>
</xsl:stylesheet>
