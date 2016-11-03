<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:myns='http://www.w3schools.com/myns/'
                 xmlns:exsl="http://exslt.org/common"
                extension-element-prefixes="exsl"
                version="2.0">
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


 <myns:grademap>
        <entry key="A">4</entry>
        <entry key="B">3</entry>
        <entry key="C">2</entry>
        <entry key="D">1</entry>
  </myns:grademap>



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
        
         <xsl:variable name="creditsSum" select="sum($transcripts/myns:transcript/myns:courses/myns:course/myns:crediHours)"/>       
        <!--Logic for calculating GPA :  Grade value * Credit Hr / Total Credit Hrs -->
         <xsl:variable name="gradeSumVar"> 
                <xsl:for-each select="$transcripts/myns:transcript/myns:courses/myns:course">                   
                    <xsl:variable name="grade" select="myns:grade"/>
                    <xsl:variable name="crditHrs" select="myns:crediHours"/>
                    <xsl:variable name="gradeVal" select="document('')/*/myns:grademap/entry[@key=$grade]"/>
                    <gardeVal>                       
                         <xsl:copy-of select="$gradeVal*$crditHrs"/>                        
                    </gardeVal>
                </xsl:for-each>
                 
         </xsl:variable>
         <xsl:variable name="gradeSum" select="sum(exsl:node-set($gradeSumVar)/gardeVal)" />         
         
         <degrees>             
           <xsl:for-each select="$transcripts/myns:transcript/myns:degrees/myns:degree"> 
               <degree xmlns="http://www.w3schools.com/myns/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">  
                    
                    <xsl:copy-of select="./myns:uniName"/> 
                    <xsl:copy-of select="./myns:type"/>                  
                    <GPA>          
                          <xsl:value-of select="number($gradeSum) div number($creditsSum)"/>
                    </GPA> 
                </degree>
                </xsl:for-each>        
         </degrees>

    </xsl:template>
    
    
    
   
    

</xsl:stylesheet>