<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:myns='http://www.w3schools.com/myns/'
                version="1.0">
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
<xsl:strip-space elements="*"/>

<xsl:param name="companyInfoF" select="'../xml/CompanyInfo.xml'"/>
<xsl:param name="employmentRecordF" select="'../xml/EmploymentRecord.xml'"/>
<xsl:param name="shortCVF" select="'../xml/ShortCV.xml'"/>
<xsl:param name="transcriptsF" select="'../xml/Transcripts.xml'"/>

<xsl:param name="employmentRecord" select="document($employmentRecordF)"/>
<xsl:param name="shortCV" select="document($shortCVF)"/>
<xsl:param name="companyInfo" select="document($companyInfoF)"/>
<xsl:param name="transcripts" select="document($transcriptsF)"/>

    <xsl:template match="/">

        <applicantProfile>
            <userdata>
                <xsl:apply-templates select="$shortCV/myns:shortCV"/>
            </userdata>

            <employmentRecord>
                <xsl:apply-templates select="$employmentRecord/myns:employmentRecord"/>
            </employmentRecord>

            <transcript>
                <xsl:apply-templates select="$transcripts/myns:transcript"/>
            </transcript>
        </applicantProfile>
    </xsl:template>

    <xsl:template match="myns:shortCV">
        <xsl:copy-of select="*[name()!='briefBio']"/>
    </xsl:template>

    <xsl:template match="myns:employmentRecord">
        <xsl:copy-of select="*[not(name()='personnummer')]"/>
    </xsl:template>

    <xsl:template match="myns:transcript">
        <xsl:copy-of select="*[not(name()='courses')]"/>
    </xsl:template>

</xsl:stylesheet>